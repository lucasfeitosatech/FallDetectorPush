package com.sandra.falldetectorpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MqttCallbackExtended {


    private MqttManagerAndroid mqttManagerAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mqttManagerAndroid = new MqttManagerAndroid(this);
        mqttManagerAndroid.getMqttAndroidClient().setCallback(this);
    }

    @OnClick(R.id.toolbar_left_button)
    public void onBackArrowClicked(){
        finish();
    }


    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        Log.w("teste", "Conexao Completada");
        //publishMessage("oi com.sandra.falldetectorpush.App denovo","/teste");
        mqttManagerAndroid.subscribeToTopic("/teste");
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.d("Message Arrived", "messageArrived: ");
        NotificationHelper notificationHelper = new NotificationHelper(this);
        notificationHelper.createNotification("Atenção!!!","Sandra Thais provavelmente sofreu uma queda");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
