package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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
        TextView txt_distance = (TextView) listItem.findViewById(R.id.txt_distance);
        TextView txt_city = (TextView) listItem.findViewById(R.id.txt_city);
        TextView txt_date = (TextView) listItem.findViewById(R.id.txt_date);
        TextView txt_time = (TextView) listItem.findViewById(R.id.txt_time);
        if (earthquake != null) {
            txt_magnitude.setText(String.valueOf(earthquake.getMagnitude()));

            GradientDrawable magnitudeCircle = (GradientDrawable) txt_magnitude.getBackground();

            int magnitudeColor = getMagnitudeColor(earthquake.getMagnitudeAsDouble());

            magnitudeCircle.setColor(magnitudeColor);

            txt_distance.setText(earthquake.getDistance());
            txt_city.setText(earthquake.getCity());
            txt_date.setText(String.valueOf(earthquake.getDate()));
            txt_time.setText(earthquake.getTime());
        }
        return listItem;
    }

    private int getMagnitudeColor(double magnitude) {

        int color = 0;
        int magnitudeFloored = (int) Math.floor(magnitude);

        switch (magnitudeFloored) {
            case 0:
            case 1:
                color = getContext().getResources().getColor(R.color.magnitude1);
                break;
            case 2:
                color = getContext().getResources().getColor(R.color.magnitude2);
                break;
            case 3:
                color = getContext().getResources().getColor(R.color.magnitude3);
                break;
            case 4:
                color = getContext().getResources().getColor(R.color.magnitude4);
                break;
            case 5:
                color = getContext().getResources().getColor(R.color.magnitude5);
                break;
            case 6:
                color = getContext().getResources().getColor(R.color.magnitude6);
                break;
            case 7:
                color = getContext().getResources().getColor(R.color.magnitude7);
                break;
            case 8:
                color = getContext().getResources().getColor(R.color.magnitude8);
                break;
            case 9:
                color = getContext().getResources().getColor(R.color.magnitude9);
                break;
            case 10:
                color = getContext().getResources().getColor(R.color.magnitude10plus);
                break;
        }//Ende switch

        return color;
    }
}
