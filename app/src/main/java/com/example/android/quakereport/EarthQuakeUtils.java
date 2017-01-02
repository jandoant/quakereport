package com.example.android.quakereport;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import hugo.weaving.DebugLog;

/**
 * Created by Jan on 22.12.2016.
 */
public class EarthQuakeUtils {

    public static final String LOG_TAG = "EarthQuakeUtils";

    /**
     * Create a private constructor because no one should ever create a {@link EarthQuakeUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    public EarthQuakeUtils() {
    }

    public static ArrayList<Earthquake> fetchEarthquakeData(String requestURL) {

        URL url;
        url = createURL(requestURL);

        String jsonResponse;
        //create Json-String from online resource
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            jsonResponse = "";
        }

        if (jsonResponse == "") {
            return null;
        }
        return extractEarthquakesListFromJson(jsonResponse);
    }

    private static String makeHttpRequest(URL url) throws IOException {

        InputStream inputStream = null;
        String jsonResponse = "";

        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10 * 1000);
            urlConnection.setConnectTimeout(15 * 1000);
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Your HttpRequest was not successfull. ERROR CODE: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    @DebugLog
    private static String readInputStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();

        while (line != null) {
            output.append(line);
            line = bufferedReader.readLine();
        }

        return output.toString();
    }

    private static URL createURL(String requestURL) {
        URL url;
        try {
            url = new URL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        return url;
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakesListFromJson(String jsonString) {

        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        //kein JSON-String vorhanden -> leere Liste
        if (jsonString == "") {
            return null;
        }

        //JSON_String vorhanden --> create Objects, falls String valide
        JSONObject root;

        try {//falls JSON-String valide

            //ROOT-Object extrahieren
            root = new JSONObject(jsonString);
            //Array mit Earthquake-Objekten extrahieren
            JSONArray earthquakesArr = root.getJSONArray("features");
            int anzEarthquakes = earthquakesArr.length();
            //Java Earthquake-Objekte erzeugen und der Liste hinzufügen
            for (int i = 0; i < anzEarthquakes; i++) {
                //Properties-Object of this very Earthquake
                JSONObject properties = earthquakesArr.getJSONObject(i).getJSONObject("properties");
                //extract necessary property-values
                Earthquake earthquake = createEarthquakeFromProperties(properties);
                //add this Earthquake Object to List
                earthquakes.add(earthquake);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Der JSON-String ist nicht valide", e);
        }
        return earthquakes;
    }

    @NonNull
    private static Earthquake createEarthquakeFromProperties(JSONObject properties) throws JSONException {

        //extract necessary propertiy-values
        long time = properties.getLong("time");
        String place = properties.getString("place");
        double magnitude = properties.getDouble("mag");
        String url = properties.getString("url");

        //create Earthquake Object with these property-values
        return new Earthquake(time, place, magnitude, url);
    }
}
