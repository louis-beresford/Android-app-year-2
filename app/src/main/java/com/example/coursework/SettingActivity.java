package com.example.coursework;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
    private Switch changeSwitch;
    SharedPref sharedPref;
    private static final String TAG = "SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set theme based on which mode app is in
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        changeSwitch = findViewById(R.id.changeSwitch);
        if (sharedPref.loadNightModeState() == true) {
            changeSwitch.setChecked(true);
        }
        //when switch is clicked change the theme of app using Shared Preferences
        changeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.i(TAG, "Switching to nightmode");
                    sharedPref.setNightModeState(true);
                    //finish all activity's in stack so that when user reopens them they are in
                    //correct theme
                    Log.i(TAG, "restarting activitys");
                    startActivity(new Intent(getApplicationContext(), SettingActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else {
                    Log.i(TAG, "Switching to daymode");
                    sharedPref.setNightModeState(false);
                    Log.i(TAG, "restarting activitys");
                    startActivity(new Intent(getApplicationContext(), SettingActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Products:
                startActivity(new Intent(this, productActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Contact:
                startActivity(new Intent(this, ContactActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
