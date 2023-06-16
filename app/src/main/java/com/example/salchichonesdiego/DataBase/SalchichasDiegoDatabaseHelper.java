package com.example.salchichonesdiego.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SalchichasDiegoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "salchichones_diego.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla de Salchichas
    private static final String CREATE_TABLE_SALCHICHAS =
            "CREATE TABLE salchichas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "precio REAL," +
                    "descripcion TEXT)";

    // Sentencia SQL para crear la tabla de Combos
    private static final String CREATE_TABLE_COMBOS =
            "CREATE TABLE combos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "precio REAL," +
                    "descripcion TEXT," +
                    "salchichas TEXT)";

    public SalchichasDiegoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear las tablas de la base de datos
        db.execSQL(CREATE_TABLE_SALCHICHAS);
        db.execSQL(CREATE_TABLE_COMBOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar la actualización de la base de datos, si se requiere
        // Aquí puedes implementar la lógica para migrar datos en caso de una nueva versión de la base de datos
    }
}
