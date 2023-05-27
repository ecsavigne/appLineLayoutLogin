package com.example.applinelayoutlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class crearPermisos extends AppCompatActivity {
    protected crearPermisos activity;
    protected Button varBtn_CrearP;
    protected EditText varEdiTxt_Permiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_permisos);

        setTitle("Crear Permiso");
        this.activity = this;
    }
}