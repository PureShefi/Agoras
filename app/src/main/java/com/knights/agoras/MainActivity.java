package com.knights.agoras;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView Logo;
    private static final String TAG_WRITER = "OwnerID";
    private static final String TAG_HEADER = "Header";
    private static final String TAG_BODY = "Text";
    private static final String TAG_CAT = "Category";
    private static final String TAG_DATE = "createdAt";
    private static ArrayList<FeedItem> listFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFeed = new ArrayList<>();

        Logo = (TextView)findViewById(R.id.textViewTitle);
        Logo.setText(getIntent().getStringExtra("Info"));

        fillList();
    }

    public void fillList()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("AgoraFeed");
        query.findInBackground( new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for(ParseObject object:objects) {
                        String writer = object.get(TAG_WRITER).toString();
                        String header = object.get(TAG_HEADER).toString();
                        String text = object.get(TAG_BODY).toString();
                        String category = object.get(TAG_CAT).toString();
                        Date postDate = object.getDate(TAG_DATE);

                        FeedItem item = new FeedItem();
                        item.Category = category;
                        item.Info = text;
                        item.Title = header;
                        item.Writer = writer;
                        item.When = postDate;
                        listFeed.add(item);
                    }
                    fillListView();
                }
            }
        });
    }

    public void fillListView()
    {
        try
        {
            ListAdapter adapter = new ListAdapter(this,0,listFeed);
            ListView listView = (ListView)findViewById(R.id.listViewMain);
            listView.setAdapter(adapter);

            Context context = getApplicationContext();
        }
        catch (Exception e)
        {
            Context context = getApplicationContext();
            CharSequence text = "Error!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

