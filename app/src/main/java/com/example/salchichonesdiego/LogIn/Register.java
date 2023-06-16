package com.example.salchichonesdiego.LogIn;

import com.example.salchichonesdiego.DataBase.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Register {
    private static Register instance;
    private Context context;

    private Register(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized Register getInstance(Context context) {
        if (instance == null) {
            instance = new Register(context);
        }
        return instance;
    }

    public void registerUser(String username, String password, String email) {
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);

        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }
}
