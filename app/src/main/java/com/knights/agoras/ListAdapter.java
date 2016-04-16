package com.knights.agoras;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<FeedItem> {

    ArrayList<FeedItem> list;
    Context context;

    public ListAdapter(Context context,int resource, ArrayList<FeedItem> feedItems)
    {
        super(context, resource, feedItems);

        this.list = feedItems;
        this.context = context;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.list_view_item, parent, false);
        try {
            TextView title = (TextView) v.findViewById(R.id.textViewTitle);
            title.setText(list.get(position).Title + " - ");
            TextView writer = (TextView) v.findViewById(R.id.textViewWriter);
            writer.setText(list.get(position).Writer);
            TextView info = (TextView) v.findViewById(R.id.textViewInfo);
            info.setText(list.get(position).Info);
            TextView category = (TextView) v.findViewById(R.id.textViewCategory);
            category.setText(list.get(position).Category + " ");
            TextView date = (TextView) v.findViewById(R.id.textViewDate);
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy.MM.dd");
            date.setText(dt1.format(list.get(position).When));
        }
        catch (Exception e)
        {}
        finally {
            return v;
        }

    }
}
