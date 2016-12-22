package com.example.android.quakereport;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private double magnitude;
    private String place;
    private long time;
    private String url;

    public Earthquake(long time, String place, double magnitude, String url) {
        this.time = time;
        this.place = place;
        this.magnitude = magnitude;
        this.url = url;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMagnitude() {
        DecimalFormat format = new DecimalFormat("0.0", DecimalFormatSymbols.getInstance());
        return format.format(magnitude);
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double getMagnitudeAsDouble() {
        return magnitude;
    }

    public String getDate() {

        Date date = new Date(time);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return df.format(date);
    }

    public String getTime() {

        Date date = new Date(time);
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");

        return tf.format(date);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDistance() {
        String[] subString = place.split(" ");
        String dist = "";

        if (hasDistanceInfo()) {
            for (int i = 0; i < 3; i++) {
                dist += subString[i] + " ";
            }
        } else {
            dist = "";
        }

        return dist;
    }

    public String getCity() {
        String[] subString = place.split(" ");
        String city = "";

        if (!hasDistanceInfo()) { // keine Angabe der Entfernung -> nur City zeigen
            for (int i = 0; i < subString.length; i++) {
                city += subString[i] + " ";
            }
        } else {
            for (int i = 3; i < subString.length; i++) {
                city += subString[i] + " ";
            }
        }
        return city;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "magnitude='" + magnitude + '\'' +
                ", place='" + place + '\'' +
                ", time=" + time +
                '}';
    }

    private boolean hasDistanceInfo() {
        String[] subString = place.split(" ");
        return subString.length > 3;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
