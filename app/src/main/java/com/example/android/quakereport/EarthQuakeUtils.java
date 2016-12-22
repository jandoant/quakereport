package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

        //Fake JSON-Data
        String jsonResponse = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1482410668000,\"url\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.5.2\",\"count\":14},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":7.8,\"place\":\"27km SSE of Muisne, Ecuador\",\"time\":1460851116980,\"updated\":1480431263327,\"tz\":-300,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20005j32\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20005j32&format=geojson\",\"felt\":190,\"cdi\":9.1,\"mmi\":8.08,\"alert\":\"orange\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":1173,\"net\":\"us\",\"code\":\"20005j32\",\"ids\":\",at00o5r3xd,us20005j32,pt16107050,gcmt20160416235837,\",\"sources\":\",at,us,pt,gcmt,\",\"types\":\",cap,dyfi,finite-fault,general-link,general-text,geoserve,impact-link,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,poster,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":1.44,\"rms\":0.94,\"gap\":15,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.8 - 27km SSE of Muisne, Ecuador\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-79.9218,0.3819,20.59]},\"id\":\"us20005j32\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":7,\"place\":\"1km E of Kumamoto-shi, Japan\",\"time\":1460737506220,\"updated\":1480089830568,\"tz\":540,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20005iis\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20005iis&format=geojson\",\"felt\":62,\"cdi\":8.4,\"mmi\":9.3,\"alert\":\"red\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":2052,\"net\":\"us\",\"code\":\"20005iis\",\"ids\":\",at00o5oo9v,pt16106053,us20005iis,gcmt20160415162506,\",\"sources\":\",at,pt,us,gcmt,\",\"types\":\",cap,dyfi,finite-fault,general-link,general-text,geoserve,impact-link,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,poster,scitech-link,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":0.349,\"rms\":0.85,\"gap\":32,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.0 - 1km E of Kumamoto-shi, Japan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[130.7543,32.7906,10]},\"id\":\"us20005iis\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.9,\"place\":\"75km SE of Mawlaik, Burma\",\"time\":1460555717800,\"updated\":1478816112504,\"tz\":390,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20005hqz\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20005hqz&format=geojson\",\"felt\":289,\"cdi\":5.9,\"mmi\":5.79,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":903,\"net\":\"us\",\"code\":\"20005hqz\",\"ids\":\",us20005hqz,gcmt20160413135517,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,general-text,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":3.001,\"rms\":1.03,\"gap\":14,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.9 - 75km SE of Mawlaik, Burma\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[94.8654,23.0944,136]},\"id\":\"us20005hqz\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.6,\"place\":\"42km WSW of Ashkasham, Afghanistan\",\"time\":1460284138720,\"updated\":1478816107914,\"tz\":270,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20005gsg\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20005gsg&format=geojson\",\"felt\":117,\"cdi\":5.8,\"mmi\":4.4,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":738,\"net\":\"us\",\"code\":\"20005gsg\",\"ids\":\",us20005gsg,gcmt20160410102858,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":0.789,\"rms\":1.03,\"gap\":17,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.6 - 42km WSW of Ashkasham, Afghanistan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[71.1311,36.4725,212]},\"id\":\"us20005gsg\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"124km ENE of Codrington, Barbuda\",\"time\":1458386793230,\"updated\":1478816081786,\"tz\":-240,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20005azy\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20005azy&format=geojson\",\"felt\":80,\"cdi\":5,\"mmi\":3.51,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":594,\"net\":\"us\",\"code\":\"20005azy\",\"ids\":\",us20005azy,gcmt20160319112634,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":1.082,\"rms\":1.16,\"gap\":27,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 124km ENE of Codrington, Barbuda\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-60.7019,17.996,26]},\"id\":\"us20005azy\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":5.1,\"place\":\"31km NW of Fairview, Oklahoma\",\"time\":1455383226290,\"updated\":1478815937393,\"tz\":-360,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20004zy8\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20004zy8&format=geojson\",\"felt\":4043,\"cdi\":6.8,\"mmi\":5.61,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":1080,\"net\":\"us\",\"code\":\"20004zy8\",\"ids\":\",us20004zy8,gcmt20160213170706,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,general-link,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":0.218,\"rms\":1.09,\"gap\":17,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 5.1 - 31km NW of Fairview, Oklahoma\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-98.709,36.4898,8.31]},\"id\":\"us20004zy8\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"36km W of Ovalle, Chile\",\"time\":1455064385340,\"updated\":1478815920844,\"tz\":-180,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20004z5b\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20004z5b&format=geojson\",\"felt\":70,\"cdi\":6.4,\"mmi\":6.7,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":655,\"net\":\"us\",\"code\":\"20004z5b\",\"ids\":\",us20004z5b,gcmt20160210003305,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":0.11,\"rms\":0.81,\"gap\":45,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 36km W of Ovalle, Chile\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-71.5838,-30.5723,29]},\"id\":\"us20004z5b\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.4,\"place\":\"25km SE of Yujing, Taiwan\",\"time\":1454702247380,\"updated\":1479041463018,\"tz\":480,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20004y6h\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20004y6h&format=geojson\",\"felt\":144,\"cdi\":8.2,\"mmi\":7.16,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":768,\"net\":\"us\",\"code\":\"20004y6h\",\"ids\":\",pt16036050,at00o23bfs,us20004y6h,gcmt20160205195727,\",\"sources\":\",pt,at,us,gcmt,\",\"types\":\",cap,dyfi,general-link,general-text,geoserve,impact-link,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,trump-moment-tensor,\",\"nst\":null,\"dmin\":0.362,\"rms\":1.19,\"gap\":14,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.4 - 25km SE of Yujing, Taiwan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[120.6014,22.9375,23]},\"id\":\"us20004y6h\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":5.2,\"place\":\"19km N of Kathmandu, Nepal\",\"time\":1454689211740,\"updated\":1461029599040,\"tz\":345,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us20004y4t\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us20004y4t&format=geojson\",\"felt\":113,\"cdi\":5.3,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":476,\"net\":\"us\",\"code\":\"20004y4t\",\"ids\":\",us20004y4t,\",\"sources\":\",us,\",\"types\":\",cap,dyfi,geoserve,impact-text,nearby-cities,origin,phase-data,tectonic-summary,\",\"nst\":null,\"dmin\":0.166,\"rms\":0.82,\"gap\":44,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 5.2 - 19km N of Kathmandu, Nepal\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[85.3377,27.8782,23.53]},\"id\":\"us20004y4t\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"50km NNE of Al Hoceima, Morocco\",\"time\":1453695722730,\"updated\":1478815788032,\"tz\":0,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us10004gy9\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us10004gy9&format=geojson\",\"felt\":117,\"cdi\":7.2,\"mmi\":5.28,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":695,\"net\":\"us\",\"code\":\"10004gy9\",\"ids\":\",gcmt20160125042203,us10004gy9,gcmt20160125042202,\",\"sources\":\",gcmt,us,gcmt,\",\"types\":\",associate,cap,dyfi,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":2.201,\"rms\":0.92,\"gap\":20,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 50km NNE of Al Hoceima, Morocco\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-3.6818,35.6493,12]},\"id\":\"us10004gy9\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":7.1,\"place\":\"86km E of Old Iliamna, Alaska\",\"time\":1453631430230,\"updated\":1478815782468,\"tz\":-540,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us10004gqp\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us10004gqp&format=geojson\",\"felt\":1816,\"cdi\":7.2,\"mmi\":6.6,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":1496,\"net\":\"us\",\"code\":\"10004gqp\",\"ids\":\",at00o1gd6r,us10004gqp,ak12496371,gcmt20160124103030,\",\"sources\":\",at,us,ak,gcmt,\",\"types\":\",cap,dyfi,finite-fault,general-link,general-text,geoserve,impact-link,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,trump-origin,\",\"nst\":null,\"dmin\":0.72,\"rms\":2.11,\"gap\":19,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.1 - 86km E of Old Iliamna, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-153.4051,59.6363,129]},\"id\":\"us10004gqp\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.7,\"place\":\"52km SE of Shizunai, Japan\",\"time\":1452741933640,\"updated\":1478815700277,\"tz\":540,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us10004ebx\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us10004ebx&format=geojson\",\"felt\":51,\"cdi\":5.8,\"mmi\":6.45,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":720,\"net\":\"us\",\"code\":\"10004ebx\",\"ids\":\",us10004ebx,gcmt20160114032534,pt16014050,at00o0xauk,gcmt20160114032533,\",\"sources\":\",us,gcmt,pt,at,gcmt,\",\"types\":\",associate,cap,dyfi,geoserve,impact-link,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.281,\"rms\":0.98,\"gap\":22,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.7 - 52km SE of Shizunai, Japan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[142.781,41.9723,46]},\"id\":\"us10004ebx\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":5.7,\"place\":\"31km SSE of Jarm, Afghanistan\",\"time\":1452629099710,\"updated\":1478815684208,\"tz\":270,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us10004dtm\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us10004dtm&format=geojson\",\"felt\":84,\"cdi\":4.6,\"mmi\":3.07,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":538,\"net\":\"us\",\"code\":\"10004dtm\",\"ids\":\",gcmt20160112200459,gcmt20160112200500,us10004dtm,\",\"sources\":\",gcmt,gcmt,us,\",\"types\":\",associate,cap,dyfi,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":0.606,\"rms\":1.07,\"gap\":20,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 5.7 - 31km SSE of Jarm, Afghanistan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[70.9503,36.5979,239]},\"id\":\"us10004dtm\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":6.7,\"place\":\"30km W of Imphal, India\",\"time\":1451862322270,\"updated\":1478815621098,\"tz\":330,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us10004b2n\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us10004b2n&format=geojson\",\"felt\":969,\"cdi\":7.6,\"mmi\":6.78,\"alert\":\"orange\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":1736,\"net\":\"us\",\"code\":\"10004b2n\",\"ids\":\",us10004b2n,gcmt20160103230522,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,general-link,general-text,geoserve,impact-text,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":1.794,\"rms\":1.01,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.7 - 30km W of Imphal, India\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[93.6505,24.8036,55]},\"id\":\"us10004b2n\"}],\"bbox\":[-153.4051,-30.5723,8.31,142.781,59.6363,239]}";

        ArrayList<Earthquake> earthquakes = extractEarthquakes(jsonResponse);
        return earthquakes;
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String jsonResponse) {

        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        //Http-Request nicht erfolgreich
        if (jsonResponse == "") {
            return null;
        }

        JSONObject root;

        try {
            root = new JSONObject(jsonResponse);

            JSONArray earthquakesArr = root.getJSONArray("features");
            int anzEarthquakes = earthquakesArr.length();
            Log.d("Anzahl", String.valueOf(anzEarthquakes));

            for (int i = 0; i < anzEarthquakes; i++) {

                //properties of this very Earthquake
                JSONObject properties = earthquakesArr.getJSONObject(i).getJSONObject("properties");

                //get necessary properties
                long time = properties.getLong("time");
                String place = properties.getString("place");
                double magnitude = properties.getDouble("mag");
                String url = properties.getString("url");

                //create Earthquake Object with these properties
                Earthquake earthquake = new Earthquake(time, place, magnitude, url);
                //add this Earthquake Object to List
                earthquakes.add(earthquake);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Der JSON-String ist nicht valide", e);
        }
        return earthquakes;
    }
}
