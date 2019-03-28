package com.example.muhammadjon.myproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.muhammadjon.myproject.dbase.Merchants;
import com.example.muhammadjon.myproject.fragment.WindowFragment;

import java.util.ArrayList;

public class WindowPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Merchants> list;

    public WindowPageAdapter(FragmentManager fm,ArrayList<Merchants> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int i) {
        return WindowFragment.newInsainssatie(list.get(i));
    }

    @Override
    public int getCount() {
        return 0;
    }
}
