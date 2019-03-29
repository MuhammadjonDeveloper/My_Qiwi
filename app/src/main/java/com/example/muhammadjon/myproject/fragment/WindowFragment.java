package com.example.muhammadjon.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.dbase.Merchants;

public class WindowFragment extends Fragment {
    public static Fragment newInsainssatie(Merchants merchants) {

        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.window_merchant, container, false);
    }
}
