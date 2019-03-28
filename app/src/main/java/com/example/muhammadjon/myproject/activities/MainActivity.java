package com.example.muhammadjon.myproject.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.dbase.Event;
import com.example.muhammadjon.myproject.network.ApiService;
import com.example.muhammadjon.myproject.network.NetworkModul;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ApiService service;
    private LinearLayout btn_paynet;
    private ProgressBar paynetuzpb;
    private CompositeDisposable cd = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlueT));
        }
        btn_paynet = findViewById(R.id.paynet_uz);
        btn_paynet.setOnClickListener(this);
        paynetuzpb = findViewById(R.id.paynet_uz_pb);
        paynetuzpb.setVisibility(View.INVISIBLE);
        NetworkModul networkModul = new NetworkModul(this);
        service = networkModul.getApiservice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cd != null && cd.isDisposed()) {
            cd.dispose();
        }
    }

    @Override
    public void onClick(View view) {
        paynetuzpb.setVisibility(View.VISIBLE);
        Single<Event> single = service.event();
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Event>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(Event event) {
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < event.getCateories()[0].getName_uz().length(); i++) {
                            list.add(event.getCateories()[0].getName_uz());
                            Log.d("MainActivity_in", "Category: " + list.get(i));
                        }
                        Toast.makeText(MainActivity.this, "ishladi: " + event.getCateories()[0].getName_ru(), Toast.LENGTH_SHORT).show();
                        paynetuzpb.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "onError"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
