package com.example.coursework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TieActivity extends AppCompatActivity {
    SharedPref sharedPref;
    private static final String TAG = "TieActivity";

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
        setContentView(R.layout.activity_tie);


        TextView nameTxt = findViewById(R.id.tieName);
        ImageView img1 = findViewById(R.id.tieImage1);
        ImageView img2 = findViewById(R.id.tieImage2);
        ImageView img3 = findViewById(R.id.tieImage3);
        TextView priceTxt = findViewById(R.id.tiePrice);
        TextView descTxt = findViewById(R.id.tieDescription);

        //getIncomingIntent();
        Bundle bundle = getIntent().getExtras();

        //get intent variables and assign to correct elements on the screen
        if (bundle != null) {
            Log.i(TAG, "Assigning variables from intent");
            String name = bundle.getString("tie_name");
            String desc = bundle.getString("tie_desc");
            String price = bundle.getString("tie_price");
            String uriString = bundle.getString("tie_image");
            Uri imgRef = Uri.parse(uriString);
            Uri imgRef2 = Uri.parse(uriString + "2");
            Uri imgRef3 = Uri.parse(uriString + "3");
            img1.setImageURI(imgRef);
            img2.setImageURI(imgRef2);
            img3.setImageURI(imgRef3);
            nameTxt.setText(name);
            priceTxt.setText(price);
            descTxt.setText(desc);
        }
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
            case R.id.Settings:
                startActivity(new Intent(this, SettingActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            case R.id.Contact:
                startActivity(new Intent(this, ContactActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}

