package com.example.nabeel.prog02;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "lW6vIesCfhhG1txhC5fvXcpyB";
    private static final String TWITTER_SECRET = "VjWBAtfsJ2bzEO1F0mbA9ARlIcbee9Wz3rPfCc0GWUnVNgsC2G";

    Button button;
    EditText edit1;
    Button button5;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;
    //public final static String EXTRA_MESSAGE = "com.example.Nabeel.prog02.MESSAGE";
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    public String API_KEY = "26f60fd05ec94c1485df79107109437c";
    //public final String apiurl = "congress.api.sunlightfoundation.com/legislators/locate?zip=" + edit1.getText().toString() + "&apikey=26f60fd05ec94c1485df79107109437c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.content_main);
        button = (Button) findViewById(R.id.button);
        button5 = (Button) findViewById(R.id.button5);
        edit1 = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);
        button5.setOnClickListener(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("election-county-2012.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //int e1 = Integer.parseInt(edit1.getText().toString());
                String apiurl = "http://congress.api.sunlightfoundation.com/legislators/locate?zip=" + edit1.getText().toString() + "&apikey=" + API_KEY;
                String apimapurl = "https://maps.googleapis.com/maps/api/geocode/json?zip=" + edit1.getText().toString() + "&key=" + API_KEY;
                new RetrieveFeedTask().execute(apiurl, apimapurl);
                break;
            case R.id.button5:
                String apiurl2 = "http://congress.api.sunlightfoundation.com/legislators/locate?latitude=" + mLatitudeText + "&longitude=" + mLongitudeText + "&apikey=" + API_KEY;
                String apimapurl2 = "https://maps.googleapis.com/maps/api/geocode/json?zip=" + edit1.getText().toString() + "&key=" + API_KEY;
                new RetrieveFeedTask().execute(apiurl2, apimapurl2);
//                Intent intent = new Intent(this, Activity2.class);
//
//                startActivity(intent);
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("SENATOR_GARY_PETERS", "Democrat2");
//                startService(sendIntent);
                break;
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText = (String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText = (String.valueOf(mLastLocation.getLongitude()));
        }
    }

    private class RetrieveFeedTask extends AsyncTask<String, Void, JSONObject> {
        private Exception exception;
        protected JSONObject doInBackground(String... urls) {
            // Do some validation here
            JSONObject[] whole  = new JSONObject[5];
            try {
                    URL url = new URL(urls[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        return new JSONObject(stringBuilder.toString());
                    } finally {
                        urlConnection.disconnect();
                    }

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(JSONObject response) {
            if (response == null) {
                //response = "THERE WAS AN ERROR";
            }
            String data[] = new String[5];
            String data2[] = new String[5];
            String data3[] = new String[5];
            String data4[] = new String[5];
            String data5[] = new String[5];
            String data6[] = new String[5];
            String data7[] = new String[5];
            String data8[] = new String[5];
            String data9[] = new String[5];
            int index = 0;

            //progressBar.setVisibility(View.GONE);
            //Log.i("INFO", response);
            try {
                JSONArray task = response.getJSONArray("results");
                for (int i = 0; i < task.length(); i++) {
                    index = i;
                    JSONObject current = task.getJSONObject(i);
                    data[i] = current.getString("first_name");
                    data2[i] = current.getString("last_name");
                    data3[i] = current.getString("oc_email");
                    //data4[i] = current.getString("bio_guide_id");
                    data5[i] = current.getString("party");
                    if (data5[i].equals("R")) {
                        data5[i] = "Republican";
                    }
                    else {
                        data5[i] = "Democrat";
                    }
                    data6[i] = current.getString("term_end");
                    data7[i] = current.getString("website");
                    if (current.getString("title").equals("Rep")) {
                        data8[i] = "Congressman ";
                    }
                    else {
                        data8[i] = "Senator ";
                    }
                    data9[i] = current.getString("twitter_id");
                }
            } catch (Exception e) {

            }
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("NAME", data8[0] + data[0] + " " + data2[0]);
            intent.putExtra("NAME2", data8[1] + data[1] + " " + data2[1]);
            intent.putExtra("NAME3", data8[2] + data[2] + " " + data2[2]);
            if (index == 3) {
                intent.putExtra("NAME4", data8[3] + data[3] + " " + data2[3]);
                intent.putExtra("EMAIL4", data3[3]);
                intent.putExtra("PARTY4", data5[3]);
                intent.putExtra("WEBSITE4", data7[3]);
                intent.putExtra("TWITTER4", data9[3]);
            }
            intent.putExtra("EMAIL", data3[0]);
            intent.putExtra("EMAIL2", data3[1]);
            intent.putExtra("EMAIL3", data3[2]);
            intent.putExtra("PARTY", data5[0]);
            intent.putExtra("PARTY2", data5[1]);
            intent.putExtra("PARTY3", data5[2]);
            intent.putExtra("WEBSITE", data7[0]);
            intent.putExtra("WEBSITE2", data7[1]);
            intent.putExtra("WEBSITE3", data7[2]);
            intent.putExtra("END_DATE", data6[0]);
            intent.putExtra("END_DATE2", data6[1]);
            intent.putExtra("END_DATE3", data6[2]);
            intent.putExtra("TWITTER", data9[0]);
            intent.putExtra("TWITTER2", data9[1]);
            intent.putExtra("TWITTER3", data9[2]);

            startActivity(intent);

            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("SENATOR_GARY_PETERS", "Democrat");
            startService(sendIntent);
        }
    }

}
