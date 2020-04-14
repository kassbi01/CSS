package com.example.css;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CustomCurationAdapter extends ArrayAdapter<String> {

    public TextView singleTV;
    public ImageView unfollow;

    public CustomCurationAdapter(@NonNull Context context, String[] items1) {
        super(context, R.layout.curation_tool_row, items1); //context of application, custom layout file, and array of items
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.curation_tool_row, parent, false);

        String singleItem = getItem(position); //singleItem= item at position in list/array

        String[] sf = singleItem.split("\n", 2); //split string at newLine
        int x = sf[0].length(); // int x = length of first split ^, or username
        int y = singleItem.length(); //y = length of entire string

        String[] xx = singleItem.split(" ", 2);
        int zz = xx[1].length(); // int zz = length of dollar amount

        SpannableString ss = new SpannableString(singleItem); //spannable string allows us to create nice strings
        ss.setSpan(new RelativeSizeSpan(1.1f),0,x,0); //set size of username larger
        ss.setSpan(new ForegroundColorSpan(Color.BLUE),0,x,0); //color username blue
        ss.setSpan(new RelativeSizeSpan(0.8f),x,y-zz,0); // shrink size of url
        ss.setSpan(new RelativeSizeSpan(1.1f),y-zz,y,0 ); // enlarge text of dollar amount

        singleTV = (TextView) customView.findViewById(R.id.userDataCURATIONROW);
        unfollow = (ImageView) customView.findViewById(R.id.steemlogoCURATIONROW);
        unfollow.setImageResource(R.drawable.steemlogo); //set image resource for each item
        singleTV.setText(ss); //set text to spannable string
        return customView;
    }
}
