package com.example.applinelayoutlogin.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applinelayoutlogin.R;

public class MyListAdapter_candadoParlet extends BaseAdapter {
    protected String[] str;
    protected Activity context;

    public MyListAdapter_candadoParlet(@NonNull Activity context, String[] string) {
        this.context = context;
        this.str = string;
    }

    @Override
    public int getCount() {
        return this.str.length;
    }

    @Override
    public Object getItem(int i) {
        return this.str[i];
    }

    @Override
    public long getItemId(int i) {return 0; }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = view;
        if(itemView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            itemView = inflater.inflate(R.layout.listview_item_candadoparlet, null, true);
        }

        TextView numero = itemView.findViewById(R.id.id_tv_candadoparlet_num);
        numero.setText(str[position]);

        return itemView;

    }
}

