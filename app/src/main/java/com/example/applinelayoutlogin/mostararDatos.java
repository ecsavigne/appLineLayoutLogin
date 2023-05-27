package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.applinelayoutlogin.adapter.adapter_jugadas;
import com.example.applinelayoutlogin.adapter.adapter_parlets;
import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.MyTuples.Quintet;
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.libs.varGlobals;
import java.util.ArrayList;
import java.util.Hashtable;

public class mostararDatos extends AppCompatActivity{
     protected AppCompatActivity act;
     protected ListView var_lv_mostrar;
     protected TextView var_tv_spnListero, v_tv_totals;
     protected LinearLayout lay_head;
     protected BD db;
     protected Cursor result;
     protected boolean band;
     protected Spinner spnListeros, var_local_spn_listbote, var_local_spn_listeros;
     protected String[] obj = {"yunior", "", ""}, objListBote = {"","Jugada", "Parlets"};
     protected Hashtable<String, String> listeros = new Hashtable<>();
     //protected int v_userId;
     protected float v_total;
     protected String str_total;
     protected ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> arrJugada;
     protected ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>>  arrParlets;
     protected Pair<Pair<Triplet<Float, Float, Float>, Triplet<Float, Float, Float>>, Pair<Float, Float>> v_totales;

     String[] arrViews;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostarar_datos);
        //setTitle("Mostrar datos segun listero");

        this.act = this;
        lay_head = findViewById(R.id.id_ly_mostrar_datos_head);
        this.var_tv_spnListero = findViewById(R.id.id_tv_spn_listero_admin);
        this.spnListeros = findViewById(R.id.id_spn_admin_mostrardatos);
        this.spnListeros.setVisibility(View.INVISIBLE);
        var_lv_mostrar = findViewById(R.id.id_lv_mostrar);
        //var_lv_bote = findViewById(R.id.id_lv_bote);
        this.db = new BD(this, "bolita.db", null, 1);
        v_totales = funcSistema.f_obtener_totales_asoc_usr(this.db, varGlobals.idUsr);

        switch (varGlobals.src) {
            case "mostararDatos":
                setTitle("Mostrar datos segun listero");
                ArrayAdapter<String> adapterList = new ArrayAdapter<>(this, R.layout.layout_spn,obj);
                spnListeros.setAdapter(adapterList);
                this.spnListeros.setSelection(0);
                cargarListas("");
            break;
            case "mostararList":
                setTitle("Mis Listas");
                f_create_head();
                //f_cargar_lista_jugada(varGlobals.idUsr);
                break;
            case "mostararBotes":
                setTitle("Mis Botes");
                f_create_head();
                //f_cargar_lista_parlets(varGlobals.idUsr);
            break;
        }
        int test = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("SetTextI18n")
    public void f_cargar_lista_jugada(int idUsr) {
        //Obtener las listas de jugadas y botes y mostrarlas segun usuario
        if(idUsr == 0) {
            //La llamada viene de mostrar lista desde menu admin
            int l = 0;
        }else {
            //La llamada viene de mostrar lista desde menu listero
            arrJugada = funcSistema.f_obtener_jugada_list(this.db, varGlobals.idUsr, 0);
            if(v_totales != null) {
                v_total = 0;
                v_total += v_totales.first.first.first + v_totales.first.first.second + v_totales.first.first.third;
                //str_total = "Totales recaudado:  ".concat(String.format("%s", v_total));
                this.v_tv_totals.setText("Totales recaudado:  ".concat(String.format("$ %s", v_total)));
            }
            adapter_jugadas adap = new adapter_jugadas(this, R.layout.layout_lv_test, this.arrJugada);
            this.var_lv_mostrar.setAdapter(adap);
        }
    }
    @SuppressLint("SetTextI18n")
    public void f_cargar_lista_jugada_bote(int idUsr) {
        //Obtener las listas de jugadas y botes y mostrarlas segun usuario
        if(idUsr == 0) {
            //La llamada viene de mostrar lista desde menu admin
            int l = 0;
        }else {
            //La llamada viene de mostrar lista desde menu listero
            arrJugada = funcSistema.f_obtener_jugada_list(this.db, varGlobals.idUsr, 1);
            if(v_totales != null) {
                v_total += v_totales.first.second.first + v_totales.first.second.second + v_totales.first.second.third;
                //str_total = "Totales recaudado:  ".concat(String.format("%s", v_total));
                this.v_tv_totals.setText("Totales recaudado:  ".concat(String.format("$ %s", v_total)));
            }
            adapter_jugadas adap = new adapter_jugadas(this, R.layout.layout_lv_test, this.arrJugada);
            this.var_lv_mostrar.setAdapter(adap);
        }
    }

    @SuppressLint("SetTextI18n")
    public void f_cargar_lista_parlets(int idUsr) {
        //Obtener las listas de parlets y botes y mostrarlas segun usuario
        if(idUsr == 0) {
            //La llamada viene de mostrar lista desde menu admin
            ArrayAdapter<String> adapterList = new ArrayAdapter<>(this, R.layout.layout_spn,obj);
            //adapterList.setDropDownViewResource(R.layout.layout_spn);
            spnListeros.setAdapter(adapterList);
        }else {
            //La llamada viene de mostrar parlets desde menu listero
            arrParlets = funcSistema.f_obtener_parlet_list(this.db, varGlobals.idUsr, 0);
            if(v_totales != null) {
                this.v_tv_totals.setText("Totales recaudado en parlets : ".concat(String.format("$ %s", v_totales.second.first)));
            }
            adapter_parlets adapter = new adapter_parlets(this, R.layout.layout_lv_test, this.arrParlets);
            this.var_lv_mostrar.setAdapter(adapter);
            //this.var_lv_bote.setAdapter(adapterParletsBote);
        }
    }
    @SuppressLint("SetTextI18n")
    public void f_cargar_lista_parlets_bote(int idUsr) {
        //Obtener las listas de parlets y botes y mostrarlas segun usuario
        if(idUsr == 0) {
            //La llamada viene de mostrar lista desde menu admin
            ArrayAdapter<String> adapterList = new ArrayAdapter<>(this, R.layout.layout_spn,obj);
            //adapterList.setDropDownViewResource(R.layout.layout_spn);
            spnListeros.setAdapter(adapterList);
        }else {
            //La llamada viene de mostrar parlets desde menu listero
            arrParlets = funcSistema.f_obtener_parlet_list(this.db, varGlobals.idUsr, 1);
            if(v_totales != null) {
                this.v_tv_totals.setText("Totales recaudado en parlets : ".concat(String.format("$ %s", v_totales.second.second)));
            }
            adapter_parlets adapter = new adapter_parlets(this, R.layout.layout_lv_test, this.arrParlets);
            this.var_lv_mostrar.setAdapter(adapter);
        }
    }

    public void cargarListas(String alistero) {

    }

    protected void f_create_head() {
        if(varGlobals.src.equals("mostararList") || varGlobals.src.equals("mostararBotes")){
            //Convertir de Dp a PX testeado
            float dp = 20;
            float px =  (dp - 5) * this.getResources().getDisplayMetrics().density;

            //Crear TextView Totales
            v_tv_totals = new TextView(this);
            v_tv_totals.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            v_tv_totals.setTextColor(Color.CYAN);
            //Crear Spiner jugada parlets
            var_local_spn_listbote = new Spinner(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins((int)px, 0, 0, 0);
            var_local_spn_listbote.setLayoutParams(params);
            ArrayAdapter<String> adapterList1 = new ArrayAdapter<>(this, R.layout.layout_spn,objListBote);
            var_local_spn_listbote.setAdapter(adapterList1);
            lay_head.removeAllViews();
            lay_head.addView(v_tv_totals);
            lay_head.addView(var_local_spn_listbote);
            var_local_spn_listbote.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(varGlobals.src.equals("mostararList")) {
                            if(var_local_spn_listbote.getSelectedItem().toString().equals("Jugada")) {
                                f_cargar_lista_jugada(varGlobals.idUsr);
                            }
                            else if(var_local_spn_listbote.getSelectedItem().toString().equals("Parlets")){
                                f_cargar_lista_parlets(varGlobals.idUsr);
                            }
                        }
                        if(varGlobals.src.equals("mostararBotes")) {
                            if(var_local_spn_listbote.getSelectedItem().toString().equals("Jugada")) {
                                f_cargar_lista_jugada_bote(varGlobals.idUsr);
                            }
                            else if(var_local_spn_listbote.getSelectedItem().toString().equals("Parlets")){
                                f_cargar_lista_parlets_bote(varGlobals.idUsr);
                            }
                        }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
    protected void f_create_body() {
    }

}