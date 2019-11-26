package com.example.latest_instagram_cloning;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity {
    private Button login,signup;
    private EditText signupUserName,signupUserPassword,loginUserName,loginUserPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login);

        //start initialize variables
        signupUserName=findViewById(R.id.signupUserName);
        signupUserPassword=findViewById(R.id.signupUserPassword);
        loginUserName=findViewById(R.id.loginUserName);
        loginUserPassword=findViewById(R.id.loginUserPassword);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);

        //ending initialize the variables

        //click listner for signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final ParseUser parseUser=new ParseUser();
                    parseUser.setUsername(signupUserName.getText().toString());
                    parseUser.setPassword(signupUserPassword.getText().toString());
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                FancyToast.makeText(SignUpLogin.this,parseUser.get("username")+" is signed successfully:", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                            else
                            {
                                FancyToast.makeText(SignUpLogin.this, e.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.WARNING, true).show();
                            }
                        }
                    });
            }
        });


        //click litner for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(loginUserName.getText().toString(),
                        loginUserPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null)
                        {
                            FancyToast.makeText(SignUpLogin.this,user.get("username")+" is loged in successfully:", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                        else
                        {
                            FancyToast.makeText(SignUpLogin.this, e.getMessage() + "", FancyToast.LENGTH_LONG, FancyToast.WARNING, true).show();
                        }
                    }
                });
            }
        });

    }
}
