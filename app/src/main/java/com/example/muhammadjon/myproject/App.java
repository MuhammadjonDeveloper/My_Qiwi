package com.example.muhammadjon.myproject;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.muhammadjon.myproject.activities.RegistrActivity;
import com.example.muhammadjon.myproject.activities.SplashActivity;
import com.example.muhammadjon.myproject.network.ApiService;
import com.example.muhammadjon.myproject.network.NetworkManager;

public class App extends Application {
    private ApiService apiService;
    private SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        NetworkManager manager = new NetworkManager(this);
        apiService = manager.getApiservice();

        boolean isFirst = preferences.getBoolean("is_first", true);
        if (isFirst) {
            startActivity(new Intent(this, RegistrActivity.class));
        } else {
            startActivity(new Intent(this, SplashActivity.class));
        }
    }

    public ApiService getApiService() {
        return apiService;
    }
}
