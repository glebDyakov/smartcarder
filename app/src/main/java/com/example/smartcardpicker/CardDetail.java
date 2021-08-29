package com.example.smartcardpicker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CardDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentcard);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            TextView smartCardName = findViewById(R.id.smartCardName);
            smartCardName.setText(extras.getString("currentCardName"));
        }

    }

}
