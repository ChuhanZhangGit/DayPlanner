package com.example.hourlyplanner;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class PlannerApplication extends Application {
    // Called when the application is starting.
    // Initialize time zone information here for three ten back port.
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);

    }
}
