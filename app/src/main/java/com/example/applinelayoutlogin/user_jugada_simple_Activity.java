package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.*;
import com.example.applinelayoutlogin.libs.MyTuples.Quintet;

import java.util.ArrayList;
//import com.example.applinelayoutlogin.libs.fecha_hora;

public class user_jugada_simple_Activity extends AppCompatActivity implements View.OnClickListener {
        protected TextView tv_num, tv_fijo, tv_corrido;
        protected Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn_guardar, btnF;
        protected ImageButton  btn_borrar;
        protected int numero,tv_select;  //Bandera de tres estados que indican el tv seleccionado a modificar
        protected String textNumero, textFijo, textCorrido;
        protected float  fijo, corrido,montoJugadas;
        protected boolean realFijo, realCorrido;
        BD admind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_jugada_simple);
        setTitle("Juga Simple ");
        montoJugadas = 0;
        realFijo = false;
        realCorrido = false;
        /*Verificar que viene de la activity Login
        * si no redireccionarme para el login
        * */
        tv_num = findViewById(R.id.id_tv_jugadasimple_num);
        tv_fijo = findViewById(R.id.id_tv_jugadasimple_fijo);
        tv_corrido = findViewById(R.id.id_tv_jugadasimple_corrido);
        //****
        btn0 = findViewById(R.id.id_btn_jugadasimple_0);
        btn1 = findViewById(R.id.id_btn_jugadasimple_1);
        btn2 = findViewById(R.id.id_btn_jugadasimple_2);
        btn3 = findViewById(R.id.id_btn_jugadasimple_3);
        btn4 = findViewById(R.id.id_btn_jugadasimple_4);
        btn5 = findViewById(R.id.id_btn_jugadasimple_5);
        btn6 = findViewById(R.id.id_btn_jugadasimple_6);
        btn7 = findViewById(R.id.id_btn_jugadasimple_7);
        btn8 = findViewById(R.id.id_btn_jugadasimple_8);
        btn9 = findViewById(R.id.id_btn_jugadasimple_9);
        btn_guardar = findViewById(R.id.id_btn_jugadasimple_guardar);
        btn_borrar = findViewById(R.id.id_btn_jugadasimple_borrar);
        btnF = findViewById(R.id.id_btn_jugadasimple_pto);
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
        tv_num.setOnClickListener(this);
        tv_fijo.setOnClickListener(this);
        tv_corrido.setOnClickListener(this);
        btnF.setOnClickListener(this);
        //
        this.admind = new BD(this, "bolita.db", null, 1);
    }

        //codigo para el manejo de los eventos onClick de los botones de la activity
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v){
        textNumero = tv_num.getText().toString();
        textFijo = tv_fijo.getText().toString();
        textCorrido = tv_corrido.getText().toString();

        switch (v.getId()){
            case R.id.id_tv_jugadasimple_num:         //Establecer el foco al los Numero que se juega
                btnF.setEnabled(false);   //Deshabilitar el "." para el numero que se juega
                if (tv_select == 2)
                    tv_fijo.setBackgroundResource(R.drawable.edit_mostrar_rounded); //quitar el foco
                else if(tv_select == 3)
                    tv_corrido.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                tv_select = 1;
                tv_num.setBackgroundResource(R.drawable.edittext_selecte); //Poner foco
                break;
            case R.id.id_tv_jugadasimple_fijo:           //Establecer foco al valor en fijo
                if (tv_select == 1) {
                    tv_num.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }else if(tv_select == 3) {
                    tv_corrido.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                btnF.setEnabled(!realFijo);
                tv_select = 2;
                tv_fijo.setBackgroundResource(R.drawable.edittext_selecte);
                break;
            case R.id.id_tv_jugadasimple_corrido:
                if (tv_select == 1)
                    tv_num.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                else if(tv_select == 2)
                    tv_fijo.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                btnF.setEnabled(!realCorrido);
                tv_select = 3;
                tv_corrido.setBackgroundResource(R.drawable.edittext_selecte);
                break;
    //********** CODIGO PARA LOS BOTONES************************************************************
            case R.id.id_btn_jugadasimple_pto:
                if (tv_select == 2 && textFijo.length()<3 && !realFijo){
                    realFijo = true;
                    tv_fijo.setText(textFijo + ".");
                    //btnF.setEnabled(false);
                }else if(tv_select == 3 && textCorrido.length()<3 && !realCorrido){
                    realCorrido = true;
                    tv_corrido.setText(textCorrido + ".");
                    //btnF.setEnabled(false);
                }
                break;
            case R.id.id_btn_jugadasimple_0:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "0");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "0");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "0");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_1:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "1");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "1");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "1");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_2:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "2");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "2");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "2");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_3:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "3");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "3");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "3");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_4:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "4");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "4");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "4");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_5:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "5");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "5");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "5");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_6:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "6");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "6");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "6");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_7:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "7");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "7");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "7");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_8:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "8");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "8");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "8");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_9:
                if (tv_select == 1 && textNumero.length()<2){
                    tv_num.setText(textNumero + "9");
                }else if(tv_select == 2 && textFijo.length()<4){
                    tv_fijo.setText(textFijo + "9");
                }else if(tv_select == 3 && textCorrido.length()<4){
                    tv_corrido.setText(textCorrido + "9");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_borrar:
                if (tv_select == 1 && textNumero.length()>0){
                      tv_num.setText(textNumero.substring(0, textNumero.length()-1));
                }else if(tv_select == 2 && textFijo.length()>0){
                    if(realFijo && (textFijo.indexOf('.')== textFijo.length()-1)){
                        realFijo = false;
                        btnF.setEnabled(true);
                       }
                    tv_fijo.setText(textFijo.substring(0,textFijo.length()-1));
                }else if(tv_select == 3 && tv_corrido.length()>0){
                    if(realCorrido && (textCorrido.indexOf('.')== textCorrido.length()-1)){
                        realCorrido = false;
                        btnF.setEnabled(true);
                    }
                    tv_corrido.setText(textCorrido.substring(0,textCorrido.length()-1));
                }else Toast.makeText(this, "Seleccione donde quiere borrar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_jugadasimple_guardar:
                numero = (textNumero.length()>0)? Integer.parseInt(textNumero): 0;
                fijo = (textFijo.length()>0 && (!textFijo.equals(".")))? Float.parseFloat(textFijo): 0;
                corrido = (textCorrido.length()>0 && (!textCorrido.equals(".")))? Float.parseFloat(textCorrido): 0;

                ArrayList<Quintet<Integer, Integer, Float, Float, Float>> jugada_ = new ArrayList<>();
                Quintet<Integer, Integer, Float, Float, Float> jugada = new Quintet<>(0, numero, fijo, corrido, (float)0);
                jugada_.add(jugada);
                int res = 0, idUsr = varGlobals.idUsr;

                if(numero > 0 && (fijo > 0 || corrido > 0)) {       //insertar en base de datoS
                    res = funcSistema.f_registrar_jugada(admind,idUsr, jugada_ );
                }
                if(res != -2) {
                        //Limpiar mis registros y asignar el foco a "numero"
                        tv_num.setText("");
                        tv_fijo.setText("");
                        tv_corrido.setText("");
                        realFijo = false;
                        realCorrido = false;
                        if (tv_select == 1)
                            tv_num.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                        else if (tv_select == 2)
                            tv_fijo.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                        else
                            tv_corrido.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                        tv_select = 0;
                        Toast.makeText(this, "Se guardo la jugada correctamente", Toast.LENGTH_SHORT).show();
                   } else Toast.makeText(this,"No se pudo guarda la jugada Revisela. Tenga en cuenta que el usuario debe tener limites asociados", Toast.LENGTH_LONG).show();
                 break;
        }
    }
    public void parlet(View v){
        Intent ir_parlet = new Intent(this,user_parlet_activity.class);
        startActivity(ir_parlet);
    }
}