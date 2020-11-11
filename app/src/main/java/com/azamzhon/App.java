package com.azamzhon;

import android.app.Application;

import com.azamzhon.data.local.Prefs;

public class App extends Application {

    private Prefs preferences;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = new Prefs(this);
    }

    public static App getInstance() {
        return instance;
    }

    public Prefs getPreferences() {
        return preferences;
    }
}