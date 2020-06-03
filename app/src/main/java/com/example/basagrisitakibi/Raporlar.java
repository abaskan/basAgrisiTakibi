package com.example.basagrisitakibi;

import java.io.Serializable;

public class Raporlar implements Serializable {

    int raporId;
    String kayitTarihi;
    String tetikleyiciListesi;
    String semptomListesi;
    String ilacListesi;
    String notlar;

    public Raporlar() {
    }

    public Raporlar(int raporId, String kayitTarihi, String tetikleyiciListesi, String semptomListesi, String ilacListesi, String notlar) {
        this.raporId = raporId;
        this.kayitTarihi = kayitTarihi;
        this.tetikleyiciListesi = tetikleyiciListesi;
        this.semptomListesi = semptomListesi;
        this.ilacListesi = ilacListesi;
        this.notlar = notlar;
    }

    public int getRaporId() {
        return raporId;
    }

    public void setRaporId(int raporId) {
        this.raporId = raporId;
    }

    public String getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(String kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public String getTetikleyiciListesi() {
        return tetikleyiciListesi;
    }

    public void setTetikleyiciListesi(String tetikleyiciListesi) {
        this.tetikleyiciListesi = tetikleyiciListesi;
    }

    public String getSemptomListesi() {
        return semptomListesi;
    }

    public void setSemptomListesi(String semptomListesi) {
        this.semptomListesi = semptomListesi;
    }

    public String getIlacListesi() {
        return ilacListesi;
    }

    public void setIlacListesi(String ilacListesi) {
        this.ilacListesi = ilacListesi;
    }

    public String getNotlar() {
        return notlar;
    }

    public void setNotlar(String notlar) {
        this.notlar = notlar;
    }
}
