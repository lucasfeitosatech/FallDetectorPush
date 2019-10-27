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


//Classe App visivel para todas as classes da aplicação
public class App extends Application {

    public static App instance;
    //Variavel para acessar o shared preferences que irá armazenar o nome de usuário
    private SharedPreferences sharedPreferences;
    //Variavel para a acessar o repositorio do Realm o qual armazena as notificacoes
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
