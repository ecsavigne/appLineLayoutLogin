package com.example.applinelayoutlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class crearMenu extends AppCompatActivity {
     protected crearMenu activity;
     protected Button varBtn_CrearM;
     protected EditText varEdiTxt_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_menu);

        setTitle("Crear Menu");
        this.activity = this;
    }
}