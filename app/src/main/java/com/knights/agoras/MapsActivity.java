package com.knights.agoras;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG_LatLong = "LatLong";
    private static final String TAG_Radius = "radius";
    private static final String TAG_Name = "name";
    private static final String TAG_Color = "Color";
    private static final String TAG_URL = "ImgUrl";
    private static final String TAG_Marker = "marker";
    private static final Map<Marker, String> markerMap = new HashMap<Marker, String>();

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

        parseMarker();
    }

    public void parseMarker()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Agoras");
        //ParseGeoPoint myPoint = new ParseGeoPoint(32.07,34.7818);
        //query.whereWithinKilometers("LatLong", myPoint, 10);
        query.findInBackground( new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for(ParseObject object:objects) {
                        ParseGeoPoint Point = object.getParseGeoPoint(TAG_LatLong);
                        Double Long = Point.getLongitude();
                        Double Lat = Point.getLatitude();
                        int Radius = Integer.parseInt(object.getNumber(TAG_Radius).toString());
                        String Name = object.getString(TAG_Name);
                        final String Url = object.getString(TAG_URL);
                        int color = Color.parseColor(object.getString(TAG_Color));
                        color = (color & 0x00FFFFFF) | 0x40000000;

                        LatLng mark = new LatLng(Long, Lat);
                        Marker toAdd = mMap.addMarker(new MarkerOptions().position(mark).title(Name));
                        markerMap.put(toAdd,Url);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(mark));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 700, null);
                        CircleOptions circleOptions = new CircleOptions()
                                .center(mark)
                                .fillColor(color)
                                .radius(Radius)
                                .strokeWidth(1);
                        mMap.addCircle(circleOptions);
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                String url = markerMap.get(marker);
                                changeActivity(marker.getTitle(),url);
                            }
                        });
                    }
                }
            }
        });;

    }
    public void changeActivity(String name,String url){
        Intent intent = new Intent(this, GroupActivity.class);
        intent.putExtra("Info",name);
        intent.putExtra("URL",url);
        startActivity(intent);
    }

}
