package com.example.nabeel.prog02;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Nabeel on 3/1/2016.
 */
public class Activity5 extends MainActivity {
    TextView textview9;
    TextView textview10;
    TextView textview11;
    ImageView imageview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main5);
        textview11 = (TextView) findViewById(R.id.textView11);
        String date = getIntent().getStringExtra("DATE3");
        textview11.setText("Term End Date: " + date);
        textview10 = (TextView) findViewById(R.id.textView10);
        String party = getIntent().getStringExtra("PARTY3");
        textview10.setText(party);
        textview9 = (TextView) findViewById(R.id.textView9);
        String name = getIntent().getStringExtra("NAME3");
        textview9.setText(name);
        imageview2 = (ImageView) findViewById(R.id.imageView2);
        String image = getIntent().getStringExtra("TWITTER3");
        Picasso.with(Activity5.this).load("https://twitter.com/" + image + "/profile_image?size=original").into(imageview2);
    }
}
