package com.example.basagrisitakibi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class KayitlarDao {

    public ArrayList<Kayitlar> tumKayitlar(veriTabani vt){
        ArrayList<Kayitlar> kayitlarArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kayitlar",null);
        while (c.moveToNext()){
            Kayitlar k = new Kayitlar(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("startTime")),
                    c.getString(c.getColumnIndex("stopTime")),
                    c.getString(c.getColumnIndex("agriSiddeti")));

            kayitlarArrayList.add(k);

        }
        db.close();
        return kayitlarArrayList;
    }

    public void kayitSil(veriTabani vt,int id){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("kayitlar","id=?",new String[]{String.valueOf(id)});
        db.close();

    }

    public void kayitEkle (veriTabani vt ,String startTime,String stopTime,String agriSiddeti){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("startTime",startTime);
        values.put("stopTime",stopTime);
        values.put("agriSiddeti",agriSiddeti);
        db.insertOrThrow("kayitlar",null,values);
        db.close();
    }

    public void kayitDuzenle(veriTabani vt,int id,String startTime,String stopTime,String agriSiddeti ){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("startTime",startTime);
        values.put("stopTime",stopTime);
        values.put("agriSiddeti",agriSiddeti);
        db.update("kayitlar",values,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
