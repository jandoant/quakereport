package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        //Data to populate
        Earthquake earthquake = getItem(position);

        //View to create
        View listItem = convertView;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItem = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView txt_magnitude = (TextView) listItem.findViewById(R.id.txt_magnitude);
        TextView txt_location = (TextView) listItem.findViewById(R.id.txt_location);
        TextView txt_date = (TextView) listItem.findViewById(R.id.txt_date);

        if (earthquake != null) {
            txt_magnitude.setText(String.valueOf(earthquake.getMagnitude()));
            txt_location.setText(earthquake.getPlace());
            txt_date.setText(String.valueOf(earthquake.getDate()));
        }
        return listItem;
    }
}
