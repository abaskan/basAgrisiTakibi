package com.example.basagrisitakibi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RaporlarDao {
    public ArrayList<Raporlar> tumRaporlar(raporlarVeriTabanı rvt){
        ArrayList<Raporlar> raporlarArrayList = new ArrayList<>();
        SQLiteDatabase db = rvt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM raporlar",null);
        while (c.moveToNext()){
            Raporlar r = new Raporlar(c.getInt(c.getColumnIndex("raporId")),
                    c.getString(c.getColumnIndex("kayitTarihi")),
                    c.getString(c.getColumnIndex("tetikleyiciListesi")),
                    c.getString(c.getColumnIndex("semptomListesi")),
                    c.getString(c.getColumnIndex("ilacListesi")),
                    c.getString(c.getColumnIndex("notlar")));

            raporlarArrayList.add(r);

        }
        db.close();
        return raporlarArrayList;
    }

    public void raporSil(raporlarVeriTabanı rvt,int raporId){
        SQLiteDatabase db = rvt.getWritableDatabase();
        db.delete("raporlar","raporId=?",new String[]{String.valueOf(raporId)});
        db.close();

    }

    public void raporEkle (raporlarVeriTabanı rvt , String kayitTarihi, String tetikleyiciListesi, String semptomListesi, String ilacListesi,String notlar){
        SQLiteDatabase db = rvt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kayitTarihi",kayitTarihi);
        values.put("tetikleyiciListesi",tetikleyiciListesi);
        values.put("semptomListesi",semptomListesi);
        values.put("ilacListesi",ilacListesi);
        values.put("notlar",notlar);
        db.insertOrThrow("raporlar",null,values);
        db.close();
    }

    public void raporDuzenle(raporlarVeriTabanı rvt,int raporId,String kayitTarihi,String tetikleyiciListesi,String semptomListesi,String ilacListesi,String notlar ){
        SQLiteDatabase db = rvt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kayitTarihi",kayitTarihi);
        values.put("tetikleyiciListesi",tetikleyiciListesi);
        values.put("semptomListesi",semptomListesi);
        values.put("ilacListesi",ilacListesi);
        values.put("notlar",notlar);
        db.update("raporlar",values,"raporId=?",new String[]{String.valueOf(raporId)});
        db.close();
    }
}
