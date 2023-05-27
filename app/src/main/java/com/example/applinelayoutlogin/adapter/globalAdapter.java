package com.example.applinelayoutlogin.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

//Clase para manejar los elementos del adapter_lv_multiple de forma global
public class globalAdapter{
        /**
         * indexSelect es el indice anteriormemnte seleccionado
         * index_marca es el indice seleccionado actualmente
         * columna numero, fijo o corrido
         */
        static public int indexSelect = -1, index_marca = -1, columna = -1;
        static public boolean es_apuesta = false;
        @SuppressLint("StaticFieldLeak")
        static public nodeTextView ultimoMarcado;
        static public nodeTextView[] listFixe = new nodeTextView[15],
                                        listRunnered = new nodeTextView[15],
                                        listNumber = new nodeTextView[15];
        static public String[] num = new String[15], fijo = new String[15], corrido = new String[15];
        /**
         * tv_marcado = es el texView Marcado que esta dentro del listView
         * mult_apuesta = TextView de Apuesta
         */
        @SuppressLint("StaticFieldLeak")
        static public TextView tv_marcado, mult_apuesta;
         //Metodos
        /**
         * Agrega textView fijo a su correspondiente array
         * */
        static public void addFixe(int position, TextView tv) {
           listFixe[position].tv = tv;
           listFixe[position].f_unmark();
        }
        /**
         * Agrega textView Runnered a su correspondiente array
         * */
        static public void addRunnered(int position, TextView tv) {
           listRunnered[position].tv = tv;
           listRunnered[position].f_unmark();
        }
        /**
         * Agrega textView Number a su correspondiente array
         * */
        static public void addNumber(int position, TextView tv) {
           listNumber[position].tv = tv;
           listNumber[position].f_unmark();
        }

        /**
         *
         * @param tv TextView en el que sa da clic
         * @param position position under listview
         * @param Col Determina si es fijo, corrido o numero
         * @return tv Elemento sobre el cual se le da click
         */
        @SuppressWarnings("UnusedReturnValue")
        static public TextView f_mark_in_list(TextView tv, int position, int Col) {
                nodeTextView temp = new nodeTextView(tv);
                if( index_marca == -1 && columna == -1){
                        indexSelect = index_marca  = position;
                        columna = Col;
                        switch (Col){ //Marcar si no se ha marcado ninguno
                                case 1:
                                       temp.f_mark();
                                       listNumber[position] = temp;
                                break;
                                case 2:
                                        temp.f_mark();
                                        listFixe[position] = temp;
                                break;
                                case 3:
                                        temp.f_mark();
                                        listRunnered[position] = temp;
                                break;
                        }

                }else { //Desmarcar
                        switch (columna){
                                case 1:
                                        temp = listNumber[index_marca];
                                        temp.f_unmark();
                                        listNumber[index_marca] = temp;
                                break;
                                case 2:
                                        temp = listFixe[index_marca];
                                        temp.f_unmark();
                                        listFixe[index_marca] = temp;
                                break;
                                case 3:
                                        temp = listRunnered[index_marca];
                                        temp.f_unmark();
                                        listRunnered[index_marca] = temp;
                                break;
                        }
                        columna = Col;
                        switch (Col){// Marcar
                                case 1:
                                        temp = listNumber[position];
                                        temp.f_mark();
                                        listNumber[position] = temp;
                                        indexSelect = index_marca;
                                        index_marca = position;
                                break;
                                case 2:
                                        temp = listFixe[position];
                                        temp.f_mark();
                                        listFixe[position] = temp;
                                        indexSelect = index_marca;
                                        index_marca = position;
                                        break;
                                case 3:
                                        temp = listRunnered[position];
                                        temp.f_mark();
                                        listRunnered[position] = temp;
                                        indexSelect = index_marca;
                                        index_marca = position;
                                        break;
                        }
                }
                ultimoMarcado = temp;
                tv_marcado = tv;
                return tv;
        }
}
