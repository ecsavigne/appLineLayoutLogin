package com.example.applinelayoutlogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class asociarPermisoGrupo extends AppCompatActivity implements View.OnClickListener {
    protected asociarPermisoGrupo activity;
    protected Spinner varSpinner_Grupo;
    public String[] opciones;
    public ArrayAdapter<String> varAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asociar_permiso);

        setTitle("Asociar Permisos a grupo");
        this.activity = this;

        this.varSpinner_Grupo = findViewById(R.id.spinner);
        this.opciones = new String[]{"Administrador", "Super Admin", "Listero"};
        //this.varAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_layout_registrar_tarifapago, this.opciones);
        this.varSpinner_Grupo.setAdapter(varAdapter);

    }

    @Override
    public void onClick(View view) {

    }
}