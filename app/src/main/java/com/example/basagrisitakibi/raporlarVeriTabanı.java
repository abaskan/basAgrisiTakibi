package com.example.basagrisitakibi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class raporlarVeriTabanı extends SQLiteOpenHelper {

    public raporlarVeriTabanı(Context context) {
        super(context, "raporlar", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS  raporlar ('raporId' INTEGER PRIMARY KEY AUTOINCREMENT,'kayitTarihi' VARCHAR ,'tetikleyiciListesi' VARCHAR , 'semptomListesi' VARCHAR, 'ilacListesi' VARCHAR, 'notlar' VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS raporlar");
        onCreate(db);
    }
}
