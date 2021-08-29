package com.example.smartcardpicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PasswordDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "passwords.db";
    public static final String CONTACTS_TABLE_NAME = "passwords";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_CARDNAME = "password";

    public PasswordDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

//    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, "DATABASE_NAME", factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("CREATE TABLE passwords (_id INTEGER PRIMARY KEY AUTOINCREMENT, password TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {}

    public boolean insertSmartCard (String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardname", name);
        contentValues.put("barcode", phone);
        db.insert("smartcards", null, contentValues);
        return true;
    }

    public boolean hasPassword(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "passwords");
        return numRows >= 1;
    }

    public boolean updateSmartCard (Integer id, String cardname, String barcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", cardname);
        contentValues.put("phone", barcode);
        db.update("smartcards", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

}
