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
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.libs.varGlobals;

import java.util.ArrayList;

public class user_parlet_activity extends AppCompatActivity implements View.OnClickListener {
    protected TextView tv_num1, tv_num2, tv_apuesta;
    protected Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn_guardar, btnF;
    protected ImageButton btn_borrar;
    protected int numero1, numero2, tv_select;  //Bandera de tres estados que indican el tv seleccionado a modificar
    String textNumero1, textNumero2, textApuesta;
    float apuesta,montoJugadas;
    boolean realApuesta;
    BD admind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_parlet);
        montoJugadas = 0;
        //realFijo = false;
        realApuesta = false;
        /*Verificar que viene de la activity Login
         * si no redireccionarme para el login
         * */
        tv_num1 = findViewById(R.id.id_tv_parlet_num1);
        tv_num2 = findViewById(R.id.id_tv_parlet_num2);
        tv_apuesta = findViewById(R.id.id_tv_parlet_apuesta);
        //****
        btn0 = findViewById(R.id.id_btn_parlet_0);
        btn1 = findViewById(R.id.id_btn_parlet_7);
        btn2 = findViewById(R.id.id_btn_parlet_8);
        btn3 = findViewById(R.id.id_btn_parlet_9);
        btn4 = findViewById(R.id.id_btn_parlet_4);
        btn5 = findViewById(R.id.id_btn_parlet_5);
        btn6 = findViewById(R.id.id_btn_parlet_6);
        btn7 = findViewById(R.id.id_btn_parlet_1);
        btn8 = findViewById(R.id.id_btn_parlet_2);
        btn9 = findViewById(R.id.id_btn_parlet_3);
        btn_guardar = findViewById(R.id.id_btn_parlet_guardar);
        btn_borrar = findViewById(R.id.id_btn_parlet_borrar);
        btnF = findViewById(R.id.id_btn_parlet_pto);
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

        //
        this.admind = new BD(this, "bolita.db", null, 1);
        //handlerConsult = this.admind.getWritableDatabase();
        // definicio del objeto que se encarga de funcEspeciales

    }
    //codigo para el manejo de los eventos onClick de los botones de la activity
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v){
        textNumero1 = tv_num1.getText().toString();
        textNumero2 = tv_num2.getText().toString();
        textApuesta = tv_apuesta.getText().toString();

        switch (v.getId()) {
            case R.id.id_tv_parlet_num1:         //Establecer el foco al los Numero que se juega
                btnF.setEnabled(false);   //Deshabilitar el "." para el numero1 que se juega
                if (tv_select == 2)
                    tv_num2.setBackgroundResource(R.drawable.edit_mostrar_rounded); //quitar el foco
                else if (tv_select == 3)
                    tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                tv_select = 1;
                tv_num1.setBackgroundResource(R.drawable.edittext_selecte); //Poner foco
                break;
            case R.id.id_tv_parlet_num2:           //Establecer foco al valor en numero2
                btnF.setEnabled(false);
                if (tv_select == 1) {
                    tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                } else if (tv_select == 3) {
                    tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                // if(!realFijo) btnF.setEnabled(true); else btnF.setEnabled(false);
                tv_select = 2;
                tv_num2.setBackgroundResource(R.drawable.edittext_selecte);
                break;
            case R.id.id_tv_parlet_apuesta:
                btnF.setEnabled(!realApuesta);
                if (tv_select == 1) {
                    tv_num1.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                } else if (tv_select == 2) {
                    tv_num2.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                }
                tv_select = 3;
                tv_apuesta.setBackgroundResource(R.drawable.edittext_selecte);
                break;
            //********** CODIGO PARA LOS BOTONES************************************************************
            case R.id.id_btn_parlet_pto:
                if (tv_select == 3 && textApuesta.length() < 3 && !realApuesta) {
                    realApuesta = true;
                    tv_apuesta.setText(textApuesta + ".");
                }
                break;
            case R.id.id_btn_parlet_0:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "0");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "0");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "0");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_7:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "7");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "7");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "7");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_8:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "8");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "8");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "8");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_9:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "9");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "9");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "9");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_4:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "4");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "4");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "4");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_5:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "5");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "5");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "5");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_6:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "6");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "6");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "6");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_1:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "1");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "1");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "1");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_2:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "2");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "2");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "2");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_3:
                if (tv_select == 1 && textNumero1.length() < 2) {
                    tv_num1.setText(textNumero1 + "3");
                } else if (tv_select == 2 && textNumero2.length() < 2) {
                    tv_num2.setText(textNumero2 + "3");
                } else if (tv_select == 3 && textApuesta.length() < 4) {
                    tv_apuesta.setText(textApuesta + "3");
                } else
                    Toast.makeText(this, "Seleccione donde quiere escribir 9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_borrar:
                if (tv_select == 1 && textNumero1.length() > 0) {
                    tv_num1.setText(textNumero1.substring(0, textNumero1.length() - 1));
                } else if (tv_select == 2 && textNumero2.length() > 0) {
                    tv_num2.setText(textNumero2.substring(0, textNumero2.length() - 1));
                } else if (tv_select == 3 && tv_apuesta.length() > 0) {
                    if (realApuesta && (textApuesta.indexOf('.') == textApuesta.length() - 1)) {
                        realApuesta = false;
                        btnF.setEnabled(true);
                    }
                    tv_apuesta.setText(textApuesta.substring(0, textApuesta.length() - 1));
                } else
                    Toast.makeText(this, "Seleccione donde quiere borrar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_btn_parlet_guardar:

                numero1 = (textNumero1.length() > 0) ? Integer.parseInt(textNumero1) : 0;
                numero2 = (textNumero2.length() > 0 && (!textNumero2.equals("."))) ? Integer.parseInt(textNumero2) : 0;
                apuesta = (textApuesta.length() > 0 && (!textApuesta.equals("."))) ? Float.parseFloat(textApuesta) : 0;

                if (numero1 > 0 && numero2 > 0 && apuesta > 0) {
                    //insertar
                    ArrayList<Triplet<Integer, Integer, Float>> parlets = new ArrayList<>();
                    Triplet<Integer, Integer, Float> temp = new Triplet<>(numero1, numero2, apuesta);
                    parlets.add(temp);
                    int res = funcSistema.f_registrar_parlets(this.admind, varGlobals.idUsr, parlets);
                    if(res == -3) {
                        Toast.makeText(this, varGlobals.nameUsr+ " Usted no tiene limite para agregar jugada consurte con su banco", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(res != -2) {
                        //Limpiar mis registros y asignar el foco a "numero"
                        tv_num1.setText("");
                        tv_num1.requestFocus();
                        tv_num2.setText("");
                        tv_apuesta.setText("");
                        realApuesta = false;
                        if (tv_select == 2)
                            tv_num2.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                        else if (tv_select == 3)
                            tv_apuesta.setBackgroundResource(R.drawable.edit_mostrar_rounded);
                        tv_select = 1;
                        Toast.makeText(this, "Se guardo la jugada correctamente", Toast.LENGTH_SHORT).show();

                    }else
                        Toast.makeText(this, "No se guardo la jugada, Error al insertar. Tenga en cuenta que el usuario debe tener limites asociados", Toast.LENGTH_LONG).show();
                }
            break;
        }
    }

}