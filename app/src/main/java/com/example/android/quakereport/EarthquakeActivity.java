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
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<ArrayList<Earthquake>>, SwipeRefreshLayout.OnRefreshListener {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String USGS_REQUEST_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query";
    final String QUERY_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-12-31&minfelt=50&minmagnitude=3";
    ListView listView;
    TextView txt_emptyView;
    EarthquakeAdapter adapter;
    ProgressBar loading_animation;
    SwipeRefreshLayout refreshLayout;

    Loader loader;
    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        initUI();

        if (hasInternetConnection()) {
            //init Loader
            loaderManager = getLoaderManager();
            loader = loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            txt_emptyView.setText("Sorry. Check your Internet Connection");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(EARTHQUAKE_LOADER_ID, null, this);
        loading_animation.setVisibility(View.VISIBLE);
    }

    private boolean hasInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void initUI() {

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        loading_animation = (ProgressBar) findViewById(R.id.loading_animation);
        listView = (ListView) findViewById(R.id.list);
        txt_emptyView = (TextView) findViewById(R.id.empty);

        // Create a new adapter that takes an empty list of earthquakes as data-input
        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setEmptyView(txt_emptyView);
    }

    private void updateUI(ArrayList<Earthquake> data) {
        /*
        If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        data set. This will trigger the ListView to update
        */
        adapter.clear();
        if (data != null && !data.isEmpty()) {

            adapter.addAll(data); //ListView will update automatically
        } else {
            txt_emptyView.setText("No earthquakes found");
        }
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

        SharedPreferences prefFile = PreferenceManager.getDefaultSharedPreferences(this);
        //Values of SharedPreferences
        String min_magnitude = prefFile.getString(getString(R.string.pref_min_magnitude_key), getString(R.string.pref_min_magnitude_default));
        String order_by = prefFile.getString(getString(R.string.pref_order_by_key), getString(R.string.pref_order_by_default));

        Uri baseURI = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseURI.buildUpon();

        //http://earthquake.usgs.gov/fdsnws/event/1/
        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("starttime", "2016-01-01");
        uriBuilder.appendQueryParameter("endtime", "2016-03-10");
        uriBuilder.appendQueryParameter("minfelt", "50");
        uriBuilder.appendQueryParameter("minmagnitude", min_magnitude);
        uriBuilder.appendQueryParameter("orderby", order_by);

        //Create a new loader for the given URL
        return new EarthquakeLoader(this, uriBuilder.toString());
    }

    /**
     * @param loader - the Loader instance that is calling this method
     * @param result - return value of the Loader's loadInBackground()-function
     */
    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> result) {
        //Update the UI with the result from the load in background function in Loader

        loading_animation.setVisibility(View.GONE);
        refreshLayout.setRefreshing(false);
        updateUI(result);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        //Loader reset, so we can clear out our existing data.
        adapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                openSettingsActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(EARTHQUAKE_LOADER_ID, null, this);
    }
}
