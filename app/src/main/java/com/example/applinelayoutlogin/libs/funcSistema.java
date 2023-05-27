package com.example.applinelayoutlogin.libs;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Pair;
import android.widget.EditText;

import com.example.applinelayoutlogin.libs.BD.BD;
import com.example.applinelayoutlogin.libs.MyTuples.Quartet;
import com.example.applinelayoutlogin.libs.MyTuples.Quintet;
import com.example.applinelayoutlogin.libs.MyTuples.Triplet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

public class funcSistema {
    static protected Cursor res;

    /**
    Esta funcion devuelve todos los numeros que terminan en
    @param u -> Valor de la unidad que conforman los numeros
    */
    static public ArrayList<Quintet<Integer, Integer, Float, Float, Float>> f_craer_num_unidades(int u, float valFijo, float valCorr) {
        ArrayList<Quintet<Integer, Integer, Float, Float, Float>> nums = new ArrayList<>();

       for(int i = 0; i < 10; i++) {
           Quintet<Integer, Integer, Float, Float, Float> temp = new Quintet<>(0, ((i * 10) + u), valFijo, valCorr, (float)0);
           nums.add(temp);
       }
       return nums;
    }

    /**
     * Esta funcion devuelve un arreglo con todos los numeros que tinen como
     * decena el parametro pasado
     *@param d Valor de la decena que conforman los numeros
     * @param valFijo valorFijo para cada decena
     * @param valCorr valor de corrido para cada decena
     * @return  ArrayList de Quintet(centena, fijo, valorFijo, ValorCorrido, ValorCentena)
     */
    static public ArrayList<Quintet<Integer, Integer, Float, Float, Float>> f_craer_num_decena(int d, float valFijo, float valCorr) {
        ArrayList<Quintet<Integer, Integer, Float, Float, Float>> nums = new ArrayList<>();

       for(int i = 0; i < 10; i++) {
           Quintet<Integer, Integer, Float, Float, Float> temp = new Quintet<>(0, ((d * 10) + i), valFijo, valCorr, (float)0);
           nums.add(temp);
       }
       return nums;
    }

    /**
     * f_idConjuntoJugada() : int
     * Ejecuta una consulta de insercion en la tabla ConjuntoJugadas
     * Retorna el valor que me devuelve la consulta
     * @return guery ----------> -1 si error o id del registro insertado
     */
    static public int f_idConjuntoJugada(BD db){
        //SQLiteDatabase handlerQuery = db.getWritableDatabase();
        ContentValues conten = new ContentValues();
        conten.put("descripcion","idDescripcion");
        return  (int)db.f_get_handler().insert("ConjuntoJugada", null, conten);

    }

    /**
     *Dado una tabla hash<Strin, String> debuelve arreglo String de valores
     * @param h es el Hash con los valores donde los valores serian los campos a mostrar
     * */
    static public String[] f_get_value_hashtable(Hashtable<String, String> h) {
        String[] obj;
        obj = new String[h.values().size()];
        int i = 0;
        for (String s : h.values()) {
            obj[i] = s;
            i++;
        }
        return obj;
    }

    /**
     * Obtiene una clave de una tablaHAsh dado su valor
     * @param hash --> tabla hash donde se buscara la Llave
     * @param value --> Valor el cual se le busca su clave
     * @param <K> --> Valor Generico de la clave en objeto Hashtable
     * @param <V> --> Valor Generico de la valor en objeto Hashtable
      * @return   --> Retorna la clave del valor pasado y Null si no existe la clave
     */
    static public  <K, V> K f_obtener_clave(Hashtable<K, V> hash, V value) {
        for (Hashtable.Entry<K, V> entry : hash.entrySet())
            if (Objects.equals(value, entry.getValue())) return entry.getKey();
        return null;
    }

    /**
     * Function que permite acceso al sistema
     * @param db BAse de datos del sistema
     * @param usr Usr  a buscar en el sistema
     * @param pass Contraseña del Usuario
     * @return True si el usuario existe en el sistema y coincide el pass
     */
    static public boolean f_logger(BD db, EditText usr, EditText pass) {
        String[] args;
        args = new String[]{"count(idPersona) as count", "nombreUsuario = '" + usr.getText().toString() + "' and " +
                "pass = '" + pass.getText().toString() + "' and isBlock = 0", ""};

        res = db.crud_read("Usuario",args );
        res.moveToFirst();
        return (Integer.parseInt(res.getString(0)) != 0) ;
    }

    /**
     * Funcion que borra el ultimo caracter de una cadena
     * @param str Cadena la cual se copia sin el ultimo char
     * @return cadena temp con sin el ultimo char de la cadena entrada
     */
    static public String f_borra_char_str(String str) {
        String temp = "";
        char c;
        for(int i = 0; i < str.length() - 1; i ++) {
            c = str.charAt(i);
            temp = temp.concat(""+c);
            //temp += "" + c;
        }
        return temp;
    }

    /**
     *
     * @param db Objeto para establecer la conexion con la BD
     * @param tabla tabla a operar
     * @param cond criterio para contar
     * @return cantidad de veces que se cumple el criterio
     */
    static public int f_contar_ocurrencia(BD db, String tabla, String cond) {
        String condition = (cond.isEmpty()) ? ";" : "where " + cond + ";";
        String sql = "Select count(*) from " + tabla + " " + condition;
        boolean eof;
        res = db.f_select_raw(sql);
        res.moveToFirst();
        return Integer.parseInt(res.getString(0));
    }

    /**
     * Esta funcion modifica el la fecha de fin de una tarifa de pago(La cierra para crear otra si existe)
     * @param db Objeto para establecer la conexion con la BD
     * @param idTipoJugada id la tarifa de pago que se verificara si no tiene fecha fin para cerrar
     */
    static public void f_modificar_fechaFin_tarifaPago(BD db, int idTipoJugada) {
        ContentValues reg = new ContentValues();
        reg.put("fechaFin", fecha_hora.f_obtenerFechaActual("GMT-5"));
        db.update("TarifaPago", reg, "idTipoJugada = "+ idTipoJugada + " " +
                "and fechaFin IS NULL;");
    }
    /**
     * Esta funcion modifica el estado de bloqueo de un usuario
     * @param db Objeto para establecer la conexion con la BD
     * @param isBlock Si se bloquea o no el usuario
     * @param idUsr Usuario a bloquear
     * @return cantidad de columnas a modificadas
     */
    static public int f_modificar_estado_block(BD db, boolean isBlock, int idUsr) {
        ContentValues colModif = new ContentValues();
        int block = (isBlock) ? 1 : 0;
        colModif.put("isBlock", block);
        return db.update("Usuario", colModif, "idPersona = "+ idUsr);
    }

    /**
     * Funcion que genera dado una lista de numeros gra los parlets posibles
     * @param nums Lista de nu
     * @return arreglo con todos los parlet generados o Null si el arreglo pasado(nums) tiene 1 solo
     *         elemento
     */
    static public ArrayList<Pair<Integer, Integer>> f_obtener_parlets(String[] nums) {
        //Pair<String, String>[] candado;
        ArrayList<String> numCloneNoEmpty = f_obtener_no_empty(nums);
        int len = numCloneNoEmpty.size();
        //Para formar los parlets
        if(len == 1 || len == 0)
            return null;//Reteornar una exepcion de que debe existir mas de 1 numero
        ArrayList<Pair<Integer, Integer>> candado = new ArrayList<>();
        for(int i = 0; i < len - 1; i++)
            for (int j = i + 1; j < len; j++) {
                Pair<Integer, Integer> Temp = new Pair<>(Integer.parseInt(numCloneNoEmpty.get(i)), Integer.parseInt(numCloneNoEmpty.get(j)));
                candado.add(Temp);
            }
        return candado;
    }

    /**
     * Esta funcion obtiene todas las jugadas no vacias es decir que tengan numero para jugar no importa
     * apuesta si no apuesta le pone 0
     * @param nums arreglo de nums en formato de Str
     * @param fijo arreglo de valor fijo en formato Str
     * @param corr arreglo de valor Corrido en formato Str
     * @return ArrayList<Triplet<Integer, Integer, Float, Float, Float>> donde el primero es el
     *         (Centena, Numero, Valor Fijo, Corrido, valorCentena) respectivamente.
     *         Y ret Null en caso de q las longitudes de los array de entrada no sea igual
     */
    static private ArrayList<Quintet<Integer, Integer, Float, Float, Float>> f_obtener_jugada_no_empty(String[] nums, String[] fijo, String[] corr) {
        int lnNum = nums.length, lnFij = fijo.length, lnCorr = corr.length;
        ArrayList<Quintet<Integer, Integer, Float, Float, Float>> result = new ArrayList<>();
        if ( lnFij == lnCorr && lnCorr == lnNum) {
            for(int i = 0; i < lnFij; i ++)
                if (!nums[i].equals("")) {
                    Quintet<Integer, Integer, Float, Float, Float> temp;
                    temp = new Quintet<>(0,
                            Integer.parseInt(nums[i]),
                            (fijo[i].equals("")) ? (float) 0 : Float.parseFloat(fijo[i]),
                            (corr[i].equals("")) ? (float) 0 : Float.parseFloat(corr[i]),
                            (float) 0);
                    result.add(temp);
                }
            return result;
        }
        return null;
    }

    /**
     * Esta funcion obtine todas las jugadas, se llama dentro de ella a f_obtener_jugada_no_empty
     * @param nums arreglo de nums en formato de Str
     * @param fijo arreglo de valor fijo en formato Str
     * @param corr arreglo de valor Corrido en formato Str
     * @return ArrayList<Triplet < Integer, Float, Float>> donde el primero es el numero y el 2 y 3ro valores fijo y
     *          corrido respectivamente. Y ret Null en caso de q las longitudes de los array de entrada no sea igual
     */
    static public ArrayList<Quintet<Integer, Integer, Float, Float, Float>> f_obtener_grupo_jugada(String[] nums, String[] fijo, String[] corr) {
        ArrayList<Quintet<Integer, Integer, Float, Float, Float>> array_num_fij_corr;
        array_num_fij_corr = f_obtener_jugada_no_empty(nums, fijo, corr);
        return array_num_fij_corr;
    }

    /**
     *
     * @param s Lista de numeros del candado
     * @return Retorna el arreglo de numeros pero solo lo que tienen elementos distinct od ""
     */
    static public ArrayList<String> f_obtener_no_empty(String[] s) {
        ArrayList<String> s1 = new ArrayList<>();
        for(String value : s) if (!value.equals("")) s1.add(value);
        return s1;
    }

    /**
     *Esta funcion obtiene los limites de recogida de un listero en cuanto a fijo, corrido y centena
     * @param db Objeto para establecer la conexion con la BD
     * @param idUsr id de usuario Ligado al sistema
     * @return retona Null si no hay limite y sino los Limites(idTopeLista_Jugada ,Limite de centena, limite fijos, limite corrido)
     */
    static private Quartet<Integer, Float, Float, Float> f_obtener_limite_numeros_listero(BD db, int idUsr) {
        boolean eof;
        Quartet<Integer, Float, Float, Float> limites;
        res = db.f_select_raw("select T.idTopeLista_jugada, T.topeCentena, T.topeFijo, T.topeCorrido \n" +
                            "from TopeLista_jugada AS T \n" +
                                    "where T.idPersona = "+ idUsr +" and T.fechaFin ISNULL; ");
        eof = res.moveToFirst();
        //No existen limites para el usuario
        if(!eof) return null;
        limites = new Quartet<>(
                Integer.parseInt(res.getString(0)),//Id de TopeLista_jugada
                Float.parseFloat(res.getString(1)),//T.topeCentena
                Float.parseFloat(res.getString(2)),//T.topeFijo
                Float.parseFloat(res.getString(3))//T.topeCorrido
        );
       return limites;
    }

    /**
     *
     *Esta funcion obtiene los limites de recogida de un listero en cuanto a los parlets
     * @param db Objeto para establecer la conexion con la BD
     * @param idUsr id de usuario Ligado al sistema
     * @return retona Null si no hay limite y sino los Limites(idTopeLista_Parlets ,tope o limite a recoger ppor parlets)
     */
    static private Pair<Integer, Float> f_obtener_limite_parlets_listero(BD db, int idUsr) {
        boolean eof;
        Pair<Integer, Float> limites;
        res = db.f_select_raw("select T.idTopeLista_parlet, T.tope from TopeLista_parlet AS T " +
                "where T.idPersona = " + idUsr + " and T.fechaFin ISNULL");
        eof = res.moveToFirst();
        //No existen limites para el usuario
        if(!eof) return null;
        limites = new Pair<>(
                Integer.parseInt(res.getString(0)),//Id de TopeLista_Parlets
                Float.parseFloat(res.getString(1))//Tope o limite a recoger por parlets
        );
       return limites;
    }

    /**
     * Esta funcion retorna todos los totales (jugada no de bote(Float, Float, Float), jugada de Bote
     * (Float, Float, Float), parlet de lista(Float) y Parlet de bote(Float) )
     * @param db Objeto para establecer la conexion con la BD
     * @param idUsr id de usuario Ligado al sistema
     * @return Pair<Pair<Triplet<Float, Float, Float>, Triplet<Float, Float, Float>>, Pair<Float, Float>>
     *              la 1ra Tripleta es Total de Jugada lista
     *              la 2da Tripleta es Total de Jugada de Bote
     *                  1er elem de tripleta Fijo
     *                  2do elem de tripleta Corrido
     *                  3er elem de tripleta Centena
     *              el Primer Float del 2do Par es Total de parlet lista
     *              el Primer Float del 2do Par es Total de parlet de Bote
     */
    static public Pair<Pair<Triplet<Float, Float, Float>, Triplet<Float, Float, Float>>, Pair<Float, Float>> f_obtener_totales_asoc_usr(BD db, int idUsr){
        Triplet<Float, Float, Float> jugadaNoBote, jugadaBote;
        float parletLista, parletBote;
        //Obtener total de Jugadas que no son de bote asociada a idUsr
        res = db.f_select_raw("select sum(tab.fijo), sum(tab.corr), sum(tab.centena) from (select cj.idConjuntoJugada, sum(j.valorFijo) as fijo, sum(j.valorCorrido) as corr, sum(j.valorCentena) as centena from Jugada as j, Jugada_TopeLista as jtl, ConjuntoJugada as cj, TopeLista_jugada as tlj\n" +
                "     where (j.idJugada = jtl.idJugada and jtl.idConjuntoJugada = cj.idConjuntoJugada and  tlj.idTopeLista_jugada = jtl.idTopeLista_jugada ) and\n" +
                "     (jtl.isBote = 0 and tlj.idPersona = " + idUsr + ")\n" +
                "     group by cj.idConjuntoJugada) as tab;");
        res.moveToFirst();
        if(res.getString(0) == null || res.getString(1) == null || res.getString(2) == null)
            return null;
        jugadaNoBote = new Triplet<>(Float.parseFloat(res.getString(0)),//Total de fijo
                Float.parseFloat(res.getString(1)),//Total Corrido
                Float.parseFloat(res.getString(2)));//Total Centena
        //Obtener total de Jugadas que son de bote asociada a idUsr
        res = db.f_select_raw("select sum(tab.fijo), sum(tab.corr), sum(tab.centena) from (select cj.idConjuntoJugada, sum(j.valorFijo) as fijo, sum(j.valorCorrido) as corr, sum(j.valorCentena) as centena from Jugada as j, Jugada_TopeLista as jtl, ConjuntoJugada as cj, TopeLista_jugada as tlj\n" +
                "     where (j.idJugada = jtl.idJugada and jtl.idConjuntoJugada = cj.idConjuntoJugada and  tlj.idTopeLista_jugada = jtl.idTopeLista_jugada ) and\n" +
                "     (jtl.isBote = 1 and tlj.idPersona = " + idUsr + ")\n" +
                "     group by cj.idConjuntoJugada) as tab;");
        res.moveToFirst();
        jugadaBote = new Triplet<>(Float.parseFloat(res.getString(0)),//Total de fijo
                Float.parseFloat(res.getString(1)),//Total Corrido
                Float.parseFloat(res.getString(2)));//Total Centena
        //Obtener total de Parlets que no son de bote asociada a idUsr
        res = db.f_select_raw("select sum(tab.parlet) from (select cj.idConjuntoJugada, round(sum(p.valorParlet)) as parlet from Parlet as p, Parlet_TopeLista as ptl, ConjuntoJugada as cj, TopeLista_parlet as tlp\n" +
                "     where (p.idParlet = ptl.idParlet and ptl.idConjuntoJugada = cj.idConjuntoJugada and  tlp.idTopeLista_parlet = ptl.idTopeLista_parlet ) and\n" +
                "     (ptl.isBote = 0 and tlp.idPersona = " + idUsr + ")\n" +
                "     group by cj.idConjuntoJugada) tab;");
        res.moveToFirst();
        parletLista = Float.parseFloat(res.getString(0));
        //Obtener total de Parlets que son de bote asociada a idUsr
        res = db.f_select_raw("select sum(tab.parlet) from (select cj.idConjuntoJugada, round(sum(p.valorParlet)) as parlet from Parlet as p, Parlet_TopeLista as ptl, ConjuntoJugada as cj, TopeLista_parlet as tlp\n" +
                "     where (p.idParlet = ptl.idParlet and ptl.idConjuntoJugada = cj.idConjuntoJugada and  tlp.idTopeLista_parlet = ptl.idTopeLista_parlet ) and\n" +
                "     (ptl.isBote = 1 and tlp.idPersona = " + idUsr + ")\n" +
                "     group by cj.idConjuntoJugada) tab;");
        res.moveToFirst();
        parletBote = Float.parseFloat(res.getString(0));

        return new Pair<>(new Pair<>(jugadaNoBote, jugadaBote), new Pair<>(parletLista, parletBote));
    }

    /**
     * Esta funcion obtiene el total jugado en fijo y corrido de un numero perteneciente a un listero
     * y es el tope vigente una fecha dada(la condicion de la fecha no esta agregadas) y no es de bote
     * @param db Manejador de la BAse datos
     * @param idUsr db Listero en el sistema
     * @param Num numero a buscar
     * @param idTope Tope de lista actual para el listero
     * @return Par de valor Suma de fijo y corrido recogido para el numero dado
     */
    static private Pair<Float, Float> f_obtener_jugado_fijo_corrido(BD db, int Num, int idTope, int idUsr){
        Pair<Float, Float> jugado;
        res = db.f_select_raw("select sum(J.valorFijo) as fijoJugado, sum(J.valorCorrido) as corrJugado \n" +
                "from Jugada as J, TopeLista_jugada as t, Jugada_TopeLista as jt\n" +
                "where(J.idJugada = jt.idJugada and jt.idTopeLista_jugada = t.idTopeLista_jugada) and\n" +
                "     (J.numero = "+ Num +" and t.idTopeLista_jugada = "+ idTope +") and (t.idPersona = "+idUsr+" and jt.isBote = 0);");

        res.moveToFirst();
        float sumFijo, sumCorrido;
        sumFijo = (res.getString(0) == null) ? (float)0 : Float.parseFloat(res.getString(0));
        sumCorrido = (res.getString(1) == null) ? (float)0 : Float.parseFloat(res.getString(1));
        jugado = new Pair<>(sumFijo, sumCorrido);
        return jugado;
    }

    /**
     *Esta funcion obtiene el total jugado en centena perteneciente a un listero
     *  y es el tope vigente una fecha dada(la condicion de la fecha no esta agregadas) y no es de bote
     * @param db Manejador de la BAse datos
     * @param idUsr  Listero en el sistema
     * @param centena centena a buscar
     * @param idTope Tope de lista actual para el listero
     * @return valor de centena Suma de centena recogida
     */
    static private float f_obtener_jugado_centena(BD db, int centena, int idTope, int idUsr){
        res = db.f_select_raw("select sum(J.valorCentena) as fijoJugado \n" +
                "from Jugada as J, TopeLista_jugada as t, Jugada_TopeLista as jt\n" +
                "where(J.idJugada = jt.idJugada and jt.idTopeLista_jugada = t.idTopeLista_jugada) and\n" +
                "     (J.centena = "+ centena +" and t.idTopeLista_jugada = "+ idTope +") and (t.idPersona = " + idUsr + " and jt.isBote = 0);");

        res.moveToFirst();
        return (res.getString(0) == null) ? (float)0 : Float.parseFloat(res.getString(0));
    }

    /**
     * Esta funcion obtiene el total jugado de 1 parlets perteneciente a un listero
     * @param db  Manejador de la BAse datos
     * @param num1 Primer numero del parlets
     * @param num2 Segundo Numero del PArlets
     * @param idTope Tope de lista actual para el listero segun los parlets
     * @param idUsr Listero en el sistema
     * @return retorna la cantidad jugada para el parlet dado
     */
    static private float f_obtener_jugado_parlets(BD db, int num1, int num2, int idTope, int idUsr){
        res = db.f_select_raw("select sum(P.valorParlet) as parletJugado from Parlet as P, TopeLista_parlet as t, Parlet_TopeLista as pt \n" +
                "where(P.idParlet = pt.idParlet and pt.idTopeLista_parlet = t.idTopeLista_parlet) and \n" +
                "(((P.num1 = " + num1 + " and P.num2 = " + num2 + ") or (P.num1 = " + num2 + " \n" +
                "and P.num2 = " + num1 +")) and t.idTopeLista_parlet = "+ idTope + ") and \n" +
                "(t.idPersona = " + idUsr + " and pt.isBote = 0);");

        res.moveToFirst();
        return (res.getString(0) == null) ? (float)0 : Float.parseFloat(res.getString(0));
    }

    /**
     * Esta fcuncion devuelve la lista de jugada es decir que no pertenecen al bote o a la lista
     * @param db Objeto para establecer la conexion con la BD
     * @param idUsr usuario en cuestion
     * @param isBot 1 si la jugada a obtener son de bote y 0 si son lista
     * @return arreglo de Pair donde Pair.first = al idConjunto de jugada y
     *              Pair.second = arreglo de todas las jugadas que pertenecen a  ese grupo o conjunto
     *              de jugada
     */
    static public ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> f_obtener_jugada_list(BD db, int idUsr, int isBot) {
        ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> jug = new ArrayList<>();
        boolean eof;
        int id, pos;
        res = db.f_select_raw("select cj.idConjuntoJugada, j.* " +
                        "from Jugada as j, Jugada_TopeLista as jtl, ConjuntoJugada as cj, TopeLista_jugada as tlj " +
                "where (j.idJugada = jtl.idJugada and jtl.idConjuntoJugada = cj.idConjuntoJugada and  tlj.idTopeLista_jugada = jtl.idTopeLista_jugada ) " +
                "and (jtl.isBote = " + isBot + " and tlj.idPersona = " + idUsr + ") " +
                "order by cj.idConjuntoJugada;");
        eof = res.moveToFirst();
        while(eof) {
            Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>> item;
            Quintet<Integer, Integer, Float, Float, Float> temp = new Quintet<>(
                    Integer.parseInt(res.getString(2)),//Centena
                    Integer.parseInt(res.getString(3)),//Num
                    Float.parseFloat(res.getString(4)),//Valor Fijo
                    Float.parseFloat(res.getString(5)),//Valor Corrido
                    Float.parseFloat(res.getString(6)));//Valor centena
            id = Integer.parseInt(res.getString(0));
            pos = f_obtener_pos_id(id, jug);
            if(pos == -1) {
                ArrayList<Quintet<Integer, Integer, Float, Float, Float>> arrTemp = new ArrayList<>();
                arrTemp.add(temp);
                item = new Pair<>(
                        id,//idConjunto
                        arrTemp
                );
                jug.add(item);
            }else {
                jug.get(pos).second.add(temp);
            }
            eof = res.moveToNext();
        }
        return jug;
    }
    /**
     *Esta fcuncion devuelve la lista de jugada es decir que no pertenecen al bote o a la lista
     * @param db Objeto para establecer la conexion con la BD
     * @param idUsr id de usuario Ligado al sistema
     * @param isBot 1 si la jugada a obtener son de bote y 0 si son lista
     * @return arreglo de Pair donde Pair.first = al idConjunto de jugada y
     *      *              Pair.second = arreglo de todas las jugadas tipo parlets que pertenecen a  ese grupo o conjunto
     *      *              de jugada
     */
    static public ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> f_obtener_parlet_list(BD db, int idUsr, int isBot) {
        ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> jug = new ArrayList<>();
        boolean eof;
        int id, pos;
        res = db.f_select_raw("select cj.idConjuntoJugada, p.idParlet, p.num1, p.num2, round(p.valorParlet, 3) " +
                "from Parlet as p, Parlet_TopeLista as ptl, ConjuntoJugada as cj, TopeLista_parlet as tlp\n" +
                "     where (p.idParlet = ptl.idParlet and ptl.idConjuntoJugada = cj.idConjuntoJugada and  tlp.idTopeLista_parlet = ptl.idTopeLista_parlet ) and\n" +
                "     (ptl.isBote = " + isBot + " and tlp.idPersona = " + idUsr + ")\n" +
                "     order by cj.idConjuntoJugada;");
        eof = res.moveToFirst();
        while(eof) {
            Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>> item;
            Triplet<Integer, Integer, Float> temp = new Triplet<>(
                    Integer.parseInt(res.getString(2)),//Num1
                    Integer.parseInt(res.getString(3)),//Num2
                    Float.parseFloat(res.getString(4))//Valor parlet
                    );
            id = Integer.parseInt(res.getString(0));
            pos = f_obtener_pos_id2(id, jug);
            if(pos == -1) {
                ArrayList<Triplet<Integer, Integer, Float>> arrTemp = new ArrayList<>();
                arrTemp.add(temp);
                item = new Pair<>(
                        id,//idConjunto
                        arrTemp
                );
                jug.add(item);
            }else {
                jug.get(pos).second.add(temp);
            }
            eof = res.moveToNext();
        }
        return jug;
    }
    /**
     * Esta fucnion obtiene dado un id la posicion del elemento que contiene ese id
     * @param idConj es el id a buscar en array.get(idConjunto).first
     * @param jug Arreglo de jugada asociadas a un idConjunto
     * @return posicion del elemento del arreglo de Pair con Pair.first = idConj y Pair.second = arreglo de jugad tipo Quintet,
     *              si no existe retorna -1
     */
    static public int f_obtener_pos_id(int idConj, ArrayList<Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>>> jug) {
       boolean band = false;
       int i = -1;
       for(Pair<Integer, ArrayList<Quintet<Integer, Integer, Float, Float, Float>>> item : jug) {
           i ++;
           if (item.first == idConj) {
               band = true;
               break;
           }
       }
       if(!band) {
           return -1; // No existe
       }
       return i;
    }
    /**
     *
     * @param idConj es el id a buscar en array.get(idConjunto).first
     * @param jug Arreglo de jugada asociadas a un idConjunto
     * @return posicion del elemento del arreglo de Pair con Pair.first = idConj y Pair.second = arreglo de jugad tipo Triplet,
     *              si no existe retorna -1
     */
    static public int f_obtener_pos_id2(int idConj, ArrayList<Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>>> jug) {
       boolean band = false;
       int i = -1;
       for(Pair<Integer, ArrayList<Triplet<Integer, Integer, Float>>> item : jug) {
           i ++;
           if (item.first == idConj) {
               band = true;
               break;
           }
       }
       if(!band) {
           return -1; // No existe
       }
       return i;
    }

    /**
     * Esta funcion obtiene todos los tipos de usuarios y devuelve un ArrayList de Pair<Integer, String> donde Integer es el idTipo
     * y String es el tipo de Usuario
     * @param db Objeto para establecer la conexion con la BD
     * @return ArrayList de Pair<Integer, String> donde Integer es el idTipo
     *         y String es el tipo de Usuario
     */
    static public ArrayList<Pair<Integer, String>> f_obtener_tipoUsuario(BD db) {
        Pair<Integer, String> temp;
        boolean eof;
        ArrayList<Pair<Integer, String>> arr = new ArrayList<>();
        res = db.f_select_raw("select * from TipoUsuario\n" +
                "order by tipoUsuario.tipoUsuario;");
        eof = res.moveToFirst();
        arr.add(new Pair<>(0, ""));//Primer elemento vacio
        while(eof) {
            arr.add(new Pair<>(Integer.parseInt(res.getString(0)), res.getString(1)));
            eof = res.moveToNext();
        }
        return arr;
    }
    /**
     * Esta funcion obtiene todos los tipos de jugadas y devuelve un ArrayList de Pair<Integer, String> donde Integer es el idTipo
     * y String es el tipo de Jugada
     * @param db Objeto para establecer la conexion con la BD
     * @return ArrayList de Pair<Integer, String> donde Integer es el idTipo
     *         y String es el tipo de Jugada
     */
    static public ArrayList<Pair<Integer, String>> f_obtener_tipoJugada(BD db) {
        Pair<Integer, String> temp;
        boolean eof;
        ArrayList<Pair<Integer, String>> arr = new ArrayList<>();
        res = db.f_select_raw("select * from TipoJugada\n" +
                "order by TipoJugada.tipoJugda;");
        eof = res.moveToFirst();
        arr.add(new Pair<>(0, ""));//Primer elemento vacio
        while(eof) {
            arr.add(new Pair<>(Integer.parseInt(res.getString(0)), res.getString(1)));
            eof = res.moveToNext();
        }
        return arr;
    }
    /**
     * Esta funcion obtiene todos los  usuarios y devuelve un ArrayList de Pair<Integer, String> donde Integer es el idPersona
     * y String es el nombre del Usuario
     * @param db Objeto para establecer la conexion con la BD
     * @return ArrayList de Pair<Integer, String> donde Integer es el idPersona
     *         y String es el nombre del Usuario
     */
    static public ArrayList<Pair<Integer, String>> f_obtener_usuario(BD db) {
        Pair<Integer, String> temp;
        boolean eof;
        ArrayList<Pair<Integer, String>> arr = new ArrayList<>();
        res = db.f_select_raw("select idPersona, nombreUsuario from Usuario\n" +
                "where(nombreUsuario <> 'edi') " +
                "order by Usuario.nombreUsuario;");
        eof = res.moveToFirst();
        arr.add(new Pair<>(0, ""));//Primer elemento vacio
        while(eof) {
            arr.add(new Pair<>(Integer.parseInt(res.getString(0)), res.getString(1)));
            eof = res.moveToNext();
        }
        return arr;
    }

    /**
     * Esta funcion obtiene todos los  usuarios bloqueados en el sistema y devuelve un ArrayList de Pair<Integer, String> donde Integer es el idPersona
     * y String es el nombre del Usuario
     * @param db Objeto para establecer la conexion con la BD
     * @return ArrayList de Pair<Integer, String> donde Integer es el idPersona
     *         y String es el nombre del Usuario
     */
    static public ArrayList<Pair<Integer, String>> f_obtener_usuario_bloqueados(BD db) {
        Pair<Integer, String> temp;
        boolean eof;
        ArrayList<Pair<Integer, String>> arr = new ArrayList<>();
        res = db.f_select_raw("select idPersona, nombreUsuario from Usuario\n" +
                "where (nombreUsuario <> 'edi' and isBlock = 1) " +
                "order by Usuario.nombreUsuario;");
        eof = res.moveToFirst();
        arr.add(new Pair<>(0, ""));//Primer elemento vacio
        while(eof) {
            arr.add(new Pair<>(Integer.parseInt(res.getString(0)), res.getString(1)));
            eof = res.moveToNext();
        }
        return arr;
    }
    /**
     * Esta funcion obtiene todos los  usuarios desbloqueados del y devuelve un ArrayList de Pair<Integer, String> donde Integer es el idPersona
     * y String es el nombre del Usuario
     * @param db Objeto para establecer la conexion con la BD
     * @return ArrayList de Pair<Integer, String> donde Integer es el idPersona
     *         y String es el nombre del Usuario
     */
    static public ArrayList<Pair<Integer, String>> f_obtener_usuario_desbloqueados(BD db) {
        Pair<Integer, String> temp;
        boolean eof;
        ArrayList<Pair<Integer, String>> arr = new ArrayList<>();
        res = db.f_select_raw("select idPersona, nombreUsuario from Usuario\n" +
                "where (nombreUsuario <> 'edi' and isBlock = 0) " +
                "order by Usuario.nombreUsuario;");
        eof = res.moveToFirst();
        arr.add(new Pair<>(0, ""));//Primer elemento vacio
        while(eof) {
            arr.add(new Pair<>(Integer.parseInt(res.getString(0)), res.getString(1)));
            eof = res.moveToNext();
        }
        return arr;
    }
    /**
     * Esta funcion devuelve un par con los porciento de lista y bote a pagar a un usuario por fijo
     * @param db Objeto para establecer la conexion con la BD
     * @param idPersona Usuario a chequear
     * @return PAir<PorcLista, PorcientoBote>
     */
    static public Pair<Integer, Integer> f_obtener_porciento_fijo(BD db, int idPersona) {
        res = db.f_select_raw("select tp.valorPago_porciento_lista as lista, tp.valorPago_porciento_bote as bote from TarifaPago as tp\n" +
                "where (tp.idTipoJugada = 3 and tp.idPersona = "+ idPersona +");");
        if(!res.moveToFirst())
            return new Pair<>(0, 0);
        return new Pair<>(Integer.parseInt(res.getString(0)), Integer.parseInt(res.getString(1)));
    }

    /**
     * Esta funcion devuelve un par con los porciento de lista y bote a pagar a un usuario por corrido
     * @param db Objeto para establecer la conexion con la BD
     * @param idPersona Usuario a chequear
     * @return PAir<PorcLista, PorcientoBote>
     */
    static public Pair<Integer, Integer> f_obtener_porciento_corrido(BD db, int idPersona) {
        res = db.f_select_raw("select tp.valorPago_porciento_lista as lista, tp.valorPago_porciento_bote as bote from TarifaPago as tp\n" +
                "where (tp.idTipoJugada = 4 and tp.idPersona = "+ idPersona +");");
        //res.moveToFirst();
        if(!res.moveToFirst())
            return new Pair<>(0, 0);
        return new Pair<>(Integer.parseInt(res.getString(0)), Integer.parseInt(res.getString(1)));
    }

    /**
     * Esta funcion devuelve un par con los porciento de lista y bote a pagar a un usuario por parlet
     * @param db Objeto para establecer la conexion con la BD
     * @param idPersona Usuario a chequear
     * @return PAir<PorcLista, PorcientoBote>
     */
    static public Pair<Integer, Integer> f_obtener_porciento_parlet(BD db, int idPersona) {
        res = db.f_select_raw("select tp.valorPago_porciento_lista as lista, tp.valorPago_porciento_bote as bote from TarifaPago as tp\n" +
                "where (tp.idTipoJugada = 1 and tp.idPersona = "+ idPersona +");");
        //res.moveToFirst();
        if(!res.moveToFirst())
            return new Pair<>(0, 0);
        return new Pair<>(Integer.parseInt(res.getString(0)), Integer.parseInt(res.getString(1)));
    }

    /**
     * Esta funcion devuelve un par con los porciento de lista y bote a pagar a un usuario por centena
     * @param db Objeto para establecer la conexion con la BD
     * @param idPersona Usuario a chequear
     * @return PAir<PorcLista, PorcientoBote>
     */
    static public Pair<Integer, Integer> f_obtener_porciento_centena(BD db, int idPersona) {
        res = db.f_select_raw("select tp.valorPago_porciento_lista as lista, tp.valorPago_porciento_bote as bote from TarifaPago as tp\n" +
                "where (tp.idTipoJugada = 2 and tp.idPersona = "+ idPersona +");");
        //res.moveToFirst();
        if(!res.moveToFirst())
            return new Pair<>(0, 0);
        return new Pair<>(Integer.parseInt(res.getString(0)), Integer.parseInt(res.getString(1)));
    }

    static public Pair<Integer, String> f_obtener_tipoUsuario_xUsuario(BD db, int idUsr) {
        res = db.f_select_raw("SELECT tu.idTipoUsuario, tu.tipoUsuario \n" +
                "FROM Usuario as u\n" +
                "inner join TipoUsuario as tu on u.idTipoUsuario = tu.idTipoUsuario\n" +
                "where u.idPersona = "+ idUsr + ";");
        //res.moveToFirst();
        if(!res.moveToFirst())
            return new Pair<>(0, "");
        return new Pair<>(Integer.parseInt(res.getString(0)), res.getString(1));
    }

    /**
     *Esta funcion registra un usuario
     * @param db Objeto para establecer la conexion con la BD
     * @param valueInsert registro(fila) a insertar en formato de ContentValues
     * @return el id de la fila insertada
     */
    static public int f_registrar_usuario(BD db, ContentValues valueInsert) {
        return db.crud_create("Usuario", valueInsert);
    }
    /**
     *Esta funcion registra un tiro
     * @param db Objeto para establecer la conexion con la BD
     * @param valueInsert registro(fila) a insertar en formato de ContentValues
     * @return el id de la fila insertada
     */
    static public int f_registrar_tiro(BD db, ContentValues valueInsert) {
        return db.crud_create("Tiro", valueInsert);
    }
    /**
     *Esta funcion registra tarifa de pago
     * @param db Objeto para establecer la conexion con la BD
     * @param valueInsert registro(fila) a insertar en formato de ContentValues
     * @return el id de la fila insertada
     */
    static public int f_registrar_tarifaPago(BD db, ContentValues valueInsert) {
        return db.crud_create("TarifaPago", valueInsert);
    }
    /**
     *Esta funcion registra tipo de jugada
     * @param db Objeto para establecer la conexion con la BD
     * @param valueInsert registro(fila) a insertar en formato de ContentValues
     * @return el id de la fila insertada
     */
    static public int f_registrar_tipoJugada(BD db, ContentValues valueInsert) {
        return db.crud_create("TipoJugada", valueInsert);
    }

    /**
     *Esta funcion registra 1 o varias jugadas asociadas a un Conjunto ya sea normal o centana
     * @param db Objeto para establecer la conexion con la BD
     * @param idUsr id de usuario Ligado al sistema
     * @param jugada_ jugada o arreglo de jugada(centena, num, valorFijo, valorCorr, valorCent)
     * @param idConjunto_Jugada por defecto se contempla a -1
     * @return devuelve idsConjunto de jugada si la jugada se registro correctamente sino -2
     */
    static public int f_registrar_jugada(BD db, int idUsr, ArrayList<Quintet<Integer, Integer, Float, Float, Float>> jugada_, int idConjunto_Jugada) {
        //Obtener Conjunto de Jugada idconjuntoJugada y la fecha y la hora promedio de insercion de jugada/s
        String Fecha, Hora;
        Fecha = fecha_hora.f_obtenerFechaActual("GMT-5");
        Hora = fecha_hora.f_obtenerHoraActual("GMT-5");
        int idConjunto = (idConjunto_Jugada == -1) ? f_idConjuntoJugada(db) :idConjunto_Jugada;
        int idJugada, idAsoc; //idDeJugada

        //Obtener los limites/Topes(id, centena, fijo, corrido) para el usr(Listero) que inserta
        Quartet<Integer, Float, Float, Float> limites;
        limites = f_obtener_limite_numeros_listero(db, idUsr);
        if(limites == null) return -2; //No existe limite

        for(int i = 0; i < jugada_.size(); i++) {
            //Asociar la jugada al Bote o a lista
            // el arreglo de atributo no tiene la misma longitud que el de valores
            if (jugada_.get(i).first != 0) {//Juega centena
                Float totalCentena = f_obtener_jugado_centena(db, jugada_.get(i).first, limites.first, idUsr);
                // el arreglo de atributo no tiene la misma longitud que el de valores
                if (totalCentena.equals(limites.second)) {
                    //Inserta centena en bote
                    String[] atribBote = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                            valBote = {"" + jugada_.get(i).first + "",
                                    "" + jugada_.get(i).second + "",
                                    "" + jugada_.get(i).third + "",
                                    "" + jugada_.get(i).fourth + "",
                                    "" + jugada_.get(i).fifth + ""
                            };
                    idJugada = db.crud_create("Jugada", atribBote, valBote);
                    if (idJugada == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                    //Asociar jugadas
                    String[] atribAsociarBote = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                            valAsociarBote = {"" + idJugada + "",
                                    "" + limites.first + "",
                                    "" + idConjunto + "",
                                    "" + 1 + "",
                                    "" + Fecha + "",
                                    "" + Hora + ""
                            };
                    idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarBote, valAsociarBote);
                } else {
                    // el arreglo de atributo no tiene la misma longitud que el de valores
                    // el arreglo de atributo no tiene la misma longitud que el de valores
                    if(totalCentena + jugada_.get(i).fifth > limites.second) {
                        //Calcula diferencia, completa la lista e inserta la diferencia al bote
                        float difCent = limites.second - totalCentena;
                        float aBoteCent = jugada_.get(i).fifth - difCent;

                        //Insertar jugada centena para completar tope de lista y obtener idJugada
                        String[] atribJugadaCent = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                                valJugadaCent = {"" + jugada_.get(i).first + "",
                                        "" + jugada_.get(i).second + "",
                                        "" + jugada_.get(i).third + "",
                                        "" + jugada_.get(i).fourth + "",
                                        "" + difCent + ""
                                };
                        idJugada = db.crud_create("Jugada", atribJugadaCent, valJugadaCent);
                        if (idJugada == -2)
                            return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                        //Asociar jugadas
                        String[] atribAsociarJugadas = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                                valAsociarJugadas = {"" + idJugada + "",
                                        "" + limites.first + "",
                                        "" + idConjunto + "",
                                        "" + 0 + "",
                                        "" + Fecha + "",
                                        "" + Hora + ""
                                };
                        idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarJugadas, valAsociarJugadas);
                        if (idAsoc == -2)
                            return -2; // el arreglo de atributo no tiene la misma longitud que el de valores

                        //Inserta la jugada con el valor sobrante a la centena para bote
                        String[] atribJugada2 = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                                valJugada2 = {"" + jugada_.get(i).first + "",
                                        "" + jugada_.get(i).second + "",
                                        "" + jugada_.get(i).third + "",
                                        "" + jugada_.get(i).fourth + "",
                                        "" + aBoteCent + ""
                                };
                        int idJugada_2 = db.crud_create("Jugada", atribJugada2, valJugada2);
                        if (idJugada_2 == -2)
                            return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                        //Asociar jugadas
                        String[] atribAsociarJugadas_2 = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                                valAsociarJugadas_2 = {"" + idJugada_2 + "",
                                        "" + limites.first + "",
                                        "" + idConjunto + "",
                                        "" + 1 + "",
                                        "" + Fecha + "",
                                        "" + Hora + ""
                                };
                        idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarJugadas_2, valAsociarJugadas_2);
                    } else {//Menor la suma
                        //Inserta centena en lista no Bote
                        String[] atribLista = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                                valLista = {"" + jugada_.get(i).first + "",
                                        "" + jugada_.get(i).second + "",
                                        "" + jugada_.get(i).third + "",
                                        "" + jugada_.get(i).fourth + "",
                                        "" + jugada_.get(i).fifth + ""
                                };
                        idJugada = db.crud_create("Jugada", atribLista, valLista);
                        if (idJugada == -2)
                            return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                        //Asociar jugadas
                        String[] atribAsociarLista = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                                valAsociarLista = {"" + idJugada + "",
                                        "" + limites.first + "",
                                        "" + idConjunto + "",
                                        "" + 0 + "",
                                        "" + Fecha + "",
                                        "" + Hora + ""
                                };
                        idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarLista, valAsociarLista);
                    }
                    if (idAsoc == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                }
            } else {//Juega fijo
                Pair<Float, Float> totalfijo_Corr = f_obtener_jugado_fijo_corrido(db, jugada_.get(i).second, limites.first, idUsr);
                // el arreglo de atributo no tiene la misma longitud que el de valores
                // el arreglo de atributo no tiene la misma longitud que el de valores
                if(totalfijo_Corr.first.equals(limites.third)) {
                    //Inserta en bote
                    String[] atribBote = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                            valBote = {"" + jugada_.get(i).first + "",
                                    "" + jugada_.get(i).second + "",
                                    "" + jugada_.get(i).third + "",
                                    "" + jugada_.get(i).fourth + "",
                                    "" + jugada_.get(i).fifth + ""
                            };
                    idJugada = db.crud_create("Jugada", atribBote, valBote);
                    if (idJugada == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                    //Asociar jugadas
                    String[] atribAsociarBote = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                            valAsociarBote = {"" + idJugada + "",
                                    "" + limites.first + "",
                                    "" + idConjunto + "",
                                    "" + 1 + "",
                                    "" + Fecha + "",
                                    "" + Hora + ""
                            };
                    idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarBote, valAsociarBote);

                } else if (totalfijo_Corr.first + jugada_.get(i).third > limites.third) {
                    //Calcula diferencia, completa la lista e inserta la diferencia al bote
                    float dif = limites.third - totalfijo_Corr.first;
                    float aBote = jugada_.get(i).third - dif;

                    //Insertar jugada para completar tope de lista y obtener idJugada
                    String[] atribJugada = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                            valJugada = {"" + jugada_.get(i).first + "",
                                    "" + jugada_.get(i).second + "",
                                    "" + dif + "",
                                    "" + jugada_.get(i).fourth + "",
                                    "" + jugada_.get(i).fifth + ""
                            };
                    idJugada = db.crud_create("Jugada", atribJugada, valJugada);
                    if (idJugada == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                    //Asociar jugadas
                    String[] atribAsociarJugadas = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                            valAsociarJugadas = {"" + idJugada + "",
                                    "" + limites.first + "",
                                    "" + idConjunto + "",
                                    "" + 0 + "",
                                    "" + Fecha + "",
                                    "" + Hora + ""
                            };
                    idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarJugadas, valAsociarJugadas);
                    if (idAsoc == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores

                    //Inserta la jugada con el valor sobrante al numero para bote
                    String[] atribJugada2 = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                            valJugada2 = {"" + jugada_.get(i).first + "",
                                    "" + jugada_.get(i).second + "",
                                    "" + aBote + "",
                                    "" + (float) 0 + "",
                                    "" + jugada_.get(i).fifth + ""
                            };
                    int idJugada_2 = db.crud_create("Jugada", atribJugada2, valJugada2);
                    if (idJugada_2 == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                    //Asociar jugadas
                    String[] atribAsociarJugadas_2 = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                            valAsociarJugadas_2 = {"" + idJugada_2 + "",
                                    "" + limites.first + "",
                                    "" + idConjunto + "",
                                    "" + 1 + "",
                                    "" + Fecha + "",
                                    "" + Hora + ""
                            };
                    idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarJugadas_2, valAsociarJugadas_2);
                } else {
                    //Inserta en lista
                    String[] atribLista = {"centena", "numero", "valorFijo", "valorCorrido", "valorCentena"},
                            valLista = {"" + jugada_.get(i).first + "",
                                    "" + jugada_.get(i).second + "",
                                    "" + jugada_.get(i).third + "",
                                    "" + jugada_.get(i).fourth + "",
                                    "" + jugada_.get(i).fifth + ""
                            };
                    idJugada = db.crud_create("Jugada", atribLista, valLista);
                    if (idJugada == -2)
                        return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                    //Asociar jugadas
                    String[] atribAsociarLista = {"idJugada", "idTopeLista_jugada", "idConjuntoJugada", "isBote", "fecha", "hora"},
                            valAsociarLista = {"" + idJugada + "",
                                    "" + limites.first + "",
                                    "" + idConjunto + "",
                                    "" + 0 + "",
                                    "" + Fecha + "",
                                    "" + Hora + ""
                            };
                    idAsoc = db.crud_create("Jugada_TopeLista", atribAsociarLista, valAsociarLista);
                }
                if (idAsoc == -2)
                    return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
            }
            if(idAsoc == -2)
                return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
        }
        return idConjunto; //Inserto correcto
    }

    static public int f_registrar_jugada(BD db, int idUsr, ArrayList<Quintet<Integer, Integer, Float, Float, Float>> jugada_) {
        return  f_registrar_jugada(db, idUsr, jugada_, -1);
    }

    /**
     * Esta funcion registra 1 o mas parlets
     * @param db Objeto para establecer la conexion con la BDObjeto para establecer la conexion con la BD
     * @param idUsr id de usuario Ligado al sistema
     * @param parlets parlet o arreglo de parlets(num1, num2, valorParlets)
     * @param idConjunto_Jugada por defecto se contempla a -1
     * @return devuelve idConjunto de Jugada si la jugada se registro correctamente -2 si hay algun error
     */
    static public int f_registrar_parlets(BD db, int idUsr, ArrayList<Triplet<Integer, Integer, Float>> parlets, int idConjunto_Jugada) {
        //Obtener Conjunto de Jugada idconjuntoJugada y la fecha y la hora promedio de insercion de jugada/s
        String Fecha, Hora;
        Fecha = fecha_hora.f_obtenerFechaActual("GMT-5");
        Hora = fecha_hora.f_obtenerHoraActual("GMT-5");
        //int idConjunto = f_idConjuntoJugada(db);
        int idConjunto = (idConjunto_Jugada == -1) ? f_idConjuntoJugada(db) : idConjunto_Jugada;
        int idJugada, idAsoc; //idDeJugada

        //Obtener los limites/Topes(id, centena, fijo, corrido) para el usr(Listero) que inserta parlets
        Pair<Integer, Float> limites;
        limites = f_obtener_limite_parlets_listero(db, idUsr);
        if(limites == null) return -2; //No existe limite

        for(int i = 0; i < parlets.size(); i++) {
            Float totalParlet = f_obtener_jugado_parlets(db, parlets.get(i).first, parlets.get(i).second,limites.first, idUsr);
            // el arreglo de atributo no tiene la misma longitud que el de valores
            // el arreglo de atributo no tiene la misma longitud que el de valores
            // el arreglo de atributo no tiene la misma longitud que el de valores
            if(totalParlet.equals(limites.second)) {
                //Inserta en bote
                String[] atribBote = {"num1", "num2", "valorParlet"},
                        valBote = {"" + parlets.get(i).first + "",
                                "" + parlets.get(i).second + "",
                                "" + parlets.get(i).third + ""
                        };
                idJugada = db.crud_create("Parlet", atribBote, valBote);
                if (idJugada == -2)
                    return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                //Asociar jugadas
                String[] atribAsociarBote = {"idParlet", "idTopeLista_parlet", "idConjuntoJugada", "isBote", "fecha", "hora"},
                        valAsociarBote = {"" + idJugada + "",
                                "" + limites.first + "",
                                "" + idConjunto + "",
                                "" + 1 + "",
                                "" + Fecha + "",
                                "" + Hora + ""
                        };
                idAsoc = db.crud_create("Parlet_TopeLista", atribAsociarBote, valAsociarBote);
            }else if (totalParlet + parlets.get(i).third > limites.second) {
                //Calcula diferencia, completa la lista e inserta la diferencia al bote
                float dif = limites.second - totalParlet;
                float aBote = parlets.get(i).third - dif;

                //Insertar parlets para completar tope de lista y obtener idJugada
                String[] atribJugada = {"num1", "num2", "valorParlet"},
                        valJugada = {"" + parlets.get(i).first + "",
                                "" + parlets.get(i).second + "",
                                "" + dif + ""
                        };
                idJugada = db.crud_create("Parlet", atribJugada, valJugada);
                if (idJugada == -2)
                    return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                //Asociar jugadas
                String[] atribAsociarBote = {"idParlet", "idTopeLista_parlet", "idConjuntoJugada", "isBote", "fecha", "hora"},
                        valAsociarBote = {"" + idJugada + "",
                                "" + limites.first + "",
                                "" + idConjunto + "",
                                "" + 0 + "",
                                "" + Fecha + "",
                                "" + Hora + ""
                        };
                idAsoc = db.crud_create("Parlet_TopeLista", atribAsociarBote, valAsociarBote);
                if (idAsoc == -2)
                    return -2; // el arreglo de atributo no tiene la misma longitud que el de valores

                //Inserta la jugada con el valor sobrante al numero para bote
                String[] atribJugada2 = {"num1", "num2", "valorParlet"},
                        valJugada2 = {"" + parlets.get(i).first + "",
                                "" + parlets.get(i).second + "",
                                "" + aBote + ""
                        };
                int idJugada_2 = db.crud_create("Parlet", atribJugada2, valJugada2);
                if (idJugada_2 == -2)
                    return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                //Asociar jugadas
                String[] atribAsociarJugadas_2 = {"idParlet", "idTopeLista_parlet", "idConjuntoJugada", "isBote", "fecha", "hora"},
                        valAsociarJugadas_2 = {"" + idJugada_2 + "",
                                "" + limites.first + "",
                                "" + idConjunto + "",
                                "" + 1 + "",
                                "" + Fecha + "",
                                "" + Hora + ""
                        };
                idAsoc = db.crud_create("Parlet_TopeLista", atribAsociarJugadas_2, valAsociarJugadas_2);
            } else {
                //Inserta en lista
                String[] atribLista = {"num1", "num2", "valorParlet"},
                        valLista = {"" + parlets.get(i).first + "",
                                "" + parlets.get(i).second + "",
                                "" + parlets.get(i).third + ""
                        };
                idJugada = db.crud_create("Parlet", atribLista, valLista);
                if (idJugada == -2)
                    return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
                //Asociar jugadas
                String[] atribAsociarLista = {"idParlet", "idTopeLista_parlet", "idConjuntoJugada", "isBote", "fecha", "hora"},
                        valAsociarLista = {"" + idJugada + "",
                                "" + limites.first + "",
                                "" + idConjunto + "",
                                "" + 0 + "",
                                "" + Fecha + "",
                                "" + Hora + ""
                        };
                idAsoc = db.crud_create("Parlet_TopeLista", atribAsociarLista, valAsociarLista);
            }
            if (idAsoc == -2)
                return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
        }

        return idConjunto;
    }

    static public int f_registrar_parlets(BD db, int idUsr, ArrayList<Triplet<Integer, Integer, Float>> parlets) {
        return  f_registrar_parlets(db, idUsr, parlets, -1);
    }

    /**
     *  Esta funcion redondea um doble a uma cantidad de lugares especificadas
     * @param num numero (doble) a ser redondeado
     * @param lug lugares (int) decimales
     * @return  Doble
     */
    static public double f_redon_double(double num, int lug){
        BigDecimal bd = new BigDecimal(num).setScale(lug, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *  Esta funcion redondea um float a uma cantidad de lugares especificadas
     * @param num numero (float) a ser redondeado
     * @param lug lugares (int) decimales
     * @return float
     */
    static public float f_redon_float(float num, int lug){
        BigDecimal bd = new BigDecimal(num).setScale(lug , RoundingMode.HALF_UP);
        return bd.floatValue();
    }

}
