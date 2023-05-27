package com.example.applinelayoutlogin.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applinelayoutlogin.R;

//Esto es otra prueba
public class MylistAdapter_multipleJugadas extends BaseAdapter {
    protected String[] str;
    protected Activity context;

    public MylistAdapter_multipleJugadas(@NonNull Activity context, String[] string) {
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

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = view;
        if(itemView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            itemView = inflater.inflate(R.layout.listview_item_multiplesjugadas, parent, true);
        }

        TextView titleText = itemView.findViewById(R.id.id_tv_numero_m);
        TextView titlefijo = itemView.findViewById(R.id.id_tv_fijo_m);
        TextView titlecorrido = itemView.findViewById(R.id.id_tv_corrrido_m);


        titleText.setText(str[position]);
        titlefijo.setText(str[position]);
        titlecorrido.setText(str[position]);

        return itemView;
    }
}