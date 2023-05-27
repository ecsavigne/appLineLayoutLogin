package com.example.applinelayoutlogin.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_IdDescription extends BaseAdapter {
    ArrayList<Pair<Integer, String>> arr;
    protected Context v_context;
    protected LayoutInflater v_layout_inflater;
    protected int v_id_layout, v_container;

    /**
     *
     * @param ctx es la instancia this del activity que contiene al adapter
     * @param idLay id del layout a buscar en el recurso (R.layout.nombre)
     * @param container id,textView que muestra los Datos
     * @param src arreglo de (id, Descripcion)
     */
    public adapter_IdDescription(Context ctx, int idLay, int container, ArrayList<Pair<Integer, String>> src) {
        arr = src;
        v_context = ctx;
        v_id_layout = idLay;
        v_container = container;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    public String getValue(int i) { return arr.get(i).second; }

    public int getIndexValue(int i) { return arr.get(i).first; }

    @Override
    public long getItemId(int i) {
        return arr.get(i).first;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View itemView = view;
        TextView txtShow;
        if( itemView == null) {
            this.v_layout_inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = v_layout_inflater.inflate(this.v_id_layout, parent, false);
        }

        //setup
        txtShow = itemView.findViewById(v_container);
        txtShow.setText(arr.get(i).second);

        return itemView;
    }
}
