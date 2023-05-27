package com.example.applinelayoutlogin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applinelayoutlogin.R;
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;

public class adapter_lv_multiple extends BaseAdapter {
    String[] v_num, v_fijo, v_corrido;
    Context v_context;
    LayoutInflater inflater;
    View.OnClickListener activity;

    public adapter_lv_multiple(Context contex, View.OnClickListener activity, String[] n, String[] f, String[] c) {
        this.v_num = n; this.v_fijo = f; this.v_corrido = c;
        this.v_context = contex;
        this.activity = activity;
        //incicializacion de listTextNode
        for(int i = 0; i < 15; i++) {
            globalAdapter.listRunnered[i] =  new nodeTextView();
            globalAdapter.listNumber[i] =  new nodeTextView();
            globalAdapter.listFixe[i] =  new nodeTextView();
        }
    }

    @Override
    public int getCount() {
        return this.v_num.length;
    }

    @Override
    public Object getItem(int i) {
        return new Triplet<>(v_num[i], v_fijo[i], v_corrido[i]);
    }

    @Override
    public long getItemId(int i) {return 0; }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView tv_num, tv_fijo, tv_corrido;
        View itemView = view;
        if( itemView == null) {
            this.inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listview_item_multiplesjugadas, parent, false);
        }

        //getter
        tv_num = itemView.findViewById(R.id.id_tv_numero_m);
        tv_fijo = itemView.findViewById(R.id.id_tv_fijo_m);
        tv_corrido = itemView.findViewById(R.id.id_tv_corrrido_m);
        globalAdapter.addNumber(position, tv_num);
        globalAdapter.addFixe(position, tv_fijo);
        globalAdapter.addRunnered(position, tv_corrido);

        //Setter
        tv_num.setText(this.v_num[position]);
        //Agregar clic
        tv_num.setOnClickListener(view1 -> {
            globalAdapter.f_mark_in_list(tv_num, position, 1);
            globalAdapter.indexSelect = position;
            if(globalAdapter.es_apuesta) {
                globalAdapter.es_apuesta = false;
                globalAdapter.mult_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
            }
        });

        tv_fijo.setText(this.v_fijo[position]);
        //Agregar click
        tv_fijo.setOnClickListener(view1 -> {
            globalAdapter.f_mark_in_list(tv_fijo, position, 2);
            globalAdapter.indexSelect = position;
            if(globalAdapter.es_apuesta) {
                globalAdapter.es_apuesta = false;
                globalAdapter.mult_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
            }
        });

        tv_corrido.setText(this.v_corrido[position]);
        //Agregar clic
        tv_corrido.setOnClickListener(view1 ->  {
                globalAdapter.f_mark_in_list(tv_corrido, position, 3);
                globalAdapter.indexSelect = position;
                if(globalAdapter.es_apuesta) {
                    globalAdapter.es_apuesta = false;
                    globalAdapter.mult_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
        });

        return itemView;
    }
}
