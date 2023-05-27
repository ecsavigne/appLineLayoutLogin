package com.example.applinelayoutlogin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.applinelayoutlogin.R;
import com.example.applinelayoutlogin.libs.MyTuples.Quintet;
import java.util.ArrayList;
import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.TextView;

public class adapter_jugadas extends BaseAdapter {
    protected ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> var_src_jugada;
    //protected ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> var_src_parlets;
//    /**/private ArrayList<Pair<Integer, ArrayList<T>>> src;
    protected Context v_context;
    protected LayoutInflater v_layout_inflater;
    protected boolean v_bandJugada;
    protected int v_id_layout;

    /**
     *
     * @param context es la instancia this del activity que contiene al adapter
     * @param idLayout id del layout a buscar en el recurso (R.layout.nombre)
     * @param Src_Jugada arreglo de jugada, si es null se trabaja con arreglo de Parlets
     *                      en e listView
     */
    public adapter_jugadas(Context context, int idLayout, ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> Src_Jugada) {
        //ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> Src_Parlets;
        this.v_bandJugada = true;// Si jugada = true es un ArrayList de jugada, else de parlets
        this.v_context = context;
        this.v_id_layout = idLayout;
       /* if(Src_Parlets == null){*/this.var_src_jugada = Src_Jugada;/*} else {this.var_src_parlets = Src_Parlets;}*/
        //this.src = Src_Jugada;
    }

    @Override
    public int getCount() {
        //return (this.v_bandJugada) ? this.var_src_jugada.size() : this.var_src_parlets.size() ;
        return this.var_src_jugada.size();
    }

    @Override
    public Object getItem(int i) {
//        return (this.v_bandJugada) ? this.var_src_jugada.get(i) : this.var_src_parlets.get(i);
        return this.var_src_jugada.get(i);
    }

    @Override
    public long getItemId(int i) {
//        return (this.v_bandJugada) ? this.var_src_jugada.get(i).first : this.var_src_parlets.get(i).first;
        return this.var_src_jugada.get(i).first;
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
        //textNum = itemView.findViewById(R.id.id_tv_numero_);
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
            int cantElem = var_src_jugada.get(pos).second.size();
            for(int i = 0; i < cantElem; i ++  ){
                textNum = new TextView(this.v_context);//num1 parlets
                textNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                //textNum.setLayoutParams(params);
                textNum.setGravity(Gravity.CENTER);
                textNum.setTextColor(Color.BLACK);
                textNum.setTypeface(null, Typeface.BOLD);
                textFijo = new TextView(itemView.getContext());//num2 parlets
                textFijo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textFijo.setTextColor(Color.BLACK);
                //textFijo.setLayoutParams(params);
                textFijo.setGravity(Gravity.CENTER);
                //textFijo.setTypeface(null, Typeface.BOLD);
                textCorrido = new TextView(itemView.getContext());//Valor Parlets
                textCorrido.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textCorrido.setTextColor(Color.BLACK);
                //textCorrido.setLayoutParams(params);
                textCorrido.setGravity(Gravity.CENTER);
                //textCorrido.setTypeface(null, Typeface.BOLD);


                if(var_src_jugada.get(pos).second.get(i).second != 0) {
                    textNum.setText(String.format("%s", var_src_jugada.get(pos).second.get(i).second));
                    textFijo.setText(String.format("%s", var_src_jugada.get(pos).second.get(i).third));
                    textCorrido.setText(String.format("%s", var_src_jugada.get(pos).second.get(i).fourth));
                    textNum.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                    textFijo.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                    textCorrido.setBackgroundResource(R.drawable.edit_mostrar_rounded);

                }else{
                    textNum.setText(String.format("%s", var_src_jugada.get(pos).second.get(i).first));
                    textFijo.setText(String.format("%s", var_src_jugada.get(pos).second.get(i).fifth));
                    textNum.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                    textFijo.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                lay.addView(textNum);
                layFijo.addView(textFijo);
                layCorrid.addView(textCorrido);
            }
            //textNum.setText();
        }
        else{
//
        }

        return itemView;
    }
}
