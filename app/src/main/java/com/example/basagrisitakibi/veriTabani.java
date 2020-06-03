package com.example.basagrisitakibi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class veriTabani extends SQLiteOpenHelper {
    public veriTabani(@Nullable Context context) {
        super(context, "kayitlar", null, 1);
    }

    @Override



    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS  kayitlar ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'startTime' VARCHAR , 'stopTime' VARCHAR, 'agriSiddeti' VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kayitlar");
        onCreate(db);
    }
}
