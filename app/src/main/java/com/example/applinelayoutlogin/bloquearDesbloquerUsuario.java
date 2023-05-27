package com.example.applinelayoutlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.applinelayoutlogin.adapter.adapter_IdDescription;
import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.libs.varGlobals;

import java.util.ArrayList;

public class bloquearDesbloquerUsuario extends AppCompatActivity implements View.OnClickListener {
    //protected String v_src;
    protected CheckBox v_bloDes;
    protected Button v_save;
    protected Spinner v_spnUsrs;
    ArrayList<Pair<Integer, String>> usuarios;
    protected BD admind;
    protected adapter_IdDescription adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquear_desbloquer_usuario);

        //Setup
        admind = new BD(this, "bolita.db", null, 1);

        v_bloDes = findViewById(R.id.id_checkb_bloqUsr);
        v_save = findViewById(R.id.id_btn_bloqUsr_guardar);
        v_save.setOnClickListener(this);
        v_spnUsrs = findViewById(R.id.id_spn_bloqUsr_usrs);
        if(varGlobals.src.equals("bloquear") ) {
            setTitle("Bloquear Usuario");
            usuarios = funcSistema.f_obtener_usuario_desbloqueados(admind);
            v_bloDes.setText(R.string.strBloquerUsr);
            v_bloDes.setChecked(false);
        }else {
            setTitle("Desbloquear Usuario");
            usuarios = funcSistema.f_obtener_usuario_bloqueados(admind);
            v_bloDes.setText(R.string.strDesBloquerUsr);
            v_bloDes.setChecked(false);
        }
        adapter = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, usuarios);
        v_spnUsrs.setAdapter(adapter);

        v_spnUsrs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //v_bloDes.setChecked(!varGlobals.src.equals("bloquear"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_btn_bloqUsr_guardar) {
            //noinspection unchecked
            Pair<Integer, String> item = (Pair<Integer, String>) v_spnUsrs.getSelectedItem();
            //Tener en cuenta si es bloqueo o desbloqueo
            if(varGlobals.src.equals("bloquear")) {// Seccion de bloqueo
                if(item.first != 0) {
                    if(!v_bloDes.isChecked()) {
                        Toast.makeText(this, "Debe marcar bloqueo", Toast.LENGTH_SHORT).show();
                    }else{
                        //Modificar estado de Bloqueo de Usuario
                        if(funcSistema.f_modificar_estado_block(admind,true, item.first ) != 0) {
                            Toast.makeText(this, varGlobals.src + " Usuario "+item.second, Toast.LENGTH_SHORT).show();
                        }
                        usuarios = funcSistema.f_obtener_usuario_desbloqueados(admind);
                        v_spnUsrs.setSelection(0);
                    }
                }else {
                    Toast.makeText(this, "No hay usuario seleccionado", Toast.LENGTH_SHORT).show();
                }
            }else {// Desbloqueo
                if(item.first != 0) {
                    if(!v_bloDes.isChecked()) {
                        Toast.makeText(this, "Debe marcar para Desbloquear", Toast.LENGTH_SHORT).show();
                    }else{
                        //Modificar estado de Bloqueo de Usuario
                        if(funcSistema.f_modificar_estado_block(admind,false, item.first ) != 0){
                            Toast.makeText(this, varGlobals.src + " Usuario "+item.second, Toast.LENGTH_SHORT).show();
                        }
                        usuarios = funcSistema.f_obtener_usuario_bloqueados(admind);
                        v_spnUsrs.setSelection(0);
                    }
                }else {
                    Toast.makeText(this, "No hay usuario seleccionado", Toast.LENGTH_SHORT).show();
                }
            }
            adapter = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, usuarios);
            v_spnUsrs.setAdapter(adapter);
        }
    }
}