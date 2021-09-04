package com.example.smartcardpicker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

public class BindingActivity extends AppCompatActivity {

    DBHelper mydb;
    public ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    public Task<Text> result;

    public void captureListener(){
        try {
            // Camera provider is now guaranteed to be available
            ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

            // Set up the view finder use case to display camera preview
            Preview preview = new Preview.Builder().build();

            ImageCapture imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).build();
            CameraSelector cameraSelector = new CameraSelector.Builder().build();
//            Camera camera = cameraProvider.bindToLifecycle(((LifecycleOwner) BindingActivity.this), cameraSelector, preview, imageAnalysis);
        } catch(ExecutionException | InterruptedException e){

        }
    }

    public void setBarCode(){
        TextView barCode = findViewById(R.id.barCode);
        barCode.setText(result.getResult().getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        ActivityCompat.requestPermissions(BindingActivity.this, new String[]{ Manifest.permission.CAMERA }, PackageManager.PERMISSION_GRANTED);
        ImageButton scanBarCode = findViewById(R.id.scanBarCodeBtn);
        scanBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(takePicture, 0);

//                BarccodeAnalyzer barccodeAnalyzer= new BarccodeAnalyzer();


//                MyCameraConfig myCameraConfig =  new MyCameraConfig();
//                cameraProviderFuture = ProcessCameraProvider.getInstance(myCameraConfig);
                cameraProviderFuture = ProcessCameraProvider.getInstance(BindingActivity.this);
                cameraProviderFuture.addListener(() -> {
                    try {
                        // Camera provider is now guaranteed to be available
                        ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                        // Set up the view finder use case to display camera preview
                        Preview preview = new Preview.Builder().build();

                        ImageCapture imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).build();

//                        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
                        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                        PreviewView previewView = findViewById(R.id.previewView);
                        preview.setSurfaceProvider(previewView.getSurfaceProvider());

//                        Camera camera = cameraProvider.bindToLifecycle(((LifecycleOwner) BindingActivity.this), cameraSelector, preview, imageAnalysis);

                        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720)).setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();
                        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(getApplicationContext()),  new ImageAnalysis.Analyzer() {
                            @Override
                            public void analyze(@NonNull ImageProxy imageProxy) {
                                int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();

                                // insert your code here.
                                Log.d("mytag", String.valueOf(imageProxy.getImageInfo().getRotationDegrees()));
                                @SuppressLint("UnsafeOptInUsageError") Image mediaImage = imageProxy.getImage();
                                if (mediaImage != null) {
                                    InputImage image = InputImage.fromMediaImage(mediaImage, imageProxy.getImageInfo().getRotationDegrees());
                                    TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
                                    result = recognizer.process(image).addOnSuccessListener(new OnSuccessListener() {
                                        @Override
                                        public void onSuccess(@NonNull Object o) {

                                            Log.d("mytag", "распознано: " + o.toString());
//                                            TextView barCode = findViewById(R.id.barCode);
//                                            barCode.setText(o.toString());
                                            setBarCode();
                                            cameraProvider.unbindAll();
                                            previewView.setVisibility(View.INVISIBLE);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("mytag", "не распознано: " + e.toString());
                                            cameraProvider.unbindAll();
                                            previewView.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                }

                                imageProxy.close();
                            }
                        });

                        cameraProvider.unbindAll();
                        cameraProvider.bindToLifecycle(((LifecycleOwner) BindingActivity.this), cameraSelector, preview, imageCapture, imageAnalysis);
//                        cameraProvider.bindToLifecycle(((LifecycleOwner) BindingActivity.this), cameraSelector, preview);
                        previewView.setVisibility(View.VISIBLE);

                    } catch(ExecutionException | InterruptedException e){

                    }
                }, ContextCompat.getMainExecutor(BindingActivity.this));

            }
        });

        Button bindingBtn = findViewById(R.id.bindingBtn);
        bindingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                mydb = new DBHelper(BindingActivity.this);

//                Cursor rs = mydb.getData(Value);
//                id_To_Update = Value;
//                rs.moveToFirst();
//                String cardname = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_CARDNAME));
//                String barcode = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_BARCODE));
//                if (!rs.isClosed()) {
//                    rs.close();
//                }

//                TextView smartCardName = findViewById(R.id.smartCardName);
//                TextView barCode = findViewById(R.id.barCode);
//                mydb.insertSmartCard("fiveCard", "789123456789");

                TextView smartCardName = findViewById(R.id.newSmartCardName);
                TextView barCode = findViewById(R.id.barCode);
                @SuppressLint("WrongConstant") SQLiteDatabase db = openOrCreateDatabase("cardbinderdb.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                Bundle extras = getIntent().getExtras();
                if(extras != null) {
                    String cardType = extras.getString("currentCardType").toString();
//                    String cardType = "five";
                    db.execSQL("INSERT INTO \"smartcards\"(cardname, barcode, cardtype) VALUES (\"" + smartCardName.getText().toString() + "\", \"" + barCode.getText().toString() + "\", \"" + cardType + "\");");
                }
//                db.execSQL("INSERT INTO \"smartcards\"(cardname, barcode, cardtype) VALUES (\"a\", \"5\", \"five\");");
                Log.d("myTag", "db.numberOfRows(): " + (int) DatabaseUtils.queryNumEntries(db, "smartcards"));
                Intent switchActivityIntent = new Intent(BindingActivity.this, CardsListActivity.class);
                BindingActivity.this.startActivity(switchActivityIntent);

            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String cardType = extras.getString("currentCardType").toString();
            if (cardType.contains("five")) {
                ImageView bindingCardImg = findViewById(R.id.bindingCardImg);
                bindingCardImg.setImageResource(R.drawable.five);
            } else if (cardType.contains("cross")) {
                ImageView bindingCardImg = findViewById(R.id.bindingCardImg);
                bindingCardImg.setImageResource(R.drawable.cross);
            } else if (cardType.contains("magnet")) {
                ImageView bindingCardImg = findViewById(R.id.bindingCardImg);
                bindingCardImg.setImageResource(R.drawable.magnet);
            }
        }
    }

}
