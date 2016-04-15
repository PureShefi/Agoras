package com.knights.agoras;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG_Long = "long";
    private static final String TAG_Lat = "lat";
    private static final String TAG_Radius = "radius";
    private static final String TAG_Name = "name";
    private static final String TAG_Marker = "marker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng TelAviv = new LatLng(32.0853, 34.7818);
        mMap.addMarker(new MarkerOptions().position(TelAviv).title("Marker in Tel-Aviv"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TelAviv));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 500, null);
        CircleOptions circleOptions = new CircleOptions()
                .center(TelAviv)
                .fillColor(0x4000ff00)
                .radius(400)
                .strokeWidth(1);
        mMap.addCircle(circleOptions);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker mark) {
                changeActivity(mark.getTitle());
            }
        });
    }

    public void changeActivity(String name){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Info",name);
        startActivity(intent);
    }

    public void Markers(String urlString, GoogleMap mMap)
    {
        JSONParser jParser = new JSONParser();
        // Getting JSON from URL
        JSONObject json = jParser.getJSONFromUrl(urlString,"32.0853","34.7818");
        try {
            // Getting JSON Array
            JSONArray markers = null;
            markers = json.getJSONArray(TAG_Marker);

            for(int n = 0; n < markers.length(); n++)
            {
                JSONObject object = markers.getJSONObject(n);
                // Storing  JSON item in a Variable
                Float Long = Float.parseFloat(object.getString(TAG_Long));
                Float Lat = Float.parseFloat(object.getString(TAG_Lat));
                String Radius = object.getString(TAG_Radius);
                String Name = object.getString(TAG_Name);

                LatLng mark = new LatLng(Long, Lat);
                mMap.addMarker(new MarkerOptions().position(mark).title(Name));
                CircleOptions circleOptions = new CircleOptions()
                        .center(mark)
                        .fillColor(0x4000ff00)
                        .radius(400)
                        .strokeWidth(1);
                mMap.addCircle(circleOptions);
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        changeActivity(marker.getTitle());
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
