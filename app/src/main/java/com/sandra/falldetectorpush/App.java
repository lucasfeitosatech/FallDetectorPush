package com.sandra.falldetectorpush;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.sandra.falldetectorpush.repository.NotificationRepository;

import io.realm.Realm;

public class App extends Application {

    public static App instance;
    private SharedPreferences sharedPreferences;
    private NotificationRepository notificationRepository;



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);

        notificationRepository = new NotificationRepository();
        setSharedPreferences(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public static App getInstance() {
        return instance;
    }


    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }
}
