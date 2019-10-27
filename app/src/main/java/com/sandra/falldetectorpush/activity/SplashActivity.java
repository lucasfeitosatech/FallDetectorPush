package com.sandra.falldetectorpush.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sandra.falldetectorpush.R;
import com.sandra.falldetectorpush.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new  Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();
            }
        };
        handler.postDelayed(runnable,1*1000);
    }
}
