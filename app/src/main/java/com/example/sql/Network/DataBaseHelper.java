package com.example.sql.Network;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sql.Singleton;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "server_DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDataBase) {
        MyDataBase.execSQL("create Table users(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDataBase, int i, int i1) {
        MyDataBase.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDataBase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDataBase = this.getWritableDatabase();
        Cursor cursor = MyDataBase.rawQuery("Select * from users where email = ?",
                new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDataBase = this.getWritableDatabase();
        Cursor cursor = MyDataBase.rawQuery("Select * from users where email = ? and password = ?",
                new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor readAllData() {
        String query = "Select * from users";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("users", null, null);
    }

    public Boolean insertUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ?",
                new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
