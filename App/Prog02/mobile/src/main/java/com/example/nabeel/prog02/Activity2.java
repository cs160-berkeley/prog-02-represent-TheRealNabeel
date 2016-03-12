package com.example.nabeel.prog02;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Nabeel on 3/1/2016.
 */
public class Activity2 extends MainActivity {
    Button button2;
    Button button3;
    Button button4;
    TextView textview4;
    TextView textview5;
    TextView textview6;
    TextView textview7;
    TextView textview9;
    TextView textview10;
    TextView textview11;
    TextView textview12;
    TextView textview14;
    TextView textview15;
    TextView textview16;
    TextView textview17;
    TextView textview19;
    TextView textview20;
    TextView textview21;
    TextView textview22;
    ImageView imageview2;
    ImageView imageview3;
    ImageView imageview4;
    ImageView imageview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        textview4 = (TextView) findViewById(R.id.textView4);
        textview5 = (TextView) findViewById(R.id.textView5);
        textview6 = (TextView) findViewById(R.id.textView6);
        textview7 = (TextView) findViewById(R.id.textView7);
        textview9 = (TextView) findViewById(R.id.textView9);
        textview10 = (TextView) findViewById(R.id.textView10);
        textview11 = (TextView) findViewById(R.id.textView11);
        textview12 = (TextView) findViewById(R.id.textView12);
        textview14 = (TextView) findViewById(R.id.textView14);
        textview15 = (TextView) findViewById(R.id.textView15);
        textview16 = (TextView) findViewById(R.id.textView16);
        textview17 = (TextView) findViewById(R.id.textView17);
        textview19 = (TextView) findViewById(R.id.textView19);
        textview20 = (TextView) findViewById(R.id.textView20);
        textview21 = (TextView) findViewById(R.id.textView21);
        textview22 = (TextView) findViewById(R.id.textView22);

        imageview2 = (ImageView) findViewById(R.id.imageView2);
        imageview3 = (ImageView) findViewById(R.id.imageView3);
        imageview4 = (ImageView) findViewById(R.id.imageView4);
        imageview5 = (ImageView) findViewById(R.id.imageView5);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        String name = getIntent().getStringExtra("NAME");
        String name2 = getIntent().getStringExtra("NAME2");
        String name3 = getIntent().getExtras().getString("NAME3");
        String name4 = getIntent().getExtras().getString("NAME4");
        String party = getIntent().getStringExtra("PARTY");
        String party2 = getIntent().getExtras().getString("PARTY2");
        String party3 = getIntent().getExtras().getString("PARTY3");
        String party4 = getIntent().getExtras().getString("PARTY4");
        String email = getIntent().getExtras().getString("EMAIL");
        String email2 = getIntent().getExtras().getString("EMAIL2");
        String email3 = getIntent().getExtras().getString("EMAIL3");
        String email4 = getIntent().getExtras().getString("EMAIL4");
        String website = getIntent().getExtras().getString("WEBSITE");
        String website2 = getIntent().getExtras().getString("WEBSITE2");
        String website3 = getIntent().getExtras().getString("WEBSITE3");
        String website4 = getIntent().getExtras().getString("WEBSITE4");
        String image = getIntent().getStringExtra("TWITTER");
        String image2 = getIntent().getStringExtra("TWITTER2");
        String image3 = getIntent().getStringExtra("TWITTER3");
        String image4 = getIntent().getStringExtra("TWITTER4");

        //Log.d("myTag", message);
        textview4.setText(name);
        textview5.setText(party);
        textview6.setText(email);
        textview7.setText(website);
        textview9.setText(name2);
        textview10.setText(party2);
        textview11.setText(email2);
        textview12.setText(website2);
        textview14.setText(name3);
        textview15.setText(party3);
        textview16.setText(email3);
        textview17.setText(website3);
        textview19.setText(name4);
        textview20.setText(party4);
        textview21.setText(email4);
        textview22.setText(website4);
        Picasso.with(Activity2.this).load("https://twitter.com/" + image + "/profile_image?size=original").into(imageview2);
        Picasso.with(Activity2.this).load("https://twitter.com/" + image2 + "/profile_image?size=original").into(imageview3);
        Picasso.with(Activity2.this).load("https://twitter.com/" + image3 + "/profile_image?size=original").into(imageview4);
        if (image4 != null) {
            Picasso.with(Activity2.this).load("https://twitter.com/" + image4 + "/profile_image?size=original").into(imageview5);
        }

    }

    @Override
    public void onClick(View v) {
        String name = getIntent().getExtras().getString("NAME");
        String name2 = getIntent().getStringExtra("NAME2");
        String name3 = getIntent().getExtras().getString("NAME3");
        String date = getIntent().getExtras().getString("END_DATE");
        String date2 = getIntent().getExtras().getString("END_DATE2");
        String date3 = getIntent().getExtras().getString("END_DATE3");
        String party = getIntent().getStringExtra("PARTY");
        String party2 = getIntent().getExtras().getString("PARTY2");
        String party3 = getIntent().getExtras().getString("PARTY3");
        String image = getIntent().getStringExtra("TWITTER");
        String image2 = getIntent().getStringExtra("TWITTER2");
        String image3 = getIntent().getStringExtra("TWITTER3");
        switch (v.getId()) {
            case R.id.button2:
                Intent intent = new Intent(this, Activity3.class);
                intent.putExtra("DATE", date);
                intent.putExtra("PARTY", party);
                intent.putExtra("NAME", name);
                intent.putExtra("TWITTER", image);
                startActivity(intent);
                break;
            case R.id.button3:
                Intent intent1 = new Intent(this, Activity4.class);
                intent1.putExtra("DATE2", date2);
                intent1.putExtra("PARTY2", party2);
                intent1.putExtra("NAME2", name2);
                intent1.putExtra("TWITTER2", image2);
                startActivity(intent1);
                break;
            case R.id.button4:
                Intent intent2 = new Intent(this, Activity5.class);
                intent2.putExtra("DATE3", date3);
                intent2.putExtra("PARTY3", party3);
                intent2.putExtra("NAME3", name3);
                intent2.putExtra("TWITTER3", image3);
                startActivity(intent2);
        }
    }
}
