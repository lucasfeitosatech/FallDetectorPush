package com.sandra.falldetectorpush.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.sandra.falldetectorpush.App;
import com.sandra.falldetectorpush.R;
import com.sandra.falldetectorpush.adapter.NotificationAdpter;
import com.sandra.falldetectorpush.model.Notification;
import com.sandra.falldetectorpush.util.MqttManagerAndroid;
import com.sandra.falldetectorpush.util.NotificationHelper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MqttCallbackExtended {


    @BindView(R.id.rv_notification)
    RecyclerView notificationList;
    ArrayList<Notification> notifications = new ArrayList<>();


    private MqttManagerAndroid mqttManagerAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configRecyclerView();
        getNotifications();


        mqttManagerAndroid = new MqttManagerAndroid(this);
        mqttManagerAndroid.getMqttAndroidClient().setCallback(this);

    }


    public void configRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        notificationList.setLayoutManager(layoutManager);
        notificationList.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    //Metodo que configura um novo adpter com os dados das notificacoes
    public void setupAdpter(){

        NotificationAdpter adpter = new NotificationAdpter(notifications);
        notificationList.setAdapter(adpter);
        notificationList.requestLayout();

    }

    //Método que recupera as notificacoes do banco e inicia o adpter
    public void getNotifications(){

        Notification[] notifications = App.getInstance().getNotificationRepository().getAllNotifications();
        this.notifications = new ArrayList<>(Arrays.asList(notifications));
        setupAdpter();

    }

    //Método responsavel por tocar um som de alerta


    @OnClick(R.id.toolbar_left_button)
    public void onBackArrowClicked(){
        finish();
    }


    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        Log.w("teste", "Conexao Completada");
        //Recupera o usuário salvo no shared preferences
        String username = PreferenceManager.getDefaultSharedPreferences(this).getString("username", null);
        //Sandra Thais Sandra-Thais
        String topic = "/" + username.replace(" ","-");
        //Subscribe no topico do MQTT
        mqttManagerAndroid.subscribeToTopic(topic);
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.d("Message Arrived", "messageArrived: ");

        //Obtem do sistema a data de hoje
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String today = dateFormat.format(date);

        //Cria uma nova notificacao e salva no banco de dados
        Notification n = new Notification(message.toString(),today);
        App.getInstance().getNotificationRepository().saveNotification(n);
        notifications.add(n);
        //Atualiza a lista
        setupAdpter();

        App.getInstance().playSound();

        //Cria a notificacao
        String username = PreferenceManager.getDefaultSharedPreferences(this).getString("username", null);
        NotificationHelper notificationHelper = new NotificationHelper(this);
        notificationHelper.createNotification("Atenção!!!",username + " provavelmente sofreu uma queda");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
