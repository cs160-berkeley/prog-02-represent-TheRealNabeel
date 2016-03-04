package com.example.nabeel.prog02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Nabeel on 3/2/2016.
 */
public class Screen3 extends Fragment {
    Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.screen3, container, false);
        button2 = (Button) v.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getActivity().getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("SENATOR_GARY", "send_toast2");
                getActivity().startService(sendIntent);
            }
        });
        return v;
    }
}
