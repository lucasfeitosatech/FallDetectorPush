package com.sandra.falldetectorpush.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sandra.falldetectorpush.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        String username = PreferenceManager.getDefaultSharedPreferences(this).getString("username", null);
        if (username!= null){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    @OnClick(R.id.loginButton)
    public void onLoginClicked(){
        if (!username.getText().toString().isEmpty()) {
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("username", username.getText().toString()).apply();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            showToast("Digite um usuário");
        }
    }

    void showToast(String text) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage(text);
        builder.setPositiveButton("OK", null);
        AlertDialog toastDialog = builder.create();
        toastDialog.show();
    }


}
