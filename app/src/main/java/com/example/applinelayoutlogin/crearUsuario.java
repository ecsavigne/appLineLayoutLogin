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
import com.example.applinelayoutlogin.adapter.adapter_IdDescription;
import com.example.applinelayoutlogin.libs.funcSistema;

import java.util.ArrayList;

public class crearUsuario extends AppCompatActivity implements View.OnClickListener{
    protected Spinner v_spn_tipoUsr;
    protected EditText v_etxt_usr, v_etxt_pass, v_etxt_passR;
    protected Button v_btn;
    ArrayList<Pair<Integer, String>> tipsUsr;
    protected BD admind;
    protected adapter_IdDescription adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        setTitle("Registrar Usuario");

        //setup
        admind = new BD(this, "bolita.db", null, 1);
        v_spn_tipoUsr = findViewById(R.id.id_spn_regusr);
        v_etxt_usr = findViewById(R.id.id_etxt_usr_regusr);
        v_etxt_pass = findViewById(R.id.id_etxt_regusr_pass);
        v_etxt_passR = findViewById(R.id.id_etxt_regusr_passR);
        v_btn = findViewById(R.id.id_btn_regusr_guardar);
        tipsUsr = funcSistema.f_obtener_tipoUsuario(admind);
        adapter = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, tipsUsr);
        v_spn_tipoUsr.setAdapter(adapter);
        v_btn.setOnClickListener(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onClick(View view) {
        //String t = v_spn_tipoUsr.getSelectedItem().toString();
        Pair<Integer, String> item = (Pair<Integer, String>) v_spn_tipoUsr.getSelectedItem();
        if(view.getId() == R.id.id_btn_regusr_guardar) {
            //Comprobar que el usuario no exista y si no existe lo registra y si existe
            if( funcSistema.f_contar_ocurrencia(admind, "usuario", "nombreUsuario = '"+v_etxt_usr.getText().toString()+"'") != 0) {
                Toast.makeText(this, "Ya existe un usuario con este nombre", Toast.LENGTH_SHORT).show();
                return;
            }
            if(((Pair<?, ?>) v_spn_tipoUsr.getSelectedItem()).second == "" ){
                Toast.makeText(this, "Debe selecionar un tipo de Usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_etxt_pass.getText().toString().isEmpty() || v_etxt_passR.getText().toString().isEmpty()) {
                Toast.makeText(this, "Debe escribir y verificar la contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
            if(v_etxt_pass.getText().toString().equals(v_etxt_passR.getText().toString())) {
                //Registra ususario
                ContentValues reg = new ContentValues();
                reg.put("idTipoUsuario", item.first);
                reg.put("nombreUsuario", v_etxt_usr.getText().toString());
                reg.put("pass", v_etxt_pass.getText().toString());
                reg.put("isBlock", 0);
                v_spn_tipoUsr.setSelection(0);
                if(funcSistema.f_registrar_usuario(admind, reg) != 0) {
                    Toast.makeText(this, "Registrado el usuario : "+ v_etxt_usr.getText().toString(), Toast.LENGTH_LONG).show();
                    v_etxt_usr.setText("");
                    v_etxt_pass.setText("");
                    v_etxt_passR.setText("");
                }else {
                    Toast.makeText(this, "Error insertando el usuario : "+ v_etxt_usr.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "No coinciden las contraseñas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}