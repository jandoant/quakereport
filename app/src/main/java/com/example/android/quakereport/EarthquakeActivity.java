/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private final String QUERY_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
    ListView earthquakeListView;
    private ArrayList<Earthquake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute(QUERY_URL);
    }

    private void updateUI() {
        earthquakeListView = (ListView) findViewById(R.id.list);
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setOnItemClickListener(this);
    }

    private void showEarthquakeDetail(Earthquake earthquake) {
        String url = earthquake.getUrl();
        openWebPage(url);
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        //Implicit Intent to open up web browser
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void updateData(ArrayList<Earthquake> result) {
        this.earthquakes = result;
    }

    private class FetchDataTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urlStrings) {

            ArrayList<Earthquake> earthquakes = EarthQuakeUtils.fetchEarthquakeData(urlStrings[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> result) {
            if (result == null) {
                Toast.makeText(getApplicationContext(), "Es gibt ein Problem mit der Verbindung", Toast.LENGTH_SHORT).show();
            } else {
                updateData(result);
                updateUI();
            }
        }
    }
}
