package com.example.applinelayoutlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.applinelayoutlogin.adapter.adapter_IdDescription;
import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.funcSistema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class registrarTopes extends AppCompatActivity {
    protected Spinner spn_TipoList, spn_Usr;
    protected EditText et_Fecha, et_Tope_Fijo, et_Tope_Corrido, et_Tope_Centena, et_Tope_Parlet;
    protected Button btn;
    protected LinearLayout ll_block1, ll_block2, ll_block3;

    protected BD db;
    protected ArrayList<Pair<Integer, String>> vUsr;
    protected String[] obj = {"","Jugada", "Parlets"};
    ArrayAdapter<String> adapterTipo;
    protected adapter_IdDescription adapterUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_topes);
        setTitle("Registrar topes lista/parlets");

        spn_Usr = findViewById(R.id.id_spn_registrar_tope_listero);
        spn_TipoList = findViewById(R.id.id_spn_registrar_tope_tipo);
        et_Fecha = findViewById(R.id.id_et_registrar_tope_datepicker);
        et_Tope_Fijo = findViewById(R.id.id_et_registrar_tope_fijo);
        et_Tope_Corrido = findViewById(R.id.id_et_registrar_tope_corr);
        et_Tope_Centena = findViewById(R.id.id_et_registrar_tope_centena);
        et_Tope_Parlet = findViewById(R.id.id_et_registrar_tope_parlet);
        ll_block1 = findViewById(R.id.id_ll_registrar_tope_block1);
        ll_block2 = findViewById(R.id.id_ll_registrar_tope_block2);
        ll_block3 = findViewById(R.id.id_ll_registrar_tope_block3);
        btn = findViewById(R.id.id_btn_registrar_tope);

        this.db = new BD(this, "bolita.db", null, 1);
        vUsr = funcSistema.f_obtener_usuario(db);
        adapterTipo = new ArrayAdapter<>(this, R.layout.layout_spn, obj);
        adapterUsr = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, vUsr);
        f_visible(-1);

        spn_TipoList.setAdapter(adapterTipo);
//        spn_TipoList.setSelection(0);
        spn_Usr.setAdapter(adapterUsr);
        et_Fecha.setOnClickListener(view -> CrearDatePicker());
        btn.setOnClickListener(view -> f_validaRegistro());
        et_Fecha.setKeyListener(null);
    }

    @Override
    protected void onStart() {
        f_setSelectUsr();
        f_setSelectTipo();
        super.onStart();
    }

    public void f_setSelectUsr() {
        spn_Usr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pair<Integer, String> item = (Pair<Integer, String>)parent.getSelectedItem();
                f_valida_usr(item.first);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void f_setSelectTipo() {
        spn_TipoList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getSelectedItem().toString();
                f_valida_tipo(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void f_visible(int criterio) {
        switch(criterio) {
            case -1://Oculta todos los bloques
                ll_block1.setVisibility(View.INVISIBLE);
                ll_block2.setVisibility(View.INVISIBLE);
                ll_block3.setVisibility(View.INVISIBLE);
            break;
            case 0:
                ll_block1.setVisibility(View.VISIBLE);
                ll_block2.setVisibility(View.INVISIBLE);
                ll_block3.setVisibility(View.VISIBLE);
            break;
            case 1:
                ll_block1.setVisibility(View.INVISIBLE);
                ll_block2.setVisibility(View.VISIBLE);
                ll_block3.setVisibility(View.VISIBLE);
            break;
        }
    }

    public void f_valida_tipo(String tipo) {
        if(tipo.equals("Jugada")){
            f_visible(0);
        }
        if(tipo.equals("Parlets")){
            f_visible(1);
        }
        //Toast.makeText(this, "Tipo >>> " + tipo, Toast.LENGTH_SHORT).show();
    }
    public void f_valida_usr(int idUsr) {
        //Toast.makeText(this, "Id usr >>> " + idUsr, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("RtlHardcoded")
    private void CrearDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year, month, dayOfMonth;
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear un nuevo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    String m = ( selectedMonth < 10 ) ? "0" + (selectedMonth + 1) : "" + (selectedMonth + 1);
                    String d = ( (selectedDayOfMonth) < 10 ) ? "0" + (selectedDayOfMonth) : "" + (selectedDayOfMonth);
                    String strDate = d + "/" + m + "/"+ selectedYear;
                    et_Fecha.setText(strDate);
                    //f_cargar_resumen();
                },
                year, // Año predeterminado
                month, // Mes predeterminado
                dayOfMonth // Día del mes predeterminado
        );
        ////////Colocar timePicker en una posicion X, Y ///////////////////////////////////
        // Obtener el objeto Window del dialogo
        Window dialogWindow = datePickerDialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT|Gravity.BOTTOM);

        // Mostrar el diálogo
        datePickerDialog.show();
    }

    private void f_validaRegistro() {
        Pair<Integer, String> item = (Pair<Integer, String>)spn_Usr.getSelectedItem();
        String tipo = spn_TipoList.getSelectedItem().toString();
        Logger Logge = Logger.getLogger("TEsssss");
        Logge.log(Level.INFO, ""+ item.second);
        if (item.second.isEmpty()) {
            Toast.makeText(this, "USuario vacio "+item.second, Toast.LENGTH_SHORT).show();
        }
        if (tipo.equals("")) {
            Toast.makeText(this, "No hay tipo", Toast.LENGTH_SHORT).show();
        }
        if (et_Fecha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Fecha cvacia", Toast.LENGTH_SHORT).show();
        }
        if (et_Tope_Fijo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Fijo vacio", Toast.LENGTH_SHORT).show();
        }
        if (et_Tope_Corrido.getText().toString().isEmpty()) {
            Toast.makeText(this, "Corrido vvacio", Toast.LENGTH_SHORT).show();
        }
        if (et_Tope_Centena.getText().toString().isEmpty()) {
            Toast.makeText(this,"Centena vacio", Toast.LENGTH_SHORT).show();
        }
        if (et_Tope_Parlet.getText().toString().isEmpty()) {
            Toast.makeText(this, "PArlet vacio", Toast.LENGTH_SHORT).show();
        }
    }
}