package com.example.coursework;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    ImageView instaPhoto;
    Button randomPhotoButton;
    SharedPref sharedPref;
    TextView nameText;
    TextView introText;

    private static final String TAG = "MainActivity";


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
        setContentView(R.layout.activity_main);

        instaPhoto = findViewById(R.id.insta_photo);
        randomPhotoButton = findViewById(R.id.search_button);
        introText = findViewById(R.id.introtext);
        nameText = findViewById(R.id.nameText);

        //get text based on name in Internal storage
        nameText.setText("Hello " + getName());

        //load random phot from internet to display when button clicked
        randomPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Getting random photo for internet");
                Random random = new Random();
                String url = links[random.nextInt(links.length)];
                LoadImage loadImage = new LoadImage(instaPhoto);
                loadImage.execute(url);
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
            case R.id.Products:
                // start products activity
                startActivity(new Intent(this, productActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Contact:
                // start contact activity
                startActivity(new Intent(this, ContactActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Settings:
                // start settings activity
                startActivity(new Intent(this, SettingActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView input) {
            this.imageView = input;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            instaPhoto.setImageBitmap(bitmap);
        }
    }

    public String getName() {
        try {
            Log.i(TAG, "Getting name of user from internal storage");

            FileInputStream fileInputStream = openFileInput("namefile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException");
            e.printStackTrace();
        }
        return "app user";
    }

    //Array of links to photos online
    String[] links = {"https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-9/73352315_550791749064085_" +
            "4095041700857118720_n.jpg?_nc_cat=107&_nc_sid=dd9801&_nc_ohc=L0dUaDixYcQAX8eDlbB&" +
            "_nc_ht=scontent-lht6-1.xx&oh=5ed54cc9e542254b1307a9e9d5112fe7&oe=5E82044C", "http" +
            "s://static.wixstatic.com/media/210006_ff8d0d263d6d4680b8489ce2da03d4d1~mv2.jpg/v1" +
            "/fill/w_375,h_500,al_c,q_85,usm_0.66_1.00_0.01/210006_ff8d0d263d6d4680b8489ce2da0" +
            "3d4d1~mv2.jpg", "https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/87497309_659619841" +
            "514608_2720389473052393472_o.jpg?_nc_cat=109&_nc_sid=9e2e56&_nc_ohc=FgD60pPcolMAX" +
            "9g_oho&_nc_ht=scontent-lhr8-1.xx&oh=a021ee878c10ed20658ac66c1d191ebe&oe=5E94BE79"
            , "https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/87614085_655926835217242_12510720" +
            "77234962432_o.jpg?_nc_cat=102&_nc_sid=2d5d41&_nc_ohc=81ICNGoBaDAAX8374p1&_nc_ht=s" +
            "content-lhr8-1.xx&oh=8947205f693b713716c35d747f307a84&oe=5E956E82", "https://scon" +
            "tent-lht6-1.xx.fbcdn.net/v/t1.0-9/87309669_653825082094084_4869482625686831104_o." +
            "jpg?_nc_cat=103&_nc_sid=9e2e56&_nc_ohc=F6D_QveoZMQAX8oMlhb&_nc_ht=scontent-lht6-1" +
            ".xx&oh=da57c4ae65401716e89072e1f038450f&oe=5E963DF0", "https://scontent-lhr8-1.xx" +
            ".fbcdn.net/v/t1.0-9/86384571_646625132814079_7062669321331277824_o.jpg?_nc_cat=11" +
            "1&_nc_sid=9e2e56&_nc_ohc=p-y2i_MSCmwAX-rn06M&_nc_ht=scontent-lhr8-1.xx&oh=9dd760d" +
            "d2fc88fcceaab75960b999e4c&oe=5E8264BF", "https://scontent-lhr8-1.xx.fbcdn.net/v/" +
            ".0-9/86493809_646625179480741_6870191671926063104_o.jpg?_nc_cat=110&_nc_sid=9e2e5" +
            "6&_nc_ohc=7ZX7jImuOMUAX-HI8LI&_nc_ht=scontent-lhr8-1.xx&oh=" +
            "&oe=5E964B10", "https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-9/81161293_1935257409" +
            "954288_5942499592351252480_o.jpg?_nc_cat=105&_nc_sid=9e2e56&_nc_ohc=m9QI-jNAXiUAX" +
            "-I0foj&_nc_ht=scontent-lht6-1.xx&oh=ad6d67f8a0aae549ead3cd90473d723c&oe=5E7FFF37"
            , "https://scontent-lhr8-1.xx.fbcdn.net/v/t1.0-9/73322196_10157738852275600_635020" +
            "3352948670464_n.jpg?_nc_cat=110&_nc_sid=8024bb&_nc_ohc=OREubqBg3n0AX-4NyyL&_nc_ht" +
            "=scontent-lhr8-1.xx&oh=36340b540d0f692847790dab6eaa0595&oe=5E929904"};
}
