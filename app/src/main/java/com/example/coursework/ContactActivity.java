package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {
    private EditText mSubject;
    private EditText mContent;
    SharedPref sharedPref;

    private static final String TAG = "ContactActivity";


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
        setContentView(R.layout.activity_contact);

        mSubject = findViewById(R.id.emailSubject);
        mContent = findViewById(R.id.emailContent);

        Button buttonSend = findViewById(R.id.buttonSendEmail);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    //used to send email intent
    private void send() {
        Log.i(TAG, "Preparing email");
        String subject = mSubject.getText().toString();
        String content = mContent.getText().toString();
        String[] recipient = {"ties@harger.co.uk"};

        //add inputs to intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);

        intent.setType("message/rfrc22");
        startActivity(Intent.createChooser(intent, null));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Perform action on click
        Context context = ContactActivity.this;
        switch (item.getItemId()) {
            case R.id.home:
                // Start the SecondActivity
                startActivity(new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Products:
                // Start the SecondActivity
                startActivity(new Intent(this, productActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Settings:
                // Start the SecondActivity
                startActivity(new Intent(this, SettingActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
