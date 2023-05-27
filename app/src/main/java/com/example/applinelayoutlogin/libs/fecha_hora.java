package com.example.applinelayoutlogin.libs;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class fecha_hora {


        public static String f_obtenerHoraActual(String zonaHoraria) {
            String formato = "HH:mm:ss";
            return fecha_hora.f_obtenerFechaConFormato(formato, zonaHoraria);
        }

        public static String f_obtenerFechaActual(String zonaHoraria) {
            String formato = "yyyy-MM-dd";
            return fecha_hora.f_obtenerFechaConFormato(formato, zonaHoraria);
        }

        @SuppressLint("SimpleDateFormat")
        public static String f_obtenerFechaConFormato(String formato, String zonaHoraria) {
            Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone(zonaHoraria));
            Date date = calendar.getTime();
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat(formato);
            sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
            return sdf.format(date);
        }

}
