package com.knights.agoras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logo = (TextView)findViewById(R.id.textViewTitle);
        Logo.setText(getIntent().getStringExtra("Info"));

        fillListView();
    }

    public void fillListView()
    {
        try
        {
            FeedItem item1 = new FeedItem();
            item1.Category = "Animals";
            item1.Info = "I need dog food, I live at Aven Gvirol 7";
            item1.Title = "Need food";
            item1.Writer = "Zohar Shefi";
            item1.When = new Date(2016,3,14);

            FeedItem item2 = new FeedItem();
            item2.Category = "School";
            item2.Info = "I'm giving free front end lessons, call me if you need help";
            item2.Title = "Free Tuturing";
            item2.Writer = "Eldad Zipori";
            item2.When = new Date(2016,4,14);

            ArrayList<FeedItem> listFeed = new ArrayList<FeedItem>();
            listFeed.add(item1);
            listFeed.add(item2);

            ListAdapter adapter = new ListAdapter(this,0,listFeed);
            ListView listView = (ListView)findViewById(R.id.listViewMain);
            listView.setAdapter(adapter);

            Context context = getApplicationContext();
            CharSequence text = "Winning!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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

