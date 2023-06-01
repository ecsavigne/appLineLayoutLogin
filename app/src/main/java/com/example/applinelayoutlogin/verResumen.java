package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applinelayoutlogin.adapter.adapter_IdDescription;
import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;
import com.example.applinelayoutlogin.libs.funcSistema;
import com.example.applinelayoutlogin.libs.varGlobals;

import java.util.ArrayList;
import java.util.Calendar;

public class verResumen extends AppCompatActivity {
    protected TextView var_tv_total, var_tv_miporc, var_tv_porcbote, var_tv_porclista;
    protected EditText var_et_hour, var_et_date;
    protected Spinner v_spn_usr;
    protected int mHour, mMinute, year, day, month, dayOfMonth;
    protected BD db;
    protected Pair<Pair<Triplet<Float, Float, Float>, Triplet<Float, Float, Float>>, Pair<Float, Float>> v_totales;
    ArrayList<Pair<Integer, String>> vUsr;
    protected int v_userId;
    protected adapter_IdDescription adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_resumen);
        setTitle("Resumen");

        var_et_hour = findViewById(R.id.id_resumen_et_hour);
        var_et_date = findViewById(R.id.id_resumen_et_date);
        var_tv_miporc = findViewById(R.id.id_resumen_tv_miporc1);
        var_tv_total = findViewById(R.id.id_resumen_tv_total);
        var_tv_porcbote = findViewById(R.id.id_resumen_tv_porcient_bote);
        var_tv_porclista = findViewById(R.id.id_resumen_tv_porcient_lista);
        v_spn_usr = findViewById(R.id.id_sp_resumen_usr);

        this.db = new BD(this, "bolita.db", null, 1);
        v_userId = varGlobals.idUsr;
        vUsr = funcSistema.f_obtener_usuario(db);
        adapter = new adapter_IdDescription(this, R.layout.layout_spn, R.id.id_layout_spn_tv, vUsr);
        v_spn_usr.setAdapter(adapter);

        var_et_hour.setOnClickListener(view -> fCrearTimePickerDialog());
        var_et_date.setOnClickListener(view -> CrearDatePicker());
        if(varGlobals.idGrupoUsr == 2) {
            fChangeItem();
            v_spn_usr.setVisibility(View.VISIBLE);
            fChangeMarginTop(68);
        }
        else{
            v_spn_usr.setVisibility(View.GONE);
            fChangeMarginTop(10);
        }

    }

    @SuppressLint("SetTextI18n")
    private void f_calcular_porciento(int idU) {
//        int idU = (idU == 0) ? v_userId : idU;
        Pair<Integer, Integer> pFijo, pCorr, pParlet, pCentena; // porcientos a pagar
        pFijo = funcSistema.f_obtener_porciento_fijo(this.db, idU);
        pCorr = funcSistema.f_obtener_porciento_corrido(this.db, idU);
        pParlet = funcSistema.f_obtener_porciento_parlet(this.db, idU);
        pCentena = funcSistema.f_obtener_porciento_centena(this.db, idU);
        v_totales = funcSistema.f_obtener_totales_asoc_usr(this.db, idU);
        float total, miPorcLista, miPorcBote, miPorc, totalFijoLista, totalFijoBote, totalCorridoLista, totaCentenaLista,
                totalCorridoBote, totaCentenaBote, totalParletLista, totalParletBote;
        //assert v_totales != null;
        if(v_totales == null) return;
        totalFijoLista = v_totales.first.first.first;
        totalFijoBote =  v_totales.first.second.first;
        totalCorridoLista = v_totales.first.first.second;
        totalCorridoBote = v_totales.first.second.second;
        totaCentenaLista = v_totales.first.first.third;
        totaCentenaBote = v_totales.first.second.third;
        totalParletLista = v_totales.second.first;
        totalParletBote = v_totales.second.second;
        total = (totalFijoLista + totalCorridoLista + totaCentenaLista);// + jugada lista
        total += (totalFijoBote + totalCorridoBote + totaCentenaBote);// + jugada bote
        total += (totalParletLista + totalParletBote);// + parlet lista y bote
//        Porcento em lista
        miPorcLista = (totalFijoLista * pFijo.first)/100 + (totalCorridoLista * pCorr.first)/100 + (totaCentenaLista * pCentena.first)/100;
        miPorcLista +=  (totalParletLista * pParlet.first)/100;
//        porcento em bote
        miPorcBote = (totalFijoBote * pFijo.second)/100 + (totalCorridoBote * pCorr.second)/100 + (totaCentenaBote * pCentena.second)/100;
        miPorcBote +=  (totalParletBote * pParlet.second)/100;
//        porcento total
        miPorc = miPorcBote + miPorcLista;
        Toast.makeText(this, ""+ totalFijoLista + " "+ totalFijoBote+" "+totalCorridoLista, Toast.LENGTH_SHORT).show();

        var_tv_porclista.setText("Porciento Lista : " + funcSistema.f_redon_float(miPorcLista, 2));
        var_tv_porcbote.setText("Porciento Bote : " + funcSistema.f_redon_float(miPorcBote, 2));
        var_tv_miporc.setText("Porciento Total : " + funcSistema.f_redon_float(miPorc, 2));
        var_tv_total.setText("Total :" + total );
    }
    private void fCrearTimePickerDialog() {
        // Set default time to current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    String h = ( hourOfDay < 10 ) ? "0" + hourOfDay : "" + hourOfDay;
                    String m = ( minute < 10 ) ? "0" + minute : "" + minute;
                    String strHora = h + ":" + m;
                    var_et_hour.setText(strHora);
                    if(var_et_date.getText().toString().isEmpty()){
                        Toast.makeText(this, "Debe seleccionar la Fecha para hacer la busqueda", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   f_cargar_resumen();
                }, mHour, mMinute, true);
        ////////Colocar timePicker en una posicion X, Y ///////////////////////////////////
        // Obtener el objeto Window del dialogo
        Window dialogWindow = timePickerDialog.getWindow();
        // Crear un nuevo objeto WindowManager.LayoutParams
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        // Establecer las coordenadas x e y de la ventana
        layoutParams.x = 100; // Ajustar a la posición horizontal deseada
        layoutParams.y = 160; // Ajustar a la posición vertical deseada
        // Establecer el ancho y alto de la ventana
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // Asignar los parametros de diseño a la ventana del diálogo
        dialogWindow.setAttributes(layoutParams);
        /////////////////////////////////////////
        // Mostrar el TimePickerDialog
        timePickerDialog.show();
    }

    @SuppressLint("RtlHardcoded")
    private void CrearDatePicker() {
        Calendar calendar = Calendar.getInstance();
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
                    var_et_date.setText(strDate);
                    f_cargar_resumen();
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
    private void f_cargar_resumen(){
        Pair<Integer, String> item = (Pair<Integer, String>) v_spn_usr.getSelectedItem();
        if (varGlobals.idGrupoUsr == 2) {
            if(item.second.equals("")){
                Toast.makeText(this, "Debe selecionar El Usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            f_calcular_porciento(item.first);
        } else {
            f_calcular_porciento(v_userId);
        }
    }

    private void fChangeItem(){
        v_spn_usr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fRestResumen();
                Pair<Integer, String> item =  (Pair<Integer, String>) parent.getItemAtPosition(position);
                if(var_et_date.getText().toString().isEmpty())
                    return;
                f_calcular_porciento(item.first);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void fRestResumen() {
        var_tv_miporc.setText("");
        var_tv_porclista.setText("");
        var_tv_porcbote.setText("");
        var_tv_total.setText("");
    }

    private void fChangeMarginTop(int dp) {
        //Colocar los margenes
        ViewGroup.MarginLayoutParams lp_Hour = (ViewGroup.MarginLayoutParams)var_et_hour.getLayoutParams();
        ViewGroup.MarginLayoutParams lp_Date = (ViewGroup.MarginLayoutParams)var_et_date.getLayoutParams();
        //coloca 10dp el margin top
        lp_Date.topMargin = dp * (getResources().getDisplayMetrics().densityDpi / 160);
        var_et_date.setLayoutParams(lp_Date);
        lp_Hour.topMargin = dp * (getResources().getDisplayMetrics().densityDpi / 160);
        var_et_date.setLayoutParams(lp_Date);
        var_et_hour.setLayoutParams(lp_Hour);
    }
}