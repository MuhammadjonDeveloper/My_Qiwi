package com.example.muhammadjon.myproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.muhammadjon.myproject.App;
import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.model.req.SignInRequest;
import com.example.muhammadjon.myproject.model.res.SignInResponse;
import com.example.muhammadjon.myproject.network.ApiService;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText etLogin;
    private TextInputEditText etpasswort;
    private AppCompatButton buttonsave;
    private ApiService service;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = findViewById(R.id.login_f_login);
        etpasswort = findViewById(R.id.login_f_passwort);
        buttonsave = findViewById(R.id.login_f_btn);
        buttonsave.setOnClickListener(this);
        App app = (App) getApplication();
        service = app.getApiService();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onClick(View view) {
        String login = etLogin.getText().toString();
        String passwort = etpasswort.getText().toString();
        if (!login.isEmpty() && !passwort.isEmpty()) {
            SignInRequest signInRequest = new SignInRequest(login, passwort);
            Single<SignInResponse> sign = service.signIn(signInRequest);
            sign.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SignInResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(SignInResponse signInResponse) {
                            Log.d("MainActivity","Succes");
                            Intent intent = new Intent(RegistrActivity.this, SplashActivity.class);
                            preferences.edit().putBoolean("is_first",false);
                            intent.putExtra("key",passwort);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("MainActivity","Error");
                            Toast.makeText(RegistrActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrActivity.this, SplashActivity.class);
                            preferences.edit().putBoolean("is_first",false);
                            intent.putExtra("key",passwort);
                            startActivity(intent);
                        }
                    });
        }
    }
}
