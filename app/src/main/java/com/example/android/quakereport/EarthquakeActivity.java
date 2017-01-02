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

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final int EARTHQUAKE_LOADER_ID = 1;
    final String QUERY_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-12-31&minfelt=50&minmagnitude=7";
    ListView listView;
    TextView txt_emptyView;
    EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        initUI();
        //initialitze Loader
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    private void initUI() {
        listView = (ListView) findViewById(R.id.list);
        txt_emptyView = (TextView) findViewById(R.id.empty);

        // Create a new adapter that takes an empty list of earthquakes as data-input
        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setEmptyView(txt_emptyView);
    }

    private void updateUI(ArrayList<Earthquake> data) {
        adapter.clear();
        adapter.addAll(data);
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
        showEarthquakeDetail(adapter.getItem(position));
    }

    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int id, Bundle args) {
        //Create a new loader for the given URL
        return new EarthquakeLoader(this, QUERY_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> result) {
        //Update the UI with the result
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (result != null && !result.isEmpty()) {
            updateUI(result);
        } else {
            txt_emptyView.setText("No earthquakes found");
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        //Loader reset, so we can clear out our existing data.
        adapter.clear();
    }
}
