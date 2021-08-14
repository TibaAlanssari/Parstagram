package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;

//ParseApplication is a subclass of the root application from android.app.application
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IEjZz3JeMruZLVoMjfoVa1mCcseNrnpSAmx13Gsg")
                .clientKey("uPOksb8nZtyKOsT2qQgl8PkfGJknw7fgSEPddZmQ")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
