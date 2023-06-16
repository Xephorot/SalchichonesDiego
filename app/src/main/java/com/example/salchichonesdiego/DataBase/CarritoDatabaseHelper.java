package com.example.salchichonesdiego.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.salchichonesdiego.Combos.Combo;

import java.util.ArrayList;
import java.util.List;

public class CarritoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "carrito.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "carrito";
    private static final String COLUMN_COMBO_NAME = "combo_name";
    private static final String COLUMN_COMBO_PRICE = "combo_price";

    public CarritoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla del carrito de compras
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_COMBO_NAME + " TEXT PRIMARY KEY," +
                COLUMN_COMBO_PRICE + " REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar la actualización de la base de datos, si se requiere
        // Aquí puedes implementar la lógica para migrar datos en caso de una nueva versión de la base de datos
    }

    public void agregarCombo(Combo combo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_COMBO_NAME, combo.getNombre());
        values.put(COLUMN_COMBO_PRICE, combo.getPrecio());

        db.insert(TABLE_NAME, null, values);
    }

    public List<Combo> getCombos() {
        List<Combo> combos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String comboName = cursor.getString(cursor.getColumnIndex(COLUMN_COMBO_NAME));
                @SuppressLint("Range") double comboPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_COMBO_PRICE));

                Combo combo = new Combo(comboName, comboPrice, "", "");
                combos.add(combo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return combos;
    }

    public void eliminarCombos() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
