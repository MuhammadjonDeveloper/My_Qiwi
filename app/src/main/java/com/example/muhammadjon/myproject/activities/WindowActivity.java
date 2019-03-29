package com.example.muhammadjon.myproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.adapter.WindowAdapter;
import com.example.muhammadjon.myproject.dbase.AppDataBase;
import com.example.muhammadjon.myproject.dbase.Merchants;
import com.example.muhammadjon.myproject.dbase.MerchantsDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WindowActivity extends AppCompatActivity {
    private MerchantsDao merchantsdao;
    private RecyclerView rv;
    private int categoryid;
    private ArrayList<Merchants> merchanlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_category);
        rv = findViewById(R.id.window_category_rv);

        merchantsdao = AppDataBase.getInstaince(this).getMerchantDao();
        Bundle arg = getIntent().getExtras();

        if (arg != null) {
            categoryid = arg.getInt("key");
            Toast.makeText(this, "id = " + categoryid, Toast.LENGTH_SHORT).show();
        }
        WindowAdapter adapter = new WindowAdapter(WindowActivity.this);

        Single<List<Merchants>> listSingle = merchantsdao.getAll(categoryid);
        listSingle.subscribeOn(Schedulers.io())
                .doOnSuccess(merchants -> {
                    adapter.setItemMarches((ArrayList<Merchants>) merchants);
                })
                .doOnError(throwable -> {
                    Toast.makeText(this, "onError" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                })
                .subscribe();
        LinearLayoutManager manager = new LinearLayoutManager(WindowActivity.this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);



    }
}
