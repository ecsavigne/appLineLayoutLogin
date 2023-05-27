package com.example.applinelayoutlogin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applinelayoutlogin.R;
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;

import java.util.ArrayList;

public class adapter_parlets extends BaseAdapter {
    //protected ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> var_src_jugada;
    protected ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> var_src_parlets;
    //    /**/private ArrayList<Pair<Integer, ArrayList<T>>> src;
    protected Context v_context;
    protected LayoutInflater v_layout_inflater;
    protected boolean v_bandJugada;
    protected int v_id_layout;

    /**
     *
     * @param context es la instancia this del activity que contiene al adapter
     * @param idLayout id del layout a buscar en el recurso (R.layout.nombre)
     * @param Src_Jugada arreglo de parlets, si es null se trabaja con arreglo de Parlets
     *                      en e listView
     */
    public adapter_parlets(Context context, int idLayout, ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> Src_Jugada) {
        //ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> Src_Parlets;
        this.v_bandJugada = true;// Si jugada = true es un ArrayList de jugada, else de parlets
        this.v_context = context;
        this.v_id_layout = idLayout;
        /* if(Src_Parlets == null){*/this.var_src_parlets = Src_Jugada;/*} else {this.var_src_parlets = Src_Parlets;}*/
        //this.src = Src_Jugada;
    }

    @Override
    public int getCount() {
        //return (this.v_bandJugada) ? this.var_src_jugada.size() : this.var_src_parlets.size() ;
        return this.var_src_parlets.size();
    }

    @Override
    public Object getItem(int i) {
        //return (this.v_bandJugada) ? this.var_src_jugada.get(i) : this.var_src_parlets.get(i);
        return this.var_src_parlets.get(i);
    }

    @Override
    public long getItemId(int i) {
        //return (this.v_bandJugada) ? this.var_src_jugada.get(i).first : this.var_src_parlets.get(i).first;
        return this.var_src_parlets.get(i).first;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        View itemView = view;
        LinearLayout lay, layFijo, layCorrid;
        if( itemView == null) {
            this.v_layout_inflater = (LayoutInflater) v_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = v_layout_inflater.inflate(this.v_id_layout, parent, false);
        }

        //getter
        lay = itemView.findViewById(R.id.id_linlay_lv_test_num);
        layFijo = itemView.findViewById(R.id.id_linlay_lv_test_fijo);
        layCorrid = itemView.findViewById(R.id.id_linlay_lv_test_corrido);
        TextView textNum, textFijo, textCorrido;
        //Coloca la configuracion del layout para los textView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        //params.gravity = Gravity.CENTER;//Coloca el gravity del componente
        params.setMargins(0, 5, 0, 5);
        //textNum.setLayoutParams(params);

        //Setter
        //textNum.setText("1 ");
        lay.removeAllViews();
        layFijo.removeAllViews();
        layCorrid.removeAllViews();
        if(v_bandJugada) {
            int cantElem = var_src_parlets.get(pos).second.size();
            if(cantElem == 1){
                textNum = new TextView(this.v_context);//num1 parlets
                textNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                textNum.setGravity(Gravity.CENTER);
                textNum.setTextColor(Color.BLACK);
                textNum.setTypeface(null, Typeface.BOLD);
                textFijo = new TextView(this.v_context);//num2 parlets
                textFijo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textFijo.setTextColor(Color.BLACK);
                textFijo.setGravity(Gravity.CENTER);
                textCorrido = new TextView(this.v_context);//Valor Parlets
                textCorrido.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textCorrido.setTextColor(Color.BLACK);
                textCorrido.setGravity(Gravity.CENTER);

                textNum.setText(String.format("%s", var_src_parlets.get(pos).second.get(0).first));
                textFijo.setText(String.format("%s", var_src_parlets.get(pos).second.get(0).second));
                textCorrido.setText(String.format("%s", var_src_parlets.get(pos).second.get(0).third));
                textNum.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                textFijo.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                textCorrido.setBackgroundResource(R.drawable.edit_mostrar_rounded);

                lay.addView(textNum);
                layFijo.addView(textFijo);
                layCorrid.addView(textCorrido);
            }
            else {
                ArrayList<Integer> arr = new ArrayList<>();
                boolean band = false;
                float apuest = 0;
                for (int i = 0; i < cantElem; i++) {
                    if(!f_exist(arr, var_src_parlets.get(pos).second.get(i).first) && !f_exist(arr, var_src_parlets.get(pos).second.get(i).second)) {
                        arr.add(var_src_parlets.get(pos).second.get(i).first);
                        arr.add(var_src_parlets.get(pos).second.get(i).second);
                    }
                    if((int)var_src_parlets.get(pos).second.get(i).first == (int)var_src_parlets.get(pos).second.get(i).second) {
                        arr.add(var_src_parlets.get(pos).second.get(i).first);
                    }
                    if(!f_exist(arr, var_src_parlets.get(pos).second.get(i).first) && f_exist(arr, var_src_parlets.get(pos).second.get(i).second)) {
                        arr.add(var_src_parlets.get(pos).second.get(i).first);
                    }
                    if(f_exist(arr, var_src_parlets.get(pos).second.get(i).first) && !f_exist(arr, var_src_parlets.get(pos).second.get(i).second)) {
                        arr.add(var_src_parlets.get(pos).second.get(i).second);
                    }
                    if(!band){
                        apuest = var_src_parlets.get(pos).second.get(i).third;
                        band = true;
                    }
                }
                int arr_long = arr.size();
                for (int j = 0; j < arr_long; j++) {
                    textNum = new TextView(v_context);//num1 parlets
                    textNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                    textNum.setGravity(Gravity.CENTER);
                    textNum.setTextColor(Color.BLACK);
                    textNum.setTypeface(null, Typeface.BOLD);
                    textFijo = new TextView(v_context);//num2 parlets
                    textFijo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    textFijo.setTextColor(Color.BLACK);
                    textFijo.setGravity(Gravity.CENTER);

                    textNum.setText(String.format("%s",arr.get(j)));
                    textNum.setBackgroundResource(R.drawable.edit_mostrar_rounded);

                    layFijo.addView(textNum);
                    if(arr_long/2 == j-1) {
                        textFijo.setText(String.format("%s", Math.round(apuest * cantElem)));
                        textFijo.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                        layCorrid.addView(textFijo);
                    }
                    else{
                        textFijo.setText("");
                        layCorrid.addView(textFijo);
                    }
                }
            }
        }

        return itemView;
    }

    private boolean f_exist(ArrayList<Integer> arr, int num) {
        int lon = arr.size();
        for(int i = 0; i < lon; i++) {
            if(arr.get(i) == num) return true;
        }
        return false;
    }
}
