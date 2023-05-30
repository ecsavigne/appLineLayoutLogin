package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applinelayoutlogin.adapter.MyListAdapter_candadoParlet;
import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.libs.varGlobals;

import java.util.ArrayList;

public class user_candado_parlet extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_num1,tv_apuesta;
    protected Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn_guardar, btnF;
    protected ImageButton btn_borrar;
    protected boolean es_apuesta;  //Bandera de que indica si estoy en el tv_apuesta
    protected String textNumero,textApuesta; // vars para recoger y modificar los valores de tv
    protected float numero1, apuesta,montoJugadas;
    boolean realApuesta;        // indica si apuesta ya es un float
    protected String [] num;
    protected ListView lv_numeros;
    protected int item_pos_lv;   // posicion del ultimo item seleccionado
    protected MyListAdapter_candadoParlet adapter;
    protected BD admind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_candado_parlet);
        setTitle("Jugar Candado - Parlet");

        montoJugadas = 0;
        realApuesta = false;
        es_apuesta = false;
        this.admind = new BD(this, "bolita.db", null, 1);

        lv_numeros = findViewById(R.id.id_lv_candadoparlet);
        num = new String[15];
        f_init_num();
        adapter = new MyListAdapter_candadoParlet (this,num);
        lv_numeros.setAdapter(adapter);
        tv_apuesta = findViewById(R.id.id_tv_candadoparlet_apuesta);

        //****
        btn0 = findViewById(R.id.id_btn_candadoparlet_0);
        btn1 = findViewById(R.id.id_btn_candadoparlet_7);
        btn2 = findViewById(R.id.id_btn_candadoparlet_8);
        btn3 = findViewById(R.id.id_btn_candadoparlet_9);
        btn4 = findViewById(R.id.id_btn_candadoparlet_4);
        btn5 = findViewById(R.id.id_btn_candadoparlet_5);
        btn6 = findViewById(R.id.id_btn_candadoparlet_6);
        btn7 = findViewById(R.id.id_btn_candadoparlet_1);
        btn8 = findViewById(R.id.id_btn_candadoparlet_2);
        btn9 = findViewById(R.id.id_btn_candadoparlet_3);
        btn_guardar = findViewById(R.id.id_btn_candadoparlet_guardar);
        btn_borrar = findViewById(R.id.id_btn_candadoparlet_borrar);
        btnF = findViewById(R.id.id_btn_candadoparlet_pto);
        //
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_guardar.setOnClickListener(this);
        btn_borrar.setOnClickListener(this);
        tv_apuesta.setOnClickListener(this);
        btnF.setOnClickListener(this);
        // *****************************************************************************************
        lv_numeros.setOnItemClickListener((adapterView, view, i, l) -> {
            if(tv_num1 != null){
                tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
            }
            tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
            tv_num1= view.findViewById(R.id.id_tv_candadoparlet_num);
            tv_num1.setBackgroundResource(R.drawable.edittext_selecte);
            item_pos_lv = i;
            es_apuesta = false;
            btnF.setEnabled(false);     // cambiar el color y activarlo el el evento de lv_apuesta


        });
    }

    //codigo para el manejo de los eventos onClick de los botones de la activity
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v){
         textNumero = (tv_num1!=null)? tv_num1.getText().toString(): "";
         textApuesta = tv_apuesta.getText().toString();
        switch (v.getId()){
            case R.id.id_tv_candadoparlet_apuesta:
                btnF.setEnabled(!realApuesta);
                if (tv_num1 != null)
                    tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                tv_apuesta.setBackgroundResource(R.drawable.edittext_selecte);
                es_apuesta = true;
                break;
            //********** CODIGO PARA LOS BOTONES************************************************************
            case R.id.id_btn_candadoparlet_pto:      // tengo que programar el estado de la apuesta OJOOOO
                if (es_apuesta && textApuesta.length()<3 && !realApuesta){
                    realApuesta = true;
                    tv_apuesta.setText(textApuesta + ".");
                }
                break;
            case R.id.id_btn_candadoparlet_0:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "0";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "0");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_1:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "1";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "1");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_2:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "2";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "2");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_3:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "3";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "3");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_4:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "4";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "4");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_5:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "5";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "5");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_6:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "6";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "6");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_7:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "7";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "7");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_8:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "8";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "8");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_9:
                if (!es_apuesta && num[item_pos_lv].length()<2){
                    num[item_pos_lv] += "9";
                }else if(es_apuesta && textApuesta.length()<4){
                    tv_apuesta.setText(textApuesta + "9");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_candadoparlet_borrar:
                if (!es_apuesta && num[item_pos_lv].length()>0){  // y es numero
                    num[item_pos_lv] = num[item_pos_lv].substring(0, num[item_pos_lv].length()-1);
                }else if(es_apuesta && textApuesta.length()>0) {
                    if (realApuesta && (textApuesta.indexOf('.') == textApuesta.length() - 1)) {
                        realApuesta = false;
                        btnF.setEnabled(true);
                    }
                    tv_apuesta.setText(textApuesta.substring(0, textApuesta.length() - 1));
                }
                break;
            case R.id.id_btn_candadoparlet_guardar: // pendiente por base de datos
                ArrayList<Pair<Integer, Integer>> parlets;
                int idUsr = varGlobals.idUsr;
                //crear parlets
                float priceParlet = (tv_apuesta.getText().toString().isEmpty()) ? (float)0 : Float.parseFloat(tv_apuesta.getText().toString());
                if(priceParlet == 0 ) {
                    Toast.makeText(this, "Debe agregar apuesta valida", Toast.LENGTH_SHORT).show();
                    return;
                }
                parlets = funcSistema.f_obtener_parlets(num);
                if(parlets == null) {
                    Toast.makeText(this, "Deba existir mas de un numero para calcular parlets", Toast.LENGTH_SHORT).show();
                    return;
                }
                priceParlet = priceParlet/parlets.size();
                ArrayList<Triplet<Integer, Integer, Float>>  parletsValor = new ArrayList<>();
                //Asigna a cada parlets su valor
                for( int i = 0; i < parlets.size(); i++) {
                   Triplet<Integer, Integer, Float> temp = new Triplet<>(parlets.get(i).first, parlets.get(i).second, priceParlet);
                   parletsValor.add(temp);
                }
                int res = funcSistema.f_registrar_parlets(admind, idUsr, parletsValor);
                if(res == -3) {
                    Toast.makeText(this, varGlobals.nameUsr+ " Usted no tiene limite para agregar jugada consurte con su banco", Toast.LENGTH_LONG).show();
                    return;
                }
                if(res != -2) {
                    Toast.makeText(this, "Parlets agregados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ocurrio un error registrando parlets. Tenga en cuenta que el usuario debe tener limites asociados", Toast.LENGTH_LONG).show();
                }
                f_init_num();
                tv_apuesta.setText("");
            break;
        }
        adapter.notifyDataSetChanged();
    }

    protected void f_init_num() {
        for(int i = 0; i < 15; i++) {
            num[i] = "";
        }
    }
}