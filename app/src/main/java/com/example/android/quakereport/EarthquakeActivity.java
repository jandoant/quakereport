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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    ListView earthquakeListView;
    ArrayList<Earthquake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        createData();
        setUpListView();
    }

    private void createData() {

        earthquakes = new ArrayList<>();

        earthquakes.add(new Earthquake(new GregorianCalendar(2016, 2, 2), "San Francisco", 557.2));
        earthquakes.add(new Earthquake(new GregorianCalendar(2015, 6, 1), "London", 6.1));
        earthquakes.add(new Earthquake(new GregorianCalendar(2015, 5, 4), "Tokyo", 5.3));
        earthquakes.add(new Earthquake(new GregorianCalendar(2014, 3, 7), "Mexico City", 1.6));
        earthquakes.add(new Earthquake(new GregorianCalendar(2013, 2, 8), "Moscow", 4.2));
        earthquakes.add(new Earthquake(new GregorianCalendar(2012, 6, 9), "Rio de Janeiro", 3.0));
        earthquakes.add(new Earthquake(new GregorianCalendar(2012, 7, 20), "Paris", 1.9));
    }

    private void setUpListView() {

        earthquakeListView = (ListView) findViewById(R.id.list);

        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
        earthquakeListView.setAdapter(adapter);
    }
}
