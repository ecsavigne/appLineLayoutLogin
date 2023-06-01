package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.MyTuples.Quintet;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.libs.varGlobals;

import java.util.ArrayList;

public class insertarTerminal extends AppCompatActivity implements View.OnClickListener {
    protected TextView tv_num1, tv_num2, tv_apuesta;
    protected Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn_guardar, btnF;
    protected ImageButton btn_borrar;
    public int tv_select,  terminal;  //Bandera de tres estados que indican el tv seleccionado a modificar
    public String textNumero1, textNumero2, textApuesta;
    public float fijo, corrido,montoJugadas;
    public boolean realFijo, realApuesta;
    protected BD db;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_terminal);
        setTitle("Jugar terminal");

        montoJugadas = 0;
        //realFijo = false;
        realApuesta = false;
        /*Verificar que viene de la activity Login
         * si no redireccionarme para el login
         * */
        tv_num1 = findViewById(R.id.id_tv_insertarterminal_terminal);
        tv_num2 = findViewById(R.id.id_tv_insertarterminal_fijo);
        tv_apuesta = findViewById(R.id.id_tv_insertarterminal_corrido);
        //****
        btn0 = findViewById(R.id.id_btn_insertarterminal_0);
        btn1 = findViewById(R.id.id_btn_insertarterminal_7);
        btn2 = findViewById(R.id.id_btn_insertarterminal_8);
        btn3 = findViewById(R.id.id_btn_insertarterminal_9);
        btn4 = findViewById(R.id.id_btn_insertarterminal_4);
        btn5 = findViewById(R.id.id_btn_insertarterminal_5);
        btn6 = findViewById(R.id.id_btn_insertarterminal_6);
        btn7 = findViewById(R.id.id_btn_insertarterminal_1);
        btn8 = findViewById(R.id.id_btn_insertarterminal_2);
        btn9 = findViewById(R.id.id_btn_insertarterminal_3);
        btn_guardar = findViewById(R.id.id_btn_insertarterminal_guardar);
        btn_borrar = findViewById(R.id.id_btn_insertarterminal_borrar);
        btnF = findViewById(R.id.id_btn_insertarterminal_pto);
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
        tv_num1.setOnClickListener(this);
        tv_num2.setOnClickListener(this);
        tv_apuesta.setOnClickListener(this);
        btnF.setOnClickListener(this);

        this.db = new BD(this, "bolita.db", null, 1);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        textNumero1 = tv_num1.getText().toString();
        textNumero2 = tv_num2.getText().toString();
        textApuesta = tv_apuesta.getText().toString();

        switch (v.getId()){
            case R.id.id_tv_insertarterminal_terminal:         //Establecer el foco al los Numero que se juega
                btnF.setEnabled(false);   //Deshabilitar el "." para el numero1 que se juega
                if (tv_select == 2) {
                    tv_num2.setBackgroundResource(R.drawable.edit_mostrar_rounded); //quitar el foco
                }else if(tv_select == 3) {
                   tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                tv_select = 1;
                tv_num1.setBackgroundResource(R.drawable.edittext_selecte); //Poner foco
                break;
            case R.id.id_tv_insertarterminal_fijo:           //Establecer foco al valor en numero2
                //btnF.setEnabled(false);
                if (tv_select == 1) {
                   tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }else if(tv_select == 3) {
                   tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                // if(!realFijo) btnF.setEnabled(true); else btnF.setEnabled(false);
                tv_select = 2;
                tv_num2.setBackgroundResource(R.drawable.edittext_selecte);
                break;
            case R.id.id_tv_insertarterminal_corrido:
                    btnF.setEnabled(!realApuesta);
                if (tv_select == 1) {
                   tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                else if(tv_select == 2) {
                    tv_num2.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                tv_select = 3;
                tv_apuesta.setBackgroundResource(R.drawable.edittext_selecte);
                break;
            //********** CODIGO PARA LOS BOTONES************************************************************
            case R.id.id_btn_insertarterminal_pto:
                if (tv_select == 2 && textNumero2.length()<4 && !realFijo){
                    realFijo = true;
                    tv_num2.setText(textNumero2 + ".");
                }else
                if(tv_select == 3 && textApuesta.length()<4 && !realApuesta){
                    realApuesta = true;
                    tv_apuesta.setText(textApuesta + ".");
                }
                break;
            case R.id.id_btn_insertarterminal_0:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "0");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "0");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "0");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_7:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "7");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "7");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "7");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_8:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "8");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "8");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "8");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_9:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "9");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "9");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "9");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_4:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "4");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "4");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "4");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_5:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "5");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "5");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "5");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_6:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "6");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "6");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "6");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_1:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "1");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "1");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "1");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_2:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "2");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "2");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "2");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_3:
                if (tv_select == 1 && textNumero1.length()<3){
                    tv_num1.setText(textNumero1 + "3");
                }else if(tv_select == 2 && textNumero2.length()<5){
                    tv_num2.setText(textNumero2 + "3");
                }else if(tv_select == 3 && textApuesta.length()<5){
                    tv_apuesta.setText(textApuesta + "3");
                }else Toast.makeText(this, "Seleccione donde quiere escribir 9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_borrar:
                if (tv_select == 1 && textNumero1.length()>0){
                    tv_num1.setText(textNumero1.substring(0, textNumero1.length()-1));
                }else if(tv_select == 2 && textNumero2.length()>0){
                    tv_num2.setText(textNumero2.substring(0,textNumero2.length()-1));
                }else if(tv_select == 3 && tv_apuesta.length()>0){
                    if(realApuesta && (textApuesta.indexOf('.')== textApuesta.length()-1)){
                        realApuesta = false;
                        btnF.setEnabled(true);
                    }
                    tv_apuesta.setText(textApuesta.substring(0,textApuesta.length()-1));
                }else Toast.makeText(this, "Seleccione donde quiere borrar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_insertarterminal_guardar:
                terminal = (textNumero1.length()>0)? Integer.parseInt(textNumero1): 0;
                fijo = (textNumero2.length()>0 && (!textNumero2.equals(".")))? Float.parseFloat(textNumero2): (float)0;
                corrido = (textApuesta.length()>0 && (!textApuesta.equals(".")))? Float.parseFloat(textApuesta): (float)0;
                int res;

                if(terminal > 0 && (fijo > 0 || corrido > 0)){
                    //insertar
                    float valF = fijo/10, valCorr = corrido/10;
                    int idUsr = varGlobals.idUsr;
                    //Calcular terminales
                    ArrayList<Quintet<Integer, Integer, Float, Float, Float>> jugadaTerminales;
                    jugadaTerminales = funcSistema.f_craer_num_unidades(terminal, valF, valCorr );
                    //insertar jugadas
                    res = funcSistema.f_registrar_jugada(this.db,idUsr, jugadaTerminales );
                    if(res == -3) {
                        Toast.makeText(this, varGlobals.nameUsr+ " Usted no tiene limite para agregar jugada consurte con su banco", Toast.LENGTH_LONG).show();
                        return;
                    }
                   if(res != -2) {
                       tv_num1.setText("");
                       tv_num2.setText("");
                       tv_apuesta.setText("");
                       //realFijo = false;
                       realApuesta = false;
                       if (tv_select == 1) {
                           tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                       } else if (tv_select == 2) {
                           tv_num2.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                       } else {
                           tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                       }
                       tv_select = 0;
                       Toast.makeText(this, "Se guardo la jugada correctamente", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(this, "No se guardo la jugada, Ocurrio un error al insertar. Tenga en cuenta que el usuario debe tener limites asociados", Toast.LENGTH_LONG).show();
                   }
                }else Toast.makeText(this, "No se guardo la jugada, revisela por favor", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}