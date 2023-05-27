package com.example.applinelayoutlogin;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.applinelayoutlogin.libs.funcSistema;

import com.example.applinelayoutlogin.libs.BD.BD;

public class registrartiro extends AppCompatActivity implements View.OnClickListener {
    protected EditText v_cent, v_fijo, v_corr1, v_corr2, v_fecha, v_hora;
    protected Button v_btn_guardar;
    protected BD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrartiro);
        setTitle("Registro de tiro");

        v_cent = findViewById(R.id.id_etxt_registrartiro_cent);
        v_fijo = findViewById(R.id.id_etxt_registrartiro_fijo);
        v_corr1 = findViewById(R.id.id_etxt_registrartiro_corr1);
        v_corr2 = findViewById(R.id.id_etxt_registrartiro_corr2);
        v_fecha = findViewById(R.id.id_etxt_registrartiro_fecha);
        v_hora = findViewById(R.id.id_etxt_registrartiro_hora);
        v_btn_guardar = findViewById(R.id.id_btn_registrartiro_guardar);
        db = new BD(this, "bolita.db", null, 1);
        v_btn_guardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_btn_registrartiro_guardar) {
            if(v_fecha.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe seleccionar la fecha", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_hora.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe seleccionar la hora", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_cent.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe ecribir la centena", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_cent.getText().toString().length() != 3) {
                Toast.makeText(this, "La centena son de 3 digitos", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_fijo.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe escribir el fijo", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_corr1.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe escribir el primer corrido", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_corr2.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe escribir el segundo corrido", Toast.LENGTH_SHORT).show();
                return;
            }
            ContentValues reg = new ContentValues();
            reg.put("centena", Integer.parseInt(v_cent.getText().toString()));
            reg.put("fijo", Integer.parseInt(v_fijo.getText().toString()));
            reg.put("corrido1", Integer.parseInt(v_corr1.getText().toString()));
            reg.put("corrido2", Integer.parseInt(v_corr2.getText().toString()));
            reg.put("fechaIni", v_fecha.getText().toString());
            reg.put("hora", v_hora.getText().toString());
            if(funcSistema.f_registrar_tiro(db, reg) != 0) {
                Toast.makeText(this, "Se registro el tiro", Toast.LENGTH_SHORT).show();
                v_cent.getText().clear();
                v_fijo.getText().clear();
                v_corr1.getText().clear();
                v_corr2.getText().clear();
                v_fecha.getText().clear();
                v_hora.getText().clear();
            }else {
                Toast.makeText(this, "Ocurrio un error al insertar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}