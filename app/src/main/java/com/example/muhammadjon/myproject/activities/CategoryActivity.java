package com.example.muhammadjon.myproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.muhammadjon.myproject.R;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

    }
}
