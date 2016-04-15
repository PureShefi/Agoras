package com.knights.agoras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logo = (TextView)findViewById(R.id.textViewTitle);
        Logo.setText(getIntent().getStringExtra("Info"));
    }

    public void fillListView()
    {
        try
        {
            ListView myListView = (ListView) findViewById(R.id.listViewMain);
        }
        catch (Exception e)
        {
            // this is just an example
        }
    }
}

