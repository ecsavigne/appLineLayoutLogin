package com.example.applinelayoutlogin.libs;

public final class Exception_personal extends Exception {

   public final int TiempoCumplidoVAlue = 0x00;
   public final String TiempoCumplidoString = "Excepcion de clase hora....e:Tiempo cumplido" + " #" + TiempoCumplidoVAlue;

   public final int OperIzqMenorDerRestHoraValue = 0x01;
   public final String OperIzqMenorDerRestHoraString = "Excepcion de clase hora....e:Hora operando derecha mayor " +
           "que operando izquierda en resta de hora" + " #" + OperIzqMenorDerRestHoraValue;

   public final int FechaRangIncorrectValue = 0x02;
   public final String FechaRangIncorrectString = "Excepcion de clase hora....e:rango incorrecto" + " #" + FechaRangIncorrectValue;

   public final int TimeCumpValue = 0x03;
   public final String TimeCumpString = "Excepcion de clase hora....e:timepo cumplido" + " #" + TimeCumpValue;

   public Exception_personal(String msg){
      super(msg);
   }

}
