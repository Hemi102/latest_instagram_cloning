package com.example.latest_instagram_cloning;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kiepV3u756WwYzZ8ORNPThc40SesdUW5qrWbAPYU")
                // if defined
                .clientKey("Vu6oP62ZHe69NrWYrn85abYec344mSIQdVIfFqDs")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
