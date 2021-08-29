package com.example.smartcardpicker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.ArrayList;

public class CardsListActivity extends AppCompatActivity {

    DBHelper mydb;
    SQLiteDatabase db;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardslist);

        Button addCardBtn = findViewById(R.id.addCardBtn);
        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(CardsListActivity.this, BindingActivity.class);
                CardsListActivity.this.startActivity(switchActivityIntent);
            }
        });

//        ImageButton fiveCard = findViewById(R.id.fiveCard);
//        fiveCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent switchActivityIntent = new Intent(CardsListActivity.this, MainActivity.class);
//                switchActivityIntent.putExtra("currentCardName", "Пятёрочка");
//                CardsListActivity.this.startActivity(switchActivityIntent);
//            }
//        });

//        ImageButton crossCard = findViewById(R.id.crossCard);
//        crossCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent switchActivityIntent = new Intent(CardsListActivity.this, MainActivity.class);
//                switchActivityIntent.putExtra("currentCardName", "Перекрёсток");
//                CardsListActivity.this.startActivity(switchActivityIntent);
//            }
//        });

//        ImageButton magneteCard = findViewById(R.id.magneteCard);
//        magneteCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent switchActivityIntent = new Intent(CardsListActivity.this, MainActivity.class);
//                switchActivityIntent.putExtra("currentCardName", "Магнит");
//                CardsListActivity.this.startActivity(switchActivityIntent);
//            }
//        });

        db = openOrCreateDatabase("cardbinderdb.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor mycards = db.rawQuery("Select * from smartcards", null);
        mycards.moveToFirst();
        @SuppressLint("WrongConstant") SQLiteDatabase db = openOrCreateDatabase("cardbinderdb.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS smartcards (_id INTEGER PRIMARY KEY AUTOINCREMENT, cardname TEXT, barcode TEXT);");
        Log.d("myTag", "db.numberOfRows(): " + (int) DatabaseUtils.queryNumEntries(db, "smartcards"));
        for(int recordIdx = 0; recordIdx < DatabaseUtils.queryNumEntries(db, "smartcards"); recordIdx++) {
            ImageButton bindingCard = new ImageButton(CardsListActivity.this);
//            bindingCard.setBackgroundColor(android.graphics.Color.rgb(255, 0, 0));
            bindingCard.setImageResource(R.drawable.five);
//            bindingCard.setImageResource(700082);

            bindingCard.setVisibility(View.VISIBLE);
//            bindingCard.setLayoutParams(
//                new LinearLayout.LayoutParams(100,100)
//            );
//            LinearLayout scrollLayout = findViewById(R.id.layoutOfCards);
//            bindingCard.setLayoutParams(
//                    scrollLayout.getLayoutParams()
//            );
            bindingCard.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    )
            );
            LinearLayout scrollLayout = findViewById(R.id.layoutOfCards);
            scrollLayout.addView(bindingCard);
            bindingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent switchActivityIntent = new Intent(CardsListActivity.this, MainActivity.class);
                    switchActivityIntent.putExtra("currentCardName", "Пятёрочка");
                    switchActivityIntent.putExtra("currentBarCode", mycards.getString(2));
                    CardsListActivity.this.startActivity(switchActivityIntent);
                }
            });
            Log.d("mytag", "bindingCard " + recordIdx + ": " + bindingCard.toString());
            Log.d("mytag", "bindingCard Width " + recordIdx + ": " + bindingCard.getWidth());
            Log.d("mytag", "bindingCard Alpha " + recordIdx + ": " + bindingCard.getAlpha());
            Log.d("mytag", "bindingCard getVisibility " + recordIdx + ": " + bindingCard.getVisibility());
            Log.d("mytag", "bindingCard getHeight " + recordIdx + ": " + bindingCard.getHeight());
            Log.d("mytag", "bindingCard getImageAlpha " + recordIdx + ": " + bindingCard.getImageAlpha());
            Log.d("mytag", "bindingCard getHeight " + recordIdx + ": " + bindingCard.getLayoutParams().height);
            Log.d("mytag", "bindingCard getHeight " + recordIdx + ": " + bindingCard.getLayoutParams().width);
            if(recordIdx < DatabaseUtils.queryNumEntries(db, "smartcards") - 1) {
                mycards.moveToNext();
            }
        }

//        mydb = new DBHelper(CardsListActivity.this);
//        Log.d("myTag", "mydb.numberOfRows(): " + mydb.numberOfRows());

//        ArrayList<String> cards = new ArrayList<String>();
//        cards.add("Five");
//        cards.add("Cross");
//        cards.add("Magnete");
//        for(String card : cards){
//            ImageButton bindingCard = new ImageButton(CardsListActivity.this);
////            bindingCard.setLayoutParams(
////                new LinearLayout.LayoutParams(
////                    LinearLayout.LayoutParams.
////                )
////            );
//            bindingCard.setBackgroundColor(android.graphics.Color.rgb(255, 0, 0));
//        }

    }
}
