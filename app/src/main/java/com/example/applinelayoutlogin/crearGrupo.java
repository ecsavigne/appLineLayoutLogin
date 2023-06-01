package com.example.applinelayoutlogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class crearGrupo extends AppCompatActivity {
    protected crearGrupo activity;
    protected Button varBtn_CrearM;
    protected EditText varEdiTxt_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        setTitle("Crear Grupo");
        this.activity = this;
    }
}