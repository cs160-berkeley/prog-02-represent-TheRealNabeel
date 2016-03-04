package com.example.nabeel.prog02;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText edit1;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        button = (Button) findViewById(R.id.button);
        button5 = (Button) findViewById(R.id.button5);
        edit1 = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                int e1 = Integer.parseInt(edit1.getText().toString());
                if (e1 == 48098) {
                    Intent intent = new Intent(this, Activity2.class);
                    startActivity(intent);
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendIntent.putExtra("SENATOR_GARY_PETERS", "Democrat");
                    startService(sendIntent);
                }
                else if (e1 == 48706) {
                    Intent intent = new Intent(this, Activity2.class);
                    startActivity(intent);
                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendIntent.putExtra("SENATOR_GARY_PETERS", "Democrat2");
                    startService(sendIntent);
                }
                else {
                    edit1.setText("");
                }
                break;
            case R.id.button5:
                Intent intent = new Intent(this, Activity2.class);
                startActivity(intent);
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("SENATOR_GARY_PETERS", "Democrat2");
                startService(sendIntent);
                break;
        }
    }
}
