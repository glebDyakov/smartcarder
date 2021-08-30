package com.example.smartcardpicker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BindingActivity extends AppCompatActivity {

    DBHelper mydb;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_binding);
        setContentView(R.layout.activity_binding);

        ImageButton scanBarCode = findViewById(R.id.scanBarCodeBtn);
        scanBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
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
//                if(extras != null) {
//                    String cardType = extras.getString("currentCardType").toString();
//                    db.execSQL("INSERT INTO \"smartcards\"(cardname, barcode, cardtype) VALUES (" + smartCardName.getText().toString() + ", " + barCode.getText().toString() + ", " + cardType + ");");
//                }
                db.execSQL("INSERT INTO \"smartcards\"(cardname, barcode) VALUES (" + smartCardName.getText().toString() + ", " + barCode.getText().toString() + ");");
                Log.d("myTag", "db.numberOfRows(): " + (int) DatabaseUtils.queryNumEntries(db, "smartcards"));
                Intent switchActivityIntent = new Intent(BindingActivity.this, CardsListActivity.class);
                BindingActivity.this.startActivity(switchActivityIntent);

            }
        });

    }
}
