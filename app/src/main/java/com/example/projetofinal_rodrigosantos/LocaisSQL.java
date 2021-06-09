package com.example.projetofinal_rodrigosantos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class LocaisSQL extends SQLiteOpenHelper {
    private  static final String DB_NAME = "Locais.db";
    private static final int DB_VERSION = 1;

    public  LocaisSQL(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        if (db.isReadOnly()){
            db = getWritableDatabase();
        }

        String query = "CREATE TABLE locais (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " local TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS locais");
        onCreate(db);
    }

    public void guardar(SQLiteDatabase db, String reg){
        String x = reg.toString();
        db.execSQL("INSERT INTO locais(local) VALUES ('" + x + "')");
    }
}
