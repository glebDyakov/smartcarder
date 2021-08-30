package com.example.smartcardpicker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.smartcardpicker.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());

//        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.fragment_first);


//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // дальше я дописываю своё
//

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            TextView smartCardName = findViewById(R.id.smartCardName);

            //            Log.d("mytag", String.valueOf(extras.getInt("currentCardId")));
//            Log.d("mytag", extras.getString("currentCardName"));


            @SuppressLint("WrongConstant") SQLiteDatabase db = openOrCreateDatabase("cardbinderdb.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//            Cursor currentCard = db.rawQuery("Select * from smartcards where _id=" + extras.getInt("currentCardId"), null);
            Cursor currentCard = db.rawQuery("Select * from smartcards", null);
            currentCard.moveToFirst();
            String extaCardName = extras.getString("currentCardName");
            for(int cardidx = 0; cardidx < DatabaseUtils.queryNumEntries(db, "smartcards"); cardidx++){


                Log.d("mytag", "cardidx: " + cardidx + "| cardid: " + String.valueOf(currentCard.getInt(0)));
//                Log.d("mytag", "extrasId: " + String.valueOf(extras.getInt("currentCardId")));
                Log.d("mytag", "extrasName: " + extaCardName);

                if(extaCardName.contains(currentCard.getString(1))){
                    smartCardName.setText(currentCard.getString(1));
                    TextView barCodeText = findViewById(R.id.barCodeText);
                    barCodeText.setText(currentCard.getString(2));
                    if(currentCard.getString(3).contains("five")){
                        ImageView smartCardImg = findViewById(R.id.smartCardImg);
                        smartCardImg.setImageResource(R.drawable.five);
                    } else if(currentCard.getString(3).contains("cross")){
                        ImageView smartCardImg = findViewById(R.id.smartCardImg);
                        smartCardImg.setImageResource(R.drawable.cross);
                    } else if(currentCard.getString(3).contains("magnet")){
                        ImageView smartCardImg = findViewById(R.id.smartCardImg);
                        smartCardImg.setImageResource(R.drawable.magnet);
                    }
                    break;
                }
                currentCard.moveToNext();
            }

        }

//        Bundle extras = getIntent().getExtras();
//        if(extras != null) {
//            TextView smartCardName = findViewById(R.id.smartCardName);
//            smartCardName.setText(extras.getString("currentCardName"));
//            TextView barCodeText = findViewById(R.id.barCodeText);
//            barCodeText.setText(extras.getString("currentBarCode"));
//            if(extras.getString("currentCardType").contains("five")){
//                ImageView smartCardImg = findViewById(R.id.smartCardImg);
//                smartCardImg.setImageResource(R.drawable.five);
//            } else if(extras.getString("currentCardType").contains("cross")){
//                ImageView smartCardImg = findViewById(R.id.smartCardImg);
//                smartCardImg.setImageResource(R.drawable.cross);
//            } else if(extras.getString("currentCardType").contains("magnet")){
//                ImageView smartCardImg = findViewById(R.id.smartCardImg);
//                smartCardImg.setImageResource(R.drawable.magnet);
//            }
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}