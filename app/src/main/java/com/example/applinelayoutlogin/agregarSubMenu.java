package com.example.applinelayoutlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class agregarSubMenu extends AppCompatActivity implements View.OnClickListener {
    protected agregarSubMenu activity;
    protected Button varBtn_CrearSubMenu;
    protected EditText varEditTxt_Value;
    protected Spinner varSpinner_SubMenu_Menu, varSpinner_SubMenu_Permiso;
    public String[] opciones, op2;
    public ArrayAdapter<String> varAdapter, varAdapterPermiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sub_menu);

        setTitle("Agregar SubMenu");
        this.activity = this;

        this.varBtn_CrearSubMenu = findViewById(R.id.idBtn_SubMenu_Registrar);

        this.varEditTxt_Value = findViewById(R.id.idEditTxt_SubMenu_NombreSubMenu);

        this.varSpinner_SubMenu_Menu = findViewById(R.id.idSpinner_SubMenu_Menu);
        this.varSpinner_SubMenu_Permiso = findViewById(R.id.idSpinner_SubMenu_Permiso);

        this.varBtn_CrearSubMenu.setOnClickListener(this);
        //Colocar datos a spinner
        this.opciones = new String[]{"Menu1", "Menu3", "Menu4", "Menu5"};
        this.op2 = new String[]{"Permiso1", "Permiso2", "Permiso3", "Permiso4"};
        //this.varAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_layout_registrar_tarifapago, this.opciones);
        this.varSpinner_SubMenu_Menu.setAdapter(varAdapter);
        //this.varAdapterPermiso = new ArrayAdapter<>(this, R.layout.spinner_item_layout_registrar_tarifapago, this.op2);
        this.varSpinner_SubMenu_Permiso.setAdapter(varAdapterPermiso);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(activity, "Crear SubMenu", Toast.LENGTH_SHORT).show();
    }
}