package com.knights.agoras;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<FeedItem> {

    List<FeedItem> list;
    Context context;

    public ListAdapter(Context context,int resource, ArrayList<FeedItem> feedItems)
    {
        super(context, resource, feedItems);

        this.list = feedItems;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.list_view_item,parent,false);
    }
}
