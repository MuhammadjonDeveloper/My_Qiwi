package com.example.muhammadjon.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.dbase.Categories;

import java.util.ArrayList;

public class CateggoryAdapter extends RecyclerView.Adapter<CateggoryAdapter.categoryVH> {

    private LayoutInflater inflater;
    private ArrayList<Categories> list;

    public CateggoryAdapter(ArrayList<Categories> list, Context context) {
        this.list = list;
        inflater=LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public categoryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=inflater.inflate(R.layout.category_row,viewGroup,false);
        return new categoryVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryVH categoryVH, int i) {
         categoryVH.onBind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class categoryVH extends RecyclerView.ViewHolder {
        private AppCompatTextView etId,ettitle;
        public categoryVH(@NonNull View itemView) {
            super(itemView);
        }

        void onBind(Categories categories) {

        }
    }
}
