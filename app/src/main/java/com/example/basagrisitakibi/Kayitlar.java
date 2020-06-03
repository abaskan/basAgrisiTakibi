package com.example.basagrisitakibi;

import java.io.Serializable;

public class Kayitlar implements Serializable {
    int id;
    String startTime;
    String stopTime;
    String agriSiddeti;

    public Kayitlar(int id, String startTime, String stopTime, String agriSiddeti) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.agriSiddeti = agriSiddeti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getAgriSiddeti() {
        return agriSiddeti;
    }

    public void setAgriSiddeti(String agriSiddeti) {
        this.agriSiddeti = agriSiddeti;
    }
}
