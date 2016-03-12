package com.example.nabeel.prog02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Nabeel on 3/2/2016.
 */
public class WatchAdapter extends FragmentPagerAdapter {
    final int SCREEN_COUNT = 4;

    public WatchAdapter(FragmentManager fm) {
        super (fm);
    }


    @Override
    public Fragment getItem(int i) {
//        Intent intent = getIntent();
//
//
//        Bundle extras = intent.getExtras();
//        String catName = "";
//        if (extras != null) {
//            catName = extras.getString("Senator Gary Peters");
//        }
        switch (i) {

            case 0:
                //Log.d("T", "I came here");
                return new Screen4();
            case 1:
                return new Screen2();
            case 2:
                return new Screen3();
            case 3:
                if (MainActivity.catName.equals("Democrat")) {
                    return new Shakeandbake();
                }
                else {
                    return new Shakeandbake2();
                }
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return SCREEN_COUNT;
    }
}
