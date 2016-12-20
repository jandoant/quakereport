package com.example.android.quakereport;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Earthquake {

    private double magnitude;
    private String location;
    private GregorianCalendar date;

    private String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public Earthquake(GregorianCalendar date, String location, double magnitude) {
        this.date = date;
        this.location = location;
        this.magnitude = magnitude;
    }

    public String getDate() {

        return months[date.get(Calendar.MONTH) - 1] + " " + date.get(Calendar.DAY_OF_MONTH) + ", " + date.get(Calendar.YEAR);
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
