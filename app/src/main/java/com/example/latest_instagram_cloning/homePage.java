package com.example.latest_instagram_cloning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class homePage extends AppCompatActivity implements View.OnClickListener {

    private TextView signupText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //signing up
        signupText=findViewById(R.id.signupText);
        signupText.setOnClickListener(this);
        //login button
        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null)
        {
            ParseUser.getCurrentUser().logOut();

        }

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.language);
        spinner.setItems("English (United State)", "Arabic", "Urdu", "Bangali", "Turkish");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {



            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }


        });

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.signupText:
                Intent intent=new Intent(homePage.this,sign_up.class);
                startActivity(intent);

                break;

            case R.id.loginButton:
                EditText username,password;
                username=findViewById(R.id.loginUsername);
                password=findViewById(R.id.loginPassword);
                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null)
                        {
                            FancyToast.makeText(homePage.this,user.get("username")+" logged in:",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }
                        else
                        {
                            FancyToast.makeText(homePage.this,e.getMessage()+" ",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });


                break;
        }

    }
}
