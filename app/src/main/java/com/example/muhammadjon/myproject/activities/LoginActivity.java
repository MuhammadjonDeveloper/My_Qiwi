package com.example.muhammadjon.myproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ProgressBar;

import com.example.muhammadjon.myproject.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText etlogin, etpasswort;
    private AppCompatButton btn_save, btn_sign;
    private ProgressBar pBar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pBar = findViewById(R.id.progressBar_login);

        pBar.setVisibility(View.INVISIBLE);
        etlogin = findViewById(R.id.login_l_login);
        etpasswort = findViewById(R.id.login_l_passwort);
        btn_save = findViewById(R.id.login_l_btn_save);
        btn_sign = findViewById(R.id.login_l_sing_up_btn);
        btn_sign.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_l_btn_save: {
                Intent intent = new Intent(LoginActivity.this, PingcodeActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.login_l_sing_up_btn: {
                Intent intent = new Intent(LoginActivity.this, RegistrActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
