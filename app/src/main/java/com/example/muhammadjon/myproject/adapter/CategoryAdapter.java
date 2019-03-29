package com.example.muhammadjon.myproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.activities.WindowActivity;
import com.example.muhammadjon.myproject.dbase.Categories;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryVH> {
    private LayoutInflater inflater;
    private ArrayList<Categories> list;
    private Context ctx;
    private int idlst;
    private static final String KEY="key";

    public CategoryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        this.ctx=context;
    }

    public void setItems(ArrayList<Categories> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public categoryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.category_row, viewGroup, false);
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

//    public void deleteItem() {
//        list.remove(0);
//        notifyDataSetChanged();
//    }

    class categoryVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView textId, texttitle;

        public categoryVH(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.disply_id);
            texttitle = itemView.findViewById(R.id.category_title);
            itemView.setOnClickListener(this);
        }

        void onBind(Categories categories) {
            texttitle.setText(categories.getName_uz());
            textId.setText(categories.getDisplay_order() + "");
            idlst= (int) categories.getId();
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(ctx, WindowActivity.class);
            intent.putExtra(KEY,idlst);
            ctx.startActivity(intent);
        }
    }
}
