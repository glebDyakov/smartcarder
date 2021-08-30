package com.example.smartcardpicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CardTypes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardstypes);

        ImageButton fiveCard = findViewById(R.id.five);
        fiveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(CardTypes.this, BindingActivity.class);
                switchActivityIntent.putExtra("currentCardType", "five");
                CardTypes.this.startActivity(switchActivityIntent);
            }
        });

        ImageButton crossCard = findViewById(R.id.cross);
        fiveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(CardTypes.this, BindingActivity.class);
                switchActivityIntent.putExtra("currentCardType", "cross");
                CardTypes.this.startActivity(switchActivityIntent);
            }
        });

        ImageButton magnetCard = findViewById(R.id.magnet);
        fiveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(CardTypes.this, BindingActivity.class);
                switchActivityIntent.putExtra("currentCardType", "magnet");
                CardTypes.this.startActivity(switchActivityIntent);
            }
        });

    }
}
