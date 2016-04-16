package com.knights.agoras;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG_Long = "Long";
    private static final String TAG_Lat = "Lat";
    private static final String TAG_Radius = "radius";
    private static final String TAG_Name = "name";
    private static final String TAG_Color = "Color";
    private static final String TAG_Marker = "marker";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

    }

    public void loginClick(View view) {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }

    public void parseMarker() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Agoras");
        query.findInBackground( new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    ParseObject object = objects.get(0);
                    Float Long = Float.parseFloat(object.getNumber(TAG_Long).toString());
                    Float Lat = Float.parseFloat(object.getNumber(TAG_Lat).toString());
                    int Radius = Integer.parseInt(object.getNumber(TAG_Radius).toString());
                    String Name = object.getString(TAG_Name);

                    Toast toast = Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        ;
    }
}
