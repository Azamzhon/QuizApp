package com.azamzhon.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private static SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("Aza", Context.MODE_PRIVATE);
    }

}