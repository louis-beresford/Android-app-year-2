package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class productActivity extends AppCompatActivity {

    private TieListAdapter mAdapter;
    SharedPref sharedPref;

    private static final String TAG = "ProductActivity";

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
        setContentView(R.layout.activity_product);

        //RECYCLER SET
        Log.i(TAG, "Setting recycler view");
        RecyclerView mtiesList = findViewById(R.id.rv_ties);
        mtiesList.setLayoutManager(new LinearLayoutManager(this));

        mtiesList.setHasFixedSize(true);

        //ADAPTER SET
        Log.i(TAG, "Setting adapter");
        mAdapter = new TieListAdapter(this, getData());
        mtiesList.setAdapter(mAdapter);
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
            case R.id.Contact:
                startActivity(new Intent(this, ContactActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            case R.id.Settings:
                startActivity(new Intent(this, SettingActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Fill data
    private ArrayList<Ties> getData() {
        ArrayList<Ties> ties = new ArrayList<>();
        ties.clear();

        //FILL
        Ties t = new Ties();
        t.setName("silver blue dot - three hearts silk tie");
        t.setDescription("Three small embroidered hearts with vibrant pink embroidery contrast be" +
                "autifully with the soft silver tones of the silk. The silk is predominantly sil" +
                "ver with small navy woven dots. The tie is finished with a contrasting tipping.");
        t.setImage(R.drawable.silver_blue_dot);
        t.setPrice("£95");
        ties.add(t);

        t = new Ties();
        t.setName("pale blue - three daisy silk tie");
        t.setDescription("A gorgeous silk tie in a beautiful soft pale blue with tones of silver." +
                " Three vertical daisies in silver organza with a deep pink embroidered centre si" +
                "t neatly at the top of the tie. The tie is finished with a contrasting silk tipp" +
                "ing.");
        t.setImage(R.drawable.grey_daisy);
        t.setPrice("£95");
        ties.add(t);

        t = new Ties();
        t.setName("blush pink dot - three heart silk tie");
        t.setDescription("Three small embroidered hearts with vibrant pink embroidery contrast b" +
                "eautifully with the soft silver tones of the silk. The silk is predominantly si" +
                "lver with small navy woven dots. The tie is finished with a contrasting tipping.");
        t.setImage(R.drawable.pink_heart);
        t.setPrice("£95");
        ties.add(t);

        t = new Ties();
        t.setName("gun metal - three stripe silk tie\n");
        t.setDescription("A beautiful tie in a rich gun metal silk. Three horizontal appliqued v" +
                "elvet stripes in deep pink and mustard gold sit neatly on the silk. The tie is f" +
                "inished with a contrasting silk tipping.");
        t.setImage(R.drawable.grey_stripe);
        t.setPrice("£75");
        ties.add(t);

        t = new Ties();
        t.setName("black - four daisy");
        t.setDescription("A beautiful tie in black repp silk with silver organza embroidered dai" +
                "sies with a soft pink embroidered centre, creating a sophisticated tie with a " +
                "quirky twist. The tie is finished with a contrasting silk tipping.");
        t.setImage(R.drawable.black_daisy);
        t.setPrice("£75");
        ties.add(t);

        t = new Ties();
        t.setName("silver navy - three stripe silk tie");
        t.setDescription("A beautiful silk tie predominantly silver in colour but with soft " +
                "tones of blue and pink. Three horizontal appliqued velvet stripes in blush pink" +
                " sit neatly on the silk. The tie is finished with a contrasting silk tipping.");
        t.setImage(R.drawable.pink_stripe);
        t.setPrice("£95");
        ties.add(t);

        t = new Ties();
        t.setName("navy - three squares silk tie");
        t.setDescription("Three embroidered abstract organza squares with teal and russet red " +
                "embroidery thread sit neatly on a rich midnight blue silk. The silk has a " +
                "beautiful self woven horizontal stripe. The tie is finished with a " +
                "contrasting silk tipping.");
        t.setImage(R.drawable.purple_square);
        t.setPrice("£95");
        ties.add(t);

        t = new Ties();
        t.setName("deep pink - circle heart silk tie");
        t.setDescription("Three black velvet hearts on organza subtly contrast with the tones of" +
                " the silk. The silk is a rich deep pink with subtle tones of pale blue coming " +
                "through in tiny woven dots. The tie is finished off with a contrasting tipping.");
        t.setImage(R.drawable.red_circle);
        t.setPrice("£98");
        ties.add(t);

        t = new Ties();
        t.setName("navy - three stripe silk tie");
        t.setDescription("Three appliqued velvet stripes in blush pink sit on a textured rich " +
                "blue silk with a self woven horizontal stripe. The tie is finished with a" +
                " contrasting silk tipping.");
        t.setImage(R.drawable.purple_line);
        t.setPrice("£95");
        ties.add(t);
        return ties;
    }
}

