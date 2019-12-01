package com.example.latest_instagram_cloning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class sign_up extends AppCompatActivity implements View.OnClickListener {

    private Button saveData;
    private EditText name,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.signupUsername);
        email=findViewById(R.id.signupEmail);
        password=findViewById(R.id.signupPassword);
        saveData=findViewById(R.id.signupButton);

        saveData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signupButton:
                final ParseUser parseUser=new ParseUser();
                parseUser.setUsername(name.getText().toString());
                parseUser.setEmail(email.getText().toString());
                parseUser.setPassword(password.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            FancyToast.makeText(sign_up.this,parseUser.get("username")+" signed in:",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else
                        {
                            FancyToast.makeText(sign_up.this,e.getMessage()+" ",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

                break;

        }

    }
}
