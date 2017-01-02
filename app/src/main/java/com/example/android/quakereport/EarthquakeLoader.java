package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jan on 02.01.2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    String queryURL;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.queryURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if (queryURL == "") {
            Log.e(LOG_TAG, "Die URL des Queries ist nicht vorhanden oder leer");
            return null;
        } else {
            ArrayList<Earthquake> result = EarthQuakeUtils.fetchEarthquakeData(queryURL);
            return result;
        }
    }
}
