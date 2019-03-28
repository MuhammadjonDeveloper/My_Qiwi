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
import com.example.muhammadjon.myproject.model.req.SignUpRequest;
import com.example.muhammadjon.myproject.model.res.SignUpResponse;
import com.example.muhammadjon.myproject.network.ApiService;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrActivity extends AppCompatActivity implements View.OnClickListener, IWatcher {
    private TextInputEditText etsurname;
    private TextInputEditText etname;
    private TextInputEditText etLogin;
    private ProgressBar bar;
    private TextInputEditText etpasswort;
    private AppCompatButton buttonsave;
    private ApiService service;
    private SharedPreferences preferences;
    private String surname, name, login, passwort;
    private CompositeDisposable cd = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actictivity_register);

        bar = findViewById(R.id.progressBar1);
        bar.setVisibility(View.INVISIBLE);

        etsurname = findViewById(R.id.register_l_firstname);
        etname = findViewById(R.id.register_l_lastname);
        etLogin = findViewById(R.id.register_activity_l_login);
        etpasswort = findViewById(R.id.reggister_l_passwort);
        buttonsave = findViewById(R.id.register_l_btn);
        buttonsave.setOnClickListener(this);
        App app = (App) getApplication();
        service = app.getApiService();


        buttonsave.setEnabled(false);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        etname.addTextChangedListener(new TextWatcherImpl(this));
        etsurname.addTextChangedListener(new TextWatcherImpl(this));
        etLogin.addTextChangedListener(new TextWatcherImpl(this));
        etpasswort.addTextChangedListener(new TextWatcherImpl(this));
    }

    @Override
    public void onClick(View view) {
        bar.setVisibility(View.VISIBLE);
        Log.d("RegistrActivity", surname + name + "Salomlar");
        if (!surname.isEmpty() && !name.isEmpty() &&
                !login.isEmpty() && !passwort.isEmpty()) {
            SignUpRequest signUpRequest = new SignUpRequest(surname, name, login, passwort);
            Single<SignUpResponse> sign = service.signUp(signUpRequest);
            sign.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<SignUpResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            cd.add(d);
                        }

                        @Override
                        public void onSuccess(SignUpResponse signUpResponse) {
                            Log.d("MainActivity", "Succes" + signUpResponse.getUserId());
                            Intent intent = new Intent(RegistrActivity.this,
                                    PingcodeActivity.class);
                            Toast.makeText(RegistrActivity.this, "userId : "+signUpResponse.getUserId(), Toast.LENGTH_SHORT).show();
                            preferences.edit().putInt("iss",signUpResponse.getUserId()).apply();
                            preferences.edit().putBoolean("is_first", false).apply();
                            intent.putExtra("key", passwort);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                           Log.d("RegisterActivity","onError"+e.getMessage());
                        }
                    });

        }

    }

    @Override
    public void onTextChanged(String text) {
        surname = etsurname.getText().toString();
        name = etname.getText().toString();
        login = etLogin.getText().toString();
        passwort = etpasswort.getText().toString();
        if (!surname.isEmpty() && !name.isEmpty() &&
                !login.isEmpty() && !passwort.isEmpty()) {
            buttonsave.setEnabled(true);
            buttonsave.setText("Save");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cd != null && cd.isDisposed()) {
            cd.dispose();
        }
    }

}
