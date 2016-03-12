package com.example.nabeel.prog02;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by Nabeel on 3/1/2016.
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String FRED_FEED = "/Democrat";
    private static final String LEXY_FEED = "/Democrat2";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)
        if( messageEvent.getPath().equalsIgnoreCase( FRED_FEED ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Intent intent2 = new Intent(this, WatchAdapter.class);
            //intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("SENATOR_GARY_PETERS", "Democrat");
            //intent2.putExtra("Senator Gary Peters", "Democrat");

            //Log.d("T", "about to start watch MainActivity SENATOR_GARY_PETERS: Democrat");
            startActivity(intent);
//        } else if (messageEvent.getPath().equalsIgnoreCase( LEXY_FEED )) {
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            Intent intent = new Intent(this, MainActivity.class );
//            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
//            //Intent intent2 = new Intent(this, WatchAdapter.class );
//            //intent2.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
//
//
//
//            //you need to add this flag since you're starting a new activity from a service
//            intent.putExtra("SENATOR_GARY_PETERS", "Democrat2");
//            //intent2.putExtra("Senator Gary Peters", "Democrat2");
//            Log.d("T", "about to start watch MainActivity with SENATOR_GARY_PETERS: Democrat2");
//            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}