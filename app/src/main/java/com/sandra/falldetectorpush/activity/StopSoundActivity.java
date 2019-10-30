package com.sandra.falldetectorpush.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sandra.falldetectorpush.App;

public class StopSoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (App.getInstance().getMp().isPlaying())
            App.getInstance().getMp().stop();
        finish();
    }
}
