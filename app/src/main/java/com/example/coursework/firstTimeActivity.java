package com.example.coursework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;



public class firstTimeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Boolean firstTime;
    EditText textName;
    Button submitButton;
    private static final String TAG = "FirstTimeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime", true);

        textName = findViewById(R.id.firstTimeName);
        submitButton = findViewById(R.id.firstTimeButton);
        //Use shared preferences to check if this page has been displayed before
        if (firstTime) {
            Log.i(TAG, "Setting shared preference");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            firstTime = false;
            editor.putBoolean("firstTime", firstTime);
            editor.apply();

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WriteFile();
                    Intent i = new Intent(firstTimeActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });

        } else {
            Log.i(TAG, "Going straight to main activity");
            Intent i = new Intent(firstTimeActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    // write input name to file in internal storage
    public void WriteFile() {
        try {
            FileOutputStream fileout = openFileOutput("namefile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(textName.getText().toString());
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
