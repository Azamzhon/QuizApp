package com.azamzhon.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.azamzhon.R;
import com.azamzhon.ui.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long delayTime = 1000;
    private final Handler handler = new Handler();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.context = SplashScreenActivity.this;
        handler.postDelayed(postTask, delayTime);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(postTask);
        super.onDestroy();
    }

    Runnable postTask = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(context, MainActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }
    };

}