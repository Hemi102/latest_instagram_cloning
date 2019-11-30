package com.example.latest_instagram_cloning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class homePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("English (United State)", "Arabic", "Urdu", "Bangali", "Turkish");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {



            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }


        });

    }
}
