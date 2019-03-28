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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.muhammadjon.myproject.App;
import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.comon.IWatcher;
import com.example.muhammadjon.myproject.comon.TextWatcherImpl;
import com.example.muhammadjon.myproject.model.req.SignInRequest;
import com.example.muhammadjon.myproject.model.res.SignInResponse;
import com.example.muhammadjon.myproject.network.ApiService;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IWatcher {
    private TextInputEditText etlogin, etpasswort;
    private AppCompatButton btn_save, btn_sign;
    private ProgressBar pBar;
    private String login, passwort;
    private ApiService service;
    private CompositeDisposable cd = new CompositeDisposable();
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
        etlogin.addTextChangedListener(new TextWatcherImpl(this));
        etpasswort.addTextChangedListener(new TextWatcherImpl(this));
        btn_sign.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        App app = (App) getApplication();
        service = app.getApiService();
        btn_save.setEnabled(false);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_l_btn_save: {
                pBar.setVisibility(View.VISIBLE);
                SignInRequest request = new SignInRequest(login, passwort);
                Single<SignInResponse> single = service.signIn(request);
                single.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<SignInResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                cd.add(d);
                            }

                            @Override
                            public void onSuccess(SignInResponse signInResponse) {
                                int num = preferences.getInt("iss", 0);
                                Toast.makeText(LoginActivity.this, "userId"+signInResponse.getUserId(), Toast.LENGTH_SHORT).show();
                                if (num == signInResponse.getUserId()) {
                                    Intent intent = new Intent(LoginActivity.this, PingcodeActivity.class);
                                    pBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("LoginActivity", "onError"+e.getMessage());
                            }
                        });
            }
            break;
            case R.id.login_l_sing_up_btn: {
                Intent intent = new Intent(LoginActivity.this, RegistrActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cd != null && cd.isDisposed()) {
            cd.dispose();
        }
    }
    @Override
    public void onTextChanged(String text) {
        login = etlogin.getText().toString();
        passwort = etpasswort.getText().toString();
        if (!login.isEmpty() && !passwort.isEmpty()) {
            btn_save.setEnabled(true);
        }
    }
}
