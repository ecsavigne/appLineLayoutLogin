package com.example.applinelayoutlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.*;
import com.example.applinelayoutlogin.libs.c_hora;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView vTv_Reloj;
    protected Button vBtn_Ingresar;
    protected MainActivity activity;
    protected EditText vEtxt_usr, vEtxt_pass;
    BD bd;
    protected Thread hilo;
    Handler ejecutor = new Handler();
    protected Cursor result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");

        this.bd = new BD(this, "bolita.db", null, 1);

        this.vTv_Reloj = findViewById(R.id.cmpTv_Reloj);
        this.vBtn_Ingresar = findViewById(R.id.id_btn_login_ingresar);
        this.vEtxt_usr = findViewById(R.id.id_txt_login_usuario);
        this.vEtxt_pass = findViewById(R.id.id_txt_login_password);

        this.activity = this;

        this.vBtn_Ingresar.setOnClickListener(this.activity);
        c_hora h_final = new c_hora(12, 14, 0);
        c_hora h_actual = new c_hora();
        c_hora h_temp = new c_hora();
        try {
            h_temp.f_asig_hora(h_final.f_restar(h_actual));
            //startThread(h_temp);
        } catch ( Exception_personal e){
            Logger Logge = Logger.getLogger("TEsssss");
            Logge.log(Level.INFO, ""+ e);
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_btn_login_ingresar) {
            if(vEtxt_usr.getText().toString().isEmpty() || vEtxt_pass.getText().toString().isEmpty()) {
                Toast.makeText(activity, "Debe ingresar usuario o contraseña", Toast.LENGTH_SHORT).show();
            }
            else {
                if( funcSistema.f_logger(bd, vEtxt_usr, vEtxt_pass)){
                    //Obtener tipo de usuario dado el nombre de usuario\
                    String sql = "select tu.tipoUsuario, u.idPersona, tu.idTipoUsuario from TipoUsuario as tu, Usuario as u \n" +
                            "where tu.idTipoUsuario = u.idTipoUsuario and " +
                            "u.nombreUsuario = \""+vEtxt_usr.getText().toString()+"\";";
                    this.result = this.bd.f_select_raw(sql);
                    this.result.moveToFirst();

                    //Intent vSgte = new Intent(activity,  principal.class);
                    Intent vSgte = new Intent(activity,  user_multiple_jugadas.class);
                    varGlobals.nameGrupoUsr = this.result.getString(0); // Nombre de grupo
                    varGlobals.idGrupoUsr =  this.result.getInt(2);//tipo de usuario
                    varGlobals.nameUsr = vEtxt_usr.getText().toString();//Nombre de usuario
                    varGlobals.idUsr = this.result.getInt(1); // Id Usr
                    vEtxt_usr.setText("");
                    vEtxt_pass.setText("");

                    startActivity( vSgte );
                }
                else {
                    Toast.makeText(activity, "Usuario o contraseña incorrecta. O el usuario esta bloqueado", Toast.LENGTH_SHORT).show();
                    vEtxt_usr.setText("");
                    vEtxt_pass.setText("");
                }
            }
        }

    }

    public void startThread(c_hora hora) {
        ejecutor = new Handler();
        reloj reloj = new reloj(1000, hora);
        hilo = new Thread(reloj, "Timer");
        hilo.start();

    }



    class reloj implements Runnable{
        int sec;
        c_hora hora;

        reloj(int seconds, c_hora h) {
            this.sec = seconds;
            this.hora = h;
        }
        @Override
        public void run() {
            //noinspection InfiniteLoopStatement
            while(true) {
                ejecutor.post(() ->  /*synchronized (this) */{
                   try {
                       hora.f_decre_hora(1);
                       vTv_Reloj.setText(hora.getHoraString());
                   } catch (Exception_personal e){
                       e.printStackTrace();
                   }
                });
                try{
                    //noinspection BusyWait
                    Thread.sleep(sec);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}