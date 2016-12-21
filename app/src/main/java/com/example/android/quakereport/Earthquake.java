package com.example.android.quakereport;

import java.text.DateFormat;
import java.util.Date;

public class Earthquake {

    private String magnitude;
    private String place;
    private long time;

    public Earthquake(long time, String place, String magnitude) {
        this.time = time;
        this.place = place;
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {

        Date date = new Date(time);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(date);
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "magnitude='" + magnitude + '\'' +
                ", place='" + place + '\'' +
                ", time=" + time +
                '}';
    }
}
