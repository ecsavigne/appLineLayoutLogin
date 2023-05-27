package com.example.applinelayoutlogin.libs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class c_hora {
    private int hora, minuto, segundo;
    private String horaString;
    Exception_personal exception = new Exception_personal("");

    public c_hora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        Date date = new Date();
        String hora = dateFormat.format(date);
        String []partsHoras = hora.split(":");

        this.hora = Integer.parseInt(partsHoras[0]);
        this.minuto = Integer.parseInt(partsHoras[1]);
        this.segundo = Integer.parseInt(partsHoras[2]);

        this.horaString = partsHoras[0] + ":" + partsHoras[1] + ":" + partsHoras[2];
    }

    public c_hora(int h, int m, int s) {
        this.hora = h;
        this.minuto = m;
        this.segundo = s;
        String hs, ms, sgs;
        hs = (h / 10 == 0) ? "0" + h : "" + h;
        ms = (m / 10 == 0) ? "0" + m : "" + m;
        sgs = (s / 10 == 0) ? "0" + s : "" + s;

        this.horaString = "" + hs + ":" + ms + ":" + sgs;
    }

    /*Metodos set y get*/

    public int getHora() {return this.hora;}
    public int getMinuto() {return this.minuto;}
    public int getSegundo() {return this.segundo;}
    public String getHoraString() {
        String hs, ms, sgs;
        hs = (this.hora / 10 == 0) ? "0" + this.hora : "" + this.hora;
        ms = (this.minuto / 10 == 0) ? "0" + this.minuto : "" + this.minuto;
        sgs = (this.segundo / 10 == 0) ? "0" + this.segundo : "" + this.segundo;
        this.horaString = "" + hs + ":" + ms + ":" + sgs;
        return this.horaString;
    }
    public void setHora(int h) {this.hora = h;}
    public void setMinuto(int m) {this.minuto = m;}
    public void setSegundo(int s) {this.segundo = s;}
    public void setHoraString(String hs) {this.horaString = hs;}

    /**
     * @param h hora a ser restada a la que ya se programo como principal
     * @return la diferencia de la fecha configurada para cierre con la pasada al momento de empezar la captura
     */

    public c_hora f_restar(c_hora h) throws Exception_personal {
        c_hora temp = new c_hora();
        if(segundo <= h.segundo) {
            if(minuto <= h.minuto) {
                if(hora <= h.hora) {
                    throw new Exception_personal(exception.TimeCumpString);
                }
            }
        }
        //
        temp.hora = this.hora - h.hora;
        if(temp.hora > 0){
            // calculo de segundos finales
            if( minuto < h.minuto) {
                temp.minuto = 60 - h.minuto;
                temp.minuto += minuto;
                temp.hora --;
            } else {temp.minuto = minuto - h.minuto;}
            // calculo de segundo
            if(segundo < h.segundo) {
                temp.segundo = 60 - h.segundo;
                temp.segundo += segundo;
                if(temp.minuto == 0)
                    temp.hora --;
                else
                    temp.minuto --;
            } else { temp.segundo = segundo - h.segundo;}
            if(temp.hora < 0){ throw new Exception_personal(exception.FechaRangIncorrectString + ", #" +
                    exception.FechaRangIncorrectValue);}
        } else {
            if(temp.hora == 0) {
                if( minuto > h.minuto){
                    // calculo de segundo
                    if(segundo < h.segundo) {
                        temp.segundo = 60 - h.segundo;
                        temp.segundo += segundo;
                        temp.minuto = minuto - h.minuto;
                        if(temp.minuto == 0)
                            temp.hora --;
                        else
                            temp.minuto --;
                    } else { temp.segundo = segundo - h.segundo;}
                }
                if(minuto == 0){
                    if(segundo > h.segundo) {
                        temp.segundo = segundo - h.segundo;
                    }
                    else{
                        throw new Exception_personal(exception.FechaRangIncorrectString + ", #" +
                                exception.FechaRangIncorrectValue);
                    }
                }
            }
        }
        return temp;
    }

    /**
     * @param decremento  Valor de la cantidad de segundos a restar en la hora
     * @throws Exception_personal lanza una excepcion para cuando la hora llega a 0 y
     *                    otra para cuando ya la hora esta a 0 inicialmente
     */

    public void f_decre_hora(int decremento) throws Exception_personal {
        if(hora == 0 && minuto == 0 && segundo == 0) {
            throw new Exception_personal(exception.TimeCumpString);
        }
        else if(segundo == 0) {
            if(minuto > 0){
                segundo = 59;
                minuto --;
            }
            if (minuto == 0) {
                if(hora > 0) {
                    hora --;
                    minuto = 59;
                    segundo = 59;
                }
                //noinspection ConstantConditions
                if(hora == 0 && segundo == 0) {
                    throw new Exception_personal(exception.TimeCumpString);
                }
            }
        } else if(segundo > 0){
            segundo -= decremento;
        }
        Logger Logge = Logger.getLogger("TEsssss");
        Logge.log(Level.INFO, getHoraString());
    }

    /**
     *Asigna una c_hora a otra
     * @param h hora que sera asignada
     */
    public void f_asig_hora(c_hora h) {
        setHora(h.getHora());
        setMinuto(h.getMinuto());
        setSegundo(h.getSegundo());
        setHoraString(h.getHoraString());
    }
}
