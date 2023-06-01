package com.example.applinelayoutlogin;

import android.content.ContentValues;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.fecha_hora;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.adapter.adapter_IdDescription;
import java.util.ArrayList;

public class agregarTarifaPago extends AppCompatActivity implements View.OnClickListener {
    protected agregarTarifaPago activity;
    protected Button varBtn_CrearM;
    protected EditText varEditTxt_Value, varEditTxt_Fecha, varEditTxt_ValueBote;
    protected Spinner varSpinner_TipoJugada, varSpinner_Persona;
    public String[] opciones;
    protected ArrayList<Pair<Integer, String>> tipsJugada, persona;
    protected BD db;
    public adapter_IdDescription varAdapter, varAdapterP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarifa_pago);

        setTitle("Agregar Tarifa de Pago");
        this.activity = this;

        this.varBtn_CrearM = findViewById(R.id.idBtn_SubMenu_Registrar);
        this.varEditTxt_Value = findViewById(R.id.idEditTxt_Registrar_TarifaPago_ValorLista);
        this.varEditTxt_ValueBote = findViewById(R.id.idEditTxt_Registrar_TarifaPago_Bote);
        this.varSpinner_TipoJugada = findViewById(R.id.idSpinner_Registrar_TarifaPago_TipoJugada);
        this.varSpinner_Persona = findViewById(R.id.idSpinner_Registrar_TarifaPago_Persona);

        db = new BD(this, "bolita.db", null, 1);
        this.varBtn_CrearM.setOnClickListener(this);
        //Colocar datos a spinner
        this.opciones = new String[]{"Parlets", "Centena", "Fijo", "Corrido"};
        tipsJugada = funcSistema.f_obtener_tipoJugada(db);
        persona = funcSistema.f_obtener_usuario(db);
        varAdapter = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, tipsJugada);
        varAdapterP = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, persona);
        this.varSpinner_TipoJugada.setAdapter(varAdapter);
        this.varSpinner_Persona.setAdapter(varAdapterP);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.idBtn_SubMenu_Registrar){
            @SuppressWarnings("unchecked")
            Pair<Integer, String> item = (Pair<Integer, String>)varSpinner_TipoJugada.getSelectedItem();
            @SuppressWarnings("unchecked")
            Pair<Integer, String> itemP = (Pair<Integer, String>)varSpinner_Persona.getSelectedItem();

            if(item.second.isEmpty()) {
                Toast.makeText(activity, "Debe seleccionar un tipo de jugada", Toast.LENGTH_SHORT).show();
                return;
            }

            if(itemP.second.isEmpty()) {
                Toast.makeText(activity, "Debe seleccionar un usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            if(varEditTxt_Value.getText().toString().isEmpty()) {
                Toast.makeText(activity, "Debe ingresar porciento a pagar de Lista", Toast.LENGTH_SHORT).show();
                return;
            }

            if(varEditTxt_ValueBote.getText().toString().isEmpty()) {
                Toast.makeText(activity, "Debe ingresar porciento a pagar de Bote", Toast.LENGTH_SHORT).show();
                return;
            }

            funcSistema.f_modificar_fechaFin_tarifaPago(db, item.first);
            ContentValues reg = new ContentValues();
            reg.put("fechaIni", fecha_hora.f_obtenerFechaActual("GMT-5"));
            reg.put("valorPago_porciento_lista", Integer.parseInt(varEditTxt_Value.getText().toString()));
            reg.put("valorPago_porciento_bote", Integer.parseInt(varEditTxt_ValueBote.getText().toString()));
            reg.put("idTipoJugada", item.first);
            reg.put("idPersona", itemP.first);
            if(funcSistema.f_registrar_tarifaPago(db, reg) != 0) {
                varSpinner_TipoJugada.setSelection(0);
                varSpinner_Persona.setSelection(0);
                varEditTxt_Value.getText().clear();
                varEditTxt_ValueBote.getText().clear();
                Toast.makeText(activity, "Tarifa de pago registrada", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(activity, "Error registrando Tarifa de pago", Toast.LENGTH_SHORT).show();
            }
        }
    }
}