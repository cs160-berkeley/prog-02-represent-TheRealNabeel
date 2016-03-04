package com.example.nabeel.prog02;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;




public class MainActivity extends FragmentActivity {

    public static String catName = "";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            catName = extras.getString("SENATOR_GARY_PETERS");
        }
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        FragmentManager manager = getSupportFragmentManager();
        pager.setAdapter(new WatchAdapter(manager));
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }
        });
    }

        public void handleShakeEvent(int count) {
//            Intent intent = getIntent();
//            Bundle extras = intent.getExtras();
            if (count > 10) {
//                Intent intent = new Intent(this, Shakeandbake.class);
//                startActivity(intent);
//                if (extras != null) {
//                    catName = extras.getString("SENATOR_GARY_PETERS");
//                }
//                shaker(catName);
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("SENATOR_GARY!", "sendtoast");
                Log.d("T", "sendtoast");
//                startService(sendIntent);
            }
            else {
                //Intent intent = new Intent(this, Shakeandbake2.class);
                //startActivity(intent);

//                shaker(catName);
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("SENATOR_GARY!", "sendtoast2");
//                startService(sendIntent);
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            // Add the following line to register the Session Manager Listener onResume
            mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        }

        @Override
        public void onPause() {
            // Add the following line to unregister the Sensor Manager onPause
            mSensorManager.unregisterListener(mShakeDetector);
            super.onPause();
        }

    }
