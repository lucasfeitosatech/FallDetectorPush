package com.sandra.falldetectorpush;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.sandra.falldetectorpush.repository.NotificationRepository;

import io.realm.Realm;


//Classe App visivel para todas as classes da aplicação
public class App extends Application {

    public static App instance;
    private MediaPlayer mp;
    //Variavel para acessar o shared preferences que irá armazenar o nome de usuário
    private SharedPreferences sharedPreferences;
    //Variavel para a acessar o repositorio do Realm o qual armazena as notificacoes
    private NotificationRepository notificationRepository;



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.sirene);
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

    public MediaPlayer getMp() {
        return mp;
    }

    public void playSound(){

       try {
        mp = new MediaPlayer();
        mp.setDataSource(getApplicationContext(), Uri.parse("android.resource://com.sandra.falldetectorpush/" + R.raw.sirene));
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.prepare();
        mp.start();
       }catch (Exception e){
           e.printStackTrace();
           mp = MediaPlayer.create(getApplicationContext(), R.raw.sirene);
       }
        //Após iniciar o som cria uma nova thread para ser executada após 10 segundos
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                mp.stop();
            }
        };
        handler.postDelayed(run,60*1000);
    }
}
