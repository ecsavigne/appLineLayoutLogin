package com.example.applinelayoutlogin.libs;

import android.os.Handler;
import android.widget.TextView;

import com.example.applinelayoutlogin.MainActivity;

public class reloj implements Runnable{
    int sec;
    protected c_hora hora;
    TextView vTv_Reloj;
    public Handler ejecutor = new Handler();

    public reloj(int seconds, c_hora h, MainActivity a) {
        this.sec = seconds;
        this.hora = h;
        this.vTv_Reloj = a.vTv_Reloj;
    }
    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while(true) {
            ejecutor.post(() -> {
                try {
                    hora.f_decre_hora(1);
                    vTv_Reloj.setText(hora.getHora());
                } catch (Exception e){
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
