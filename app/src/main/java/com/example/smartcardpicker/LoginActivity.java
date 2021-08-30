package com.example.smartcardpicker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;
    boolean needPassword = false;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = openOrCreateDatabase("cardbinderdb.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//        db.execSQL("DROP TABLE IF EXISTS smartcards");
        db.execSQL("CREATE TABLE IF NOT EXISTS passwords (_id INTEGER PRIMARY KEY AUTOINCREMENT, password TEXT);");
        if(DatabaseUtils.queryNumEntries(db, "passwords") >= 1) {
            needPassword = false;
        } else if(DatabaseUtils.queryNumEntries(db, "passwords") <= 0) {
            needPassword = true;
        }


//        Button btnEnter = findViewById(R.id.btnEnter);
//        btnEnter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
//                LoginActivity.this.startActivity(switchActivityIntent);
//            }
//        });

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() >= 1) {
                    password.setText(password.getText().subSequence(0, password.getText().length() - 1));
                }
            }
        });

        Button numOne = findViewById(R.id.numOne);
        numOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "1");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numTwo = findViewById(R.id.numTwo);
        numTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "2");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numThree = findViewById(R.id.numThree);
        numThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "3");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numFour = findViewById(R.id.numFour);
        numFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "4");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numFive = findViewById(R.id.numFive);
        numFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "5");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numSix = findViewById(R.id.numSix);
        numSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "6");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numSeven = findViewById(R.id.numSeven);
        numSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "7");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numEight = findViewById(R.id.numEight);
        numEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "8");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

        Button numNine = findViewById(R.id.numNine);
        numNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText password = findViewById(R.id.editTextNumberPassword);
                if(password.getText().length() <= 3) {
                    password.setText(password.getText() + "9");
                    if (password.getText().length() >= 4) {

                        if(needPassword){
                            db.execSQL("INSERT INTO \"passwords\"(password) VALUES (" + password.getText() + ");");
                            Log.d("mytag", "Создаю пароль: " + password.getText());
                            Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                            LoginActivity.this.startActivity(switchActivityIntent);
                        } else if(!needPassword){
                            Cursor passwords = db.rawQuery("Select * from passwords",null);
                            passwords.moveToFirst();
                            String bindingPassword = passwords.getString(1);
                            if(password.getText().toString().contains(bindingPassword)){
                                Log.d("mytag", "Пароль правильный: " + password.getText());
                                Intent switchActivityIntent = new Intent(LoginActivity.this, CardsListActivity.class);
                                LoginActivity.this.startActivity(switchActivityIntent);
                            } else if(!password.getText().toString().contains(bindingPassword)) {
                                Log.d("mytag", "Пароль неправильный: " + password.getText() + " | должен быть: " + bindingPassword);
                            }
                        }

                    }
                }
            }
        });

//        PasswordDBHelper myDB = new PasswordDBHelper(LoginActivity.this);
//        TextView loginLabel = findViewById(R.id.loginLabel);
//        if(myDB.hasPassword()) {
//            loginLabel.setText("Войти");
//        } else if(!myDB.hasPassword()) {
//            loginLabel.setText("Придумайте пароль");
//        }

    }
}