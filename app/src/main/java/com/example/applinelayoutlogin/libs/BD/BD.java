package com.example.applinelayoutlogin.libs.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class BD extends SQLiteOpenHelper {
    public SQLiteDatabase handler;
    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        //Abre la base de datos en lectura y escritura
        getWritableDatabase();
    }

    /**
     *
     * @return el maipulador de consulta que abre la base de datos en lectura y escritura
     */
    public SQLiteDatabase f_get_handler() {
        this.handler = getWritableDatabase();
        return this.handler;
    }

    /*Funciones de la base de datos*/

    /**
     * Function que realiza consulta de Seleccion Cruda
     * @param sql Consulta de Cruda de seleccion
     * @return Cursor que servira para recorrer la seleccion
     */
    public Cursor f_select_raw(String sql) {
        return f_get_handler().rawQuery(sql, null);
    }

    /**
     * crud_select(SQLiteDatabase, String[]):  Cursor
     * Ejecuta una consulta de seleccion gral con args donde los argumentos pasan
     * en un Arreglo de argumentos
     * @param tabla --->    Tabla de la cual se va  a seleccionar
     * @param args ------>  Arreglo de argumentos para general la consulta
     *                      arg1 = atributos a mostrar de la tabla separados por coma ej: argtabla1, ..., argTablaN
     *                      arg2 = condicion o conjunto de condiciones en forma de string unido a
     *                      arg3       congunto de clausaras ej: order by, limit, agroup, etc en forma de string
     * @return  --------->  Cursor que es el que permite navegar por la tabla o vista resultado
     * */
    private Cursor crud_select(String tabla, String[] args) {
        Cursor res;
        if(args[0] == null){
            //Error de que almenos hay que pasar un argument
            res = null;
        } else {
            res = f_get_handler().rawQuery("select "+ args[0]+" from " + tabla + " " +
                    "where " + args[1] + " " + args[2]+";", null);
        }
        return res;
    }

    /**
     * crud_read(SQLiteDatabase, String[]):  Cursor
     * Ejecuta una consulta de seleccion gral con args donde los argumentos pasan
     * en un Arreglo de argumentos
     * @param tabla --->    Tabla de la cual se va  a seleccionar
     * @param args ------>  Arreglo de argumentos para general la consulta
     *                      arg1 = atributos a mostrar de la tabla separados por coma ej: argtabla1, ..., argTablaN
     *                      arg2 = condicion o conjunto de condiciones en forma de string unido a
     *                      arg3       congunto de clausaras ej: order by, limit, agroup, etc en forma de string
     * @return  --------->  Cursor que es el que permite navegar por la tabla o vista resultado
     * */

     public Cursor crud_read(String tabla, String[] args){ return crud_select(tabla, args); }


    /**
     * crud_read(SQLiteDatabase, String, String[], String[], String[]): Cursor
     * Ejecuta una consulta de seleccion con los parametros pasdos
     * @param tabla ----------->  Tabla a seleccionar
     * @param atrib ---------->  Arreglo de Atributos a mostrar segun el orden
     * @param cond ------------>  Arreglo de condiciones o condicion
     * @param clausere -------->  Arreglo de clausures o clausure
     * @return --------->  Cursor que permite recorrer el resultado de la seleccion
     * */
    public Cursor crud_read(String tabla, String[] atrib, String[] cond, String[] clausere) {
        String[] args = new String[3];
        String atribs = "", conds = "", clauseres = "";
        //Conformando atributos
        for (int i = 0; i < atrib.length; i ++) {
            if((atrib.length - 2) > i) {
               //atribs += ;
                atribs = atribs.concat(atrib[i]);
                atribs = atribs.concat(",");
                //atribs += ",";
            }
            else{
//                atribs += atrib[i];
                atribs = atribs.concat(atrib[i]);
            }
        }
//Conformar cond
        for (int i = 0; i < cond.length; i ++) {
            if((cond.length - 2) > i) {
                conds = conds.concat(cond[i]);
                conds = conds.concat(",");
            }
            else{
                conds = conds.concat(cond[i]);
                conds += cond[i];
            }
        }
        //Conformar clausure
        for (int i = 0; i < clausere.length; i ++) {
            if((clausere.length - 2) > i) {
                clauseres = clauseres.concat(clausere[i]);
                clauseres = clauseres.concat(",");
            }
            else{
                clauseres = clauseres.concat(clausere[i]);
            }
        }
        args[0] = atribs;
        args[1] = conds;
        args[2] = clauseres;
        return this.crud_select(tabla, args);
    }

    /**
     * Genera las consultas de insercion en la base de datos a tratar
     * parametros:
     *                         que sirve para abrirla en lectura y escritura se crea
     *                            ej:
     *                              this.handlerConsult = this.db.getWritableDatabase();
     *                           donde db es de tipo SqliteOpenHelper(Nombre de la clase manejadora de BD)
     * @param tabla     --> Nombre de la tabla a insertars
     * @param atrib     --> Arreglo con el nombre de los campos de la base de datos el tipo es String
     * @param vals      --> Arreglo con los valores a insertar se deben de settear los tipos segun Sqlite el tipo es
     *                    String
     * Retornos:
     *                  -2 las longitudes de atrib y vals no coinciden
     *                  -1 error ocurrido a la hora de ejecutar la consulta
     *                  int el id del registro insertado
     * */
    public int crud_create(String tabla, String[] atrib, String[] vals) {
        ContentValues reg = new ContentValues();
        int lenAtrib, lenVals, idRes;
        lenAtrib = atrib.length;
        lenVals = vals.length;
        if(lenAtrib == lenVals) {
            for (int i = 0; i < lenAtrib; i++) {
                reg.put(atrib[i], vals[i]);
            }
            idRes = (int)f_get_handler().insert(tabla, null, reg);
        }
        else {
            return -2; // el arreglo de atributo no tiene la misma longitud que el de valores
        }
        return idRes;
    }
    public int crud_create(String tabla, ContentValues regs) {
            return (int)f_get_handler().insert(tabla, null, regs);
    }

    /**
     * crud_read(SQLiteDatabase, String, String[], String[]): Cursor
     * Ejecuta una consulta de seleccion con los parametros pasdos en cond, clausure y segun la tabla
     *   Ej; select * from tabla where cond[0] cond[1] cond[N] clausure[0] clausure[N]
     * @param tabla----------->  Tabla a seleccionar
     * @param cond------------>  Arreglo de condiciones o condicion
     * @param clausere-------->  Arreglo de clausures o clausure
     * */
    public Cursor crud_read_cond_clausure( String tabla, String[] cond, String[] clausere){
        String atribs = "*", conds = "", clauseres = "";
        String[] args = new String[3];
//Conformar cond
        for (int i = 0; i < cond.length; i ++) {
            if((cond.length - 2) > i) {
                conds = conds.concat(cond[i]);
                conds = conds.concat(",");
            }
            else{
                conds = conds.concat(cond[i]);
            }
        }
        //Conformar clausure
        for (int i = 0; i < clausere.length; i ++) {
            if((clausere.length - 2) > i) {
                clauseres = clauseres.concat(clausere[i]);
                clauseres = clauseres.concat(",");
            }
            else{
                clauseres = clauseres.concat(clausere[i]);
            }
        }

        args[0] = atribs;
        args[1] = conds;
        args[2] = clauseres;
        return crud_select(tabla, args);
    }

    /**
     * crud_read(SQLiteDatabase, String,  String[]): Cursor
     * Ejecuta una consulta de seleccion aplicando consulta o conjunto de condiciones
     *          eje: select * from tabla where cond1 cond2 condN
     * @param tabla----------->  Tabla a seleccionar
     * @param cond------------>  Arreglo de condiciones o condicion
     * */
    public Cursor crud_read_cond(SQLiteDatabase handlerQuery, String tabla, String[] cond) {
        String atribs = "*", conds = "";
        String[] args = new String[2];
//Conformar cond
        for (int i = 0; i < cond.length; i ++) {
            if((cond.length - 2) > i) {
                conds = conds.concat(cond[i]);
                conds = conds.concat(",");
            }
            else{
                conds = conds.concat(cond[i]);
            }
        }

        args[0] = atribs;
        args[1] = conds;
        return crud_select(tabla, args);

    }

    /**
     * crud_read(SQLiteDatabase, String, String[], String[]): Cursor
     * Ejecuta una consulta de seleccion aplicando consulta o conjunto de condiciones y mostrando
     * atributos dados
     *          eje: select atrib[0],.., atrib[n] from tabla where cond1 cond2 condN
     * @param tabla----------->  Tabla a seleccionar
     * @param atrib----------->  conjunto de atributos a seleccionar
     * @param cond------------>  Arreglo de condiciones o condicion
     * @return --------------->
     * */
    public Cursor crud_read_atrib_cond(String tabla, String[] atrib, String[] cond) {
        String[] args = new String[2];
        String atribs = "", conds = "";
        //Conformando atributos
        for (int i = 0; i < atrib.length; i ++) {
            if((atrib.length - 2) > i) {
                atribs = atribs.concat(atrib[i]);
                atribs = atribs.concat(",");
            }
            else{
                atribs = atribs.concat(atrib[i]);
            }
        }
//Conformar cond
        for (int i = 0; i < cond.length; i ++) {
            if((cond.length - 2) > i) {
                conds = conds.concat(cond[i]);
                conds = conds.concat(",");
            }
            else{
                conds =  conds.concat(cond[i]);
            }
        }
        args[0] = atribs;
        args[1] = conds;
        return crud_select(tabla, args);
    }
    /**
     * crud_read(SQLiteDatabase, String, String[]): Cursor
     * Ejecuta una consulta de seleccion sobre la tabla aplicandole todas las clausuras pasadas
     *          ej: select * from <nombre_table> ORDER BY column_1 [DESC|ASC] Limit 1 ;
     * @param tabla-----------> Tabla a seleccionar
     * @param clausere-------->  Arreglo de clausures o clausure
     * @return ------->
     */
    public Cursor crud_read_clausure(String tabla, String[] clausere) {
        String[] args = new String[2];
        String atribs = "*", clauseres = "";

        //Conformar clausure
        for (int i = 0; i < clausere.length; i ++) {
            if((clausere.length - 2) > i) {
//                clauseres += ;
//                clauseres += ",";
                clauseres = clauseres.concat(clausere[i]);
                clauseres = clauseres.concat(",");
            }
            else{
                /*clauseres += clausere[i];*/
                clauseres = clauseres.concat(clausere[i]);
            }
        }
        args[0] = atribs;
        args[1] = clauseres;
        return crud_select(tabla, args);
    }
    /**
     * crud_read(SQLiteDatabase, String, String[], String[]): Cursor
     * Ejecuta una consulta de seleccion sobre la tabla aplicandole todas las clausuras pasadas y mostrando
     *     solo los atributos seleccionados
     *          ej: select atrib1,..., atribN from <nombre_table> ORDER BY column_1 [DESC|ASC] Limit 1 ;
     *  @param tabla-----------> Tabla a seleccionar
     *  @param atrib----------->  conjunto de atributos a seleccionar
     * @param clausere-------->  Arreglo de clausures o clausure
     * @return ---------------->
     * */
    public Cursor crud_read(String tabla, String[] atrib, String[] clausere) {
        String[] args = new String[3];
        String atribs = "", conds = "", clauseres = "";

        //Conformando atributos
        for (int i = 0; i < atrib.length; i ++) {
            if((atrib.length - 2) > i) {
                //atribs += atrib[i];
                atribs = atribs.concat(atrib[i]);
                atribs = atribs.concat(",");
//                atribs += ",";
            }
            else{
//                atribs += atrib[i];
                atribs = atribs.concat(atrib[i]);
            }
        }

        //Conformar clausure
        for (int i = 0; i < clausere.length; i ++) {
            if((clausere.length - 2) > i) {
                clauseres = clauseres.concat(clausere[i]);
                clauseres = clauseres.concat(",");
            }
            else{
                clauseres = clauseres.concat(clausere[i]);
            }
        }
        args[0] = atribs;
        args[1] = conds;
        args[2] = clauseres;
        return crud_select(tabla, args);
    }

    /**
     * crud_read(SQLiteDatabase, String): Cursor
     * Ejecuta una consulta de seleccion con los sobre la tabla mostrando todos los parametros
     *   ej: select * from <nombre_table>;
     * @param tabla----------->  Tabla a seleccionar
     * */
    public Cursor crud_read_table(String tabla) {
        String[] args = new String[3];
        String atribs = "*", conds = "", clauseres = "";

        args[0] = atribs;
        args[1] = conds;
        args[2] = clauseres;
        return crud_select(tabla, args);
    }

    /**
     * crud_read(SQLiteDatabase, String): Cursor
     * Ejecuta una consulta de seleccion con los sobre la tabla mostrando atributos seleccionados
     *   ej: select atrib1,..., atribN from <nombre_table>;
     * @param  atrib----------->  conjunto de atributos a seleccionar
     * @param  tabla----------->  Tabla a seleccionar
     * @return ---------------->
     * */
    public Cursor crud_read_atrib(String tabla, String[] atrib) {
        String[] args = new String[3];
        String atribs = "", conds = "", clauseres = "";
        //Conformando atributos
        for (int i = 0; i < atrib.length; i ++) {
            if((atrib.length - 2) > i) {
                atribs = atribs.concat(atrib[i]);
                atribs = atribs.concat(",");
            }
            else{
                atribs = atribs.concat(atrib[i]);
            }
        }

        args[0] = atribs;
        args[1] = conds;
        args[2] = clauseres;
        return crud_select(tabla, args);
    }

    /**
     * Esto realiza el crud de actualizacion
     * @param tabla Nombre de la tabla que se va a modificar
     * @param atrib_valuModif es de tipo ContentValues. Estos son los par de (nombreCol, valor) en
     *                        formato de ContentValue
     * @param Cond Condicion a tener en cuenta para la insercion
     * @return Devuelve cantidad de columnas afectadas
     */
    protected int crud_update( String tabla, ContentValues atrib_valuModif, String Cond) {
        return f_get_handler().update(tabla, atrib_valuModif, Cond, null);

    }
    public int update( String tabla, ContentValues atrib_valuModif, String Cond) {
        return crud_update(tabla, atrib_valuModif, Cond);

    }

    public void crud_delete(SQLiteDatabase handlerQuery, String tabla) {}

//////////////////////////Craecion de tablas//////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.handler = sqLiteDatabase;
        f_create_table_android_metadata();
        f_create_table_ConjuntoJugada();
        f_create_table_Grupo();
        f_create_table_Grupo_Permiso();
        f_create_table_Jugada();
        f_create_table_Jugada_TopeLista();
        f_create_table_Menu();
        f_create_table_NumeroLimitado();
        f_create_table_Parlet();
        f_create_table_Parlet_TopeLista();
        f_create_table_ParletLimitado();
        f_create_table_Permiso();
        f_create_table_Persona();
        f_create_table_SubMenu();
        f_create_table_TarifaPago();
        f_create_table_TipoJugada();
        f_create_table_TipoUsuario();
        f_create_table_TopeLista_parlet();
        f_create_table_TopeLista_jugada();
        f_create_table_Usuario();
        f_create_table_Usuario_Grupo();
        f_create_table_Usuario_Permiso();
        f_create_table_Tiro();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void f_close() {
        this.getWritableDatabase().close();
        close();
    }

    private void f_create_table_android_metadata() {
        //f_drop_if_exist(this.handler, "android_metadata");

        this.handler.execSQL("CREATE TABLE IF NOT EXISTS android_metadata (\n" +
                "    locale TEXT\n" +
                ");");

        //this.handler.execSQL("INSERT INTO android_metadata (locale) VALUES ('en_US');");
    }

    private void f_create_table_ConjuntoJugada() {
        //f_drop_if_exist(this.handler, "ConjuntoJugada");
        this.handler.execSQL("CREATE TABLE ConjuntoJugada (\n" +
                "    idConjuntoJugada INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    descripcion      TEXT (35) \n" +
                ");");
        //Migracion
        this.handler.execSQL("INSERT INTO ConjuntoJugada (idConjuntoJugada,descripcion) VALUES\n" +
                "\t (1,'idDescripcion'),\n" +
                "\t (2,'idDescripcion'),\n" +
                "\t (3,'idDescripcion'),\n" +
                "\t (4,'idDescripcion'),\n" +
                "\t (5,'idDescripcion'),\n" +
                "\t (6,'idDescripcion'),\n" +
                "\t (7,'idDescripcion'),\n" +
                "\t (8,'idDescripcion'),\n" +
                "\t (9,'idDescripcion'),\n" +
                "\t (10,'idDescripcion'),\n" +
                "\t (11,'idDescripcion'),\n" +
                "\t (12,'idDescripcion'),\n" +
                "\t (13,'idDescripcion'),\n" +
                "\t (14,'idDescripcion'),\n" +
                "\t (15,'idDescripcion'),\n" +
                "\t (16,'idDescripcion'),\n" +
                "\t (17,'idDescripcion'),\n" +
                "\t (18,'idDescripcion'),\n" +
                "\t (19,'idDescripcion'),\n" +
                "\t (20,'idDescripcion'),\n" +
                "\t (21,'idDescripcion'),\n" +
                "\t (22,'idDescripcion'),\n" +
                "\t (23,'idDescripcion'),\n" +
                "\t (24,'idDescripcion'),\n" +
                "\t (25,'idDescripcion'),\n" +
                "\t (26,'idDescripcion');");
    }

    private void f_create_table_Grupo() {
        //f_drop_if_exist(this.handler, "Grupo");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Grupo (\n" +
                "    idGrupo INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    grupo   TEXT (35) \n" +
                ");\n");
    }

    private void f_create_table_Grupo_Permiso() {
        //f_drop_if_exist(this.handler, "Grupo_Permiso");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Grupo_Permiso (\n" +
                "    idGrupoPermiso INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idPermiso      INTEGER REFERENCES Permiso (IdPermiso) ON DELETE CASCADE\n" +
                "                                                          ON UPDATE CASCADE,\n" +
                "    idGrupo        INTEGER REFERENCES Grupo (idGrupo) ON DELETE CASCADE\n" +
                "                                                      ON UPDATE CASCADE\n" +
                ");");
    }

    private void f_create_table_Jugada() {
        //f_drop_if_exist(this.handler, "Jugada");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Jugada (\n" +
                "    idJugada     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    centena      INTEGER,\n" +
                "    numero       INTEGER,\n" +
                "    valorFijo    REAL,\n" +
                "    valorCorrido REAL,\n" +
                "    valorCentena REAL\n" +
                ");");
        //Migracion
        this.handler.execSQL("INSERT INTO Jugada (idJugada,centena,numero,valorFijo,valorCorrido,valorCentena) VALUES\n" +
                "\t (9,0,78,4000.0,100.0,0.0),\n" +
                "\t (10,0,78,500.0,1.0,0.0),\n" +
                "\t (11,0,78,90.0,25.0,0.0),\n" +
                "\t (12,0,78,100.0,50.0,0.0),\n" +
                "\t (13,0,78,100.0,45.0,0.0),\n" +
                "\t (14,0,78,200.0,10.0,0.0),\n" +
                "\t (15,0,78,10.0,89.0,0.0),\n" +
                "\t (16,0,78,190.0,0.0,0.0),\n" +
                "\t (17,0,78,0.0,100.0,0.0),\n" +
                "\t (18,0,78,250.0,0.0,0.0),\n" +
                "\t (19,0,7,8.0,9.0,0.0),\n" +
                "\t (20,777,0,0.0,0.0,200.0),\n" +
                "\t (21,977,0,0.0,0.0,101.0),\n" +
                "\t (22,0,70,10.0,10.0,0.0),\n" +
                "\t (23,0,71,10.0,10.0,0.0),\n" +
                "\t (24,0,72,10.0,10.0,0.0),\n" +
                "\t (25,0,73,10.0,10.0,0.0),\n" +
                "\t (26,0,74,10.0,10.0,0.0),\n" +
                "\t (27,0,75,10.0,10.0,0.0),\n" +
                "\t (28,0,76,10.0,10.0,0.0),\n" +
                "\t (29,0,77,10.0,10.0,0.0),\n" +
                "\t (30,0,78,0.0,10.0,0.0),\n" +
                "\t (31,0,78,10.0,0.0,0.0),\n" +
                "\t (32,0,79,10.0,10.0,0.0),\n" +
                "\t (33,0,6,100.0,100.0,0.0),\n" +
                "\t (34,0,16,100.0,100.0,0.0),\n" +
                "\t (35,0,26,100.0,100.0,0.0),\n" +
                "\t (36,0,36,100.0,100.0,0.0),\n" +
                "\t (37,0,46,100.0,100.0,0.0),\n" +
                "\t (38,0,56,100.0,100.0,0.0),\n" +
                "\t (39,0,66,100.0,100.0,0.0),\n" +
                "\t (40,0,76,100.0,100.0,0.0),\n" +
                "\t (41,0,86,100.0,100.0,0.0),\n" +
                "\t (42,0,96,100.0,100.0,0.0),\n" +
                "\t (43,0,78,100.0,10.0,0.0),\n" +
                "\t (44,0,25,100.0,10.0,0.0),\n" +
                "\t (45,0,14,100.0,10.0,0.0);");
    }
    /**
     * Migracion para controlar las listas_bote(Lista o Bote segun el valor del campo bote) de Jugadas
     * */
    private void f_create_table_Jugada_TopeLista() {
        //f_drop_if_exist(this.handler, "Jugada_TopeLista");
        this.handler.execSQL("CREATE TABLE Jugada_TopeLista (\n" +
                "    idJugada_TopeLista INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idJugada           INTEGER REFERENCES Jugada (idJugada) ON DELETE CASCADE\n" +
                "                                                            ON UPDATE CASCADE,\n" +
                "    idTopeLista_jugada INTEGER REFERENCES TopeLista_jugada (idTopeLista_jugada) ON DELETE CASCADE\n" +
                "                                                                                ON UPDATE CASCADE,\n" +
                "    idConjuntoJugada   INTEGER REFERENCES ConjuntoJugada   (idConjuntoJugada)   ON DELETE CASCADE\n" +
                "                                                                                ON UPDATE CASCADE,\n" +
                "    isBote             INTEGER DEFAULT (0),\n" +
                "    fecha              DATE,\n" +
                "    hora               TIME\n" +
                ");\n");
        //Migracion
        this.handler.execSQL("INSERT INTO Jugada_TopeLista (idJugada_TopeLista,idJugada,idTopeLista_jugada,idConjuntoJugada,isBote,fecha,hora) VALUES\n" +
                "\t (8,9,1,10,0,'2023-02-19','14:50:45'),\n" +
                "\t (9,10,1,11,0,'2023-02-19','15:06:29'),\n" +
                "\t (10,11,1,12,0,'2023-02-19','15:06:49'),\n" +
                "\t (11,12,1,13,0,'2023-02-19','15:07:23'),\n" +
                "\t (12,13,1,14,0,'2023-02-19','15:07:51'),\n" +
                "\t (13,14,1,15,0,'2023-02-19','15:19:22'),\n" +
                "\t (14,15,1,16,0,'2023-02-19','15:19:31'),\n" +
                "\t (15,16,1,16,1,'2023-02-19','15:19:31'),\n" +
                "\t (16,17,1,17,0,'2023-02-19','15:19:46'),\n" +
                "\t (17,18,1,17,1,'2023-02-19','15:19:46'),\n" +
                "\t (18,19,1,18,0,'2023-02-19','07:31:06'),\n" +
                "\t (19,20,1,19,0,'2023-02-19','18:33:05'),\n" +
                "\t (20,21,1,19,1,'2023-02-19','18:33:05'),\n" +
                "\t (21,22,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (22,23,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (23,24,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (24,25,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (25,26,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (26,27,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (27,28,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (28,29,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (29,30,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (30,31,1,20,1,'2023-02-19','18:33:24'),\n" +
                "\t (31,32,1,20,0,'2023-02-19','18:33:24'),\n" +
                "\t (32,33,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (33,34,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (34,35,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (35,36,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (36,37,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (37,38,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (38,39,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (39,40,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (40,41,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (41,42,1,21,0,'2023-02-19','18:33:41'),\n" +
                "\t (42,43,1,26,1,'2023-02-24','22:23:09'),\n" +
                "\t (43,44,1,26,0,'2023-02-24','22:23:09'),\n" +
                "\t (44,45,1,26,0,'2023-02-24','22:23:09');");
    }

    private void f_create_table_Menu() {
        //f_drop_if_exist(this.handler, "Menu");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Menu (\n" +
                "    idMenu INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    menu   TEXT (35) \n" +
                ");");
    }

    private void f_create_table_NumeroLimitado() {
        f_drop_if_exist(this.handler, "NumeroLimitado");
        this.handler.execSQL("CREATE TABLE NumeroLimitado (\n" +
                "    idNumeroLimitado INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    num              INTEGER,\n" +
                "    valorLimite    REAL,\n" +
                "    fechaIni         DATETIME,\n" +
                "    fechaFin         DATETIME\n" +
                ");\n");
    }

    private void f_create_table_Parlet() {
        //f_drop_if_exist(this.handler, "Parlet");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Parlet (\n" +
                "    idParlet    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    num1        INTEGER,\n" +
                "    num2        INTEGER,\n" +
                "    valorParlet REAL\n" +
                ");");
        //Migracion
        this.handler.execSQL("INSERT INTO Parlet (idParlet,num1,num2,valorParlet) VALUES\n" +
                "\t (1,78,58,4500.0),\n" +
                "\t (2,58,78,490.0),\n" +
                "\t (3,78,58,10.0),\n" +
                "\t (4,78,58,40.0),\n" +
                "\t (5,89,52,33.333332),\n" +
                "\t (6,89,9,33.333332),\n" +
                "\t (7,52,9,33.333332),\n" +
                "\t (8,78,25,333.33334),\n" +
                "\t (9,78,14,333.33334),\n" +
                "\t (10,25,14,333.33334);");
    }

/**
 * Migracion para controlar las listas_bote(Lista o Bote segun el valor del campo bote) de Parlets
 * */
    private void f_create_table_Parlet_TopeLista() {
        //f_drop_if_exist(this.handler, "Parlet_TopeLista");
        this.handler.execSQL("CREATE TABLE Parlet_TopeLista (\n" +
                "    idParlet_topeLista INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idParlet           INTEGER REFERENCES Parlet (idParlet) ON DELETE CASCADE\n" +
                "                                                            ON UPDATE CASCADE,\n" +
                "    idTopeLista_parlet INTEGER REFERENCES TopeLista_parlet (idTopeLista_parlet) ON DELETE CASCADE\n" +
                "                                                                                ON UPDATE CASCADE,\n" +
                "    idConjuntoJugada   INTEGER REFERENCES ConjuntoJugada (idConjuntoJugada) ON DELETE NO ACTION\n" +
                "                                                                            ON UPDATE NO ACTION,\n" +
                "    isBote             INTEGER DEFAULT (0),\n" +
                "    fecha              DATE,\n" +
                "    hora               TIME\n" +
                ");\n");
        //Migracion
        this.handler.execSQL("INSERT INTO Parlet_TopeLista (idParlet_topeLista,idParlet,idTopeLista_parlet,idConjuntoJugada,isBote,fecha,hora) VALUES\n" +
                "\t (1,1,1,22,0,'2023-02-20','22:52:26'),\n" +
                "\t (2,2,1,23,0,'2023-02-20','22:56:32'),\n" +
                "\t (3,3,1,24,0,'2023-02-20','22:56:45'),\n" +
                "\t (4,4,1,24,1,'2023-02-20','22:56:45'),\n" +
                "\t (5,5,1,25,0,'2023-02-24','06:46:24'),\n" +
                "\t (6,6,1,25,0,'2023-02-24','06:46:24'),\n" +
                "\t (7,7,1,25,0,'2023-02-24','06:46:24'),\n" +
                "\t (8,8,1,26,0,'2023-02-24','22:23:09'),\n" +
                "\t (9,9,1,26,0,'2023-02-24','22:23:09'),\n" +
                "\t (10,10,1,26,0,'2023-02-24','22:23:09');");
    }

    private void f_create_table_ParletLimitado() {
        f_drop_if_exist(this.handler, "ParletLimitado");
        this.handler.execSQL("CREATE TABLE ParletLimitado (\n" +
                "    idParletLimitado INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    num1             INTEGER,\n" +
                "    num2             INTEGER,\n" +
                "    valorLimite      REAL,\n" +
                "    fechaIni         DATETIME,\n" +
                "    fechaFin         DATETIME\n" +
                ");");
    }

    private void f_create_table_Permiso() {
        //f_drop_if_exist(this.handler, "Permiso");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Permiso (\n" +
                "    IdPermiso INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    permiso   TEXT (35) \n" +
                ");");
    }

    private void f_create_table_Persona() {
        //f_drop_if_exist(this.handler, "Persona");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Persona (\n" +
                "    idPersona INTEGER    PRIMARY KEY AUTOINCREMENT,\n" +
                "    ci        TEXT (11),\n" +
                "    nombre    TEXT (35),\n" +
                "    apellido  TEXT (80),\n" +
                "    dir       TEXT (255),\n" +
                "    correo    TEXT (100) \n" +
                ");");

        this.handler.execSQL("INSERT INTO Persona (idPersona, ci, nombre, apellido, dir, correo) " +
                "VALUES (1, '3333333333', 'tttt', 'gggg', 'fgsd fdsfdsfsdf', 'dsd@df.cu');");
    }

    private void f_create_table_SubMenu() {
        //f_drop_if_exist(this.handler, "SubMenu");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS SubMenu (\n" +
                "    idPermiso INTEGER   PRIMARY KEY\n" +
                "                        REFERENCES Permiso (IdPermiso) ON DELETE CASCADE\n" +
                "                                                       ON UPDATE CASCADE,\n" +
                "    idMenu    INTEGER   REFERENCES Menu (idMenu) ON DELETE CASCADE\n" +
                "                                                 ON UPDATE CASCADE,\n" +
                "    subMenu   TEXT (35) \n" +
                ");");
    }

    private void f_create_table_TarifaPago() {
        //f_drop_if_exist(this.handler, "TarifaPago");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS TarifaPago (\n" +
                "    idTarifaPago INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    idTipoJugada INTEGER  REFERENCES TipoJugada (idTipoJugada) ON DELETE CASCADE\n" +
                "                                                               ON UPDATE CASCADE,\n" +
                "    idPersona    INTEGER  REFERENCES Persona (idPersona) ON DELETE CASCADE \n"+
                "                                                                   ON UPDATE CASCADE,\n"+
                "    valorPago_porciento_lista INTEGER,\n" +
                "    valorPago_porciento_bote  INTEGER,\n" +
                "    fechaIni     DATETIME,\n" +
                "    fechaFin     DATETIME\n" +
                ");");
    }

    private void f_create_table_TipoJugada() {
        //f_drop_if_exist(this.handler, "TipoJugada");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS TipoJugada (\n" +
                "    idTipoJugada INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    tipoJugda    TEXT (35) \n" +
                ");");
        //Migracion
        this.handler.execSQL("INSERT INTO TipoJugada (idTipoJugada,tipoJugda) VALUES\n" +
                "\t (1,'Parlets'),\n" +
                "\t (2,'Centena'),\n" +
                "\t (3,'Fijo'),\n" +
                "\t (4,'Corrido');");
    }

    private void f_create_table_TipoUsuario() {
        //f_drop_if_exist(this.handler, "TipoUsuario");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS TipoUsuario (\n" +
                "    idTipoUsuario INTEGER   PRIMARY KEY AUTOINCREMENT,\n" +
                "    tipoUsuario   TEXT (35) \n" +
                ");");


        //Migracion
        this.handler.execSQL("INSERT INTO TipoUsuario (idTipoUsuario, tipoUsuario) VALUES (1, 'listero');");
        this.handler.execSQL("INSERT INTO TipoUsuario (idTipoUsuario, tipoUsuario) VALUES (2, 'admin');");
    }

    /**
     * Migracion para controlar el limite permitodo de recogida(Suma de todos los parlets que han sido recogigo
     * en parlet_topeLista debe de ser menor o igual que el limite sino coloca 1 al campo bote de parlet_topeLista)
     * en lista de parlet por un usuario
     * */
    private void f_create_table_TopeLista_parlet() {
        f_drop_if_exist(this.handler, "TopeLista_parlet");
        this.handler.execSQL("CREATE TABLE TopeLista_parlet (\n" +
                "    idTopeLista_parlet INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    idPersona          INTEGER  REFERENCES Usuario (idPersona) ON DELETE CASCADE\n" +
                "                                                               ON UPDATE CASCADE,\n" +
                "    tope               REAL,\n" +
                "    fechaIni           DATETIME,\n" +
                "    fechaFin           DATETIME\n" +
                ");");

        //Migracion
        this.handler.execSQL("INSERT INTO TopeLista_parlet (idTopeLista_parlet,idPersona,tope,fechaIni,fechaFin) VALUES\n" +
                "\t (1,1,5000.0,NULL,NULL);");
    }

    /**
     * Migracion para controlar el limite permitodo de recogida(Suma de todos las jugadas que han sido recogiga
     * en jugada_topeLista debe de ser menor o igual que el limite sino coloca 1 al campo bote de jugada_topeLista)
     * en lista de Jugada por un usuario
     * */
     private void f_create_table_TopeLista_jugada() {
            f_drop_if_exist(this.handler, "TopeLista_jugada");
            this.handler.execSQL("CREATE TABLE TopeLista_jugada (\n" +
                    "    idTopeLista_jugada INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                    "    idPersona          INTEGER  REFERENCES Usuario (idPersona) ON DELETE CASCADE\n" +
                    "                                                               ON UPDATE CASCADE,\n" +
                    "    topeFijo           REAL     DEFAULT ( -1),\n" +
                    "    topeCorrido        REAL     DEFAULT ( -1),\n" +
                    "    topeCentena        REAL     DEFAULT ( -1),\n" +
                    "    fechaIni           DATETIME,\n" +
                    "    fechaFin           DATETIME\n" +
                    ");");
         //Migracion
         this.handler.execSQL("INSERT INTO TopeLista_jugada (idTopeLista_jugada,idPersona,topeFijo,topeCorrido,topeCentena,fechaIni,fechaFin) VALUES\n" +
                 "\t (1,1,5000.0,-1.0,-1.0,NULL,NULL);");
     }

    private void f_create_table_Usuario() {
        //f_drop_if_exist(this.handler, "Usuario");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Usuario (\n" +
                "    idPersona     INTEGER   PRIMARY KEY\n" +
                "                            REFERENCES Persona (idPersona) ON DELETE CASCADE\n" +
                "                                                           ON UPDATE CASCADE,\n" +
                "    idTipoUsuario INTEGER   REFERENCES TipoUsuario (idTipoUsuario) ON DELETE CASCADE\n" +
                "                                                                   ON UPDATE CASCADE,\n" +
                "    nombreUsuario TEXT (35),\n" +
                "    pass          TEXT (50),\n" +
                "    isBlock       INTEGER   DEFAULT (0) \n" +
                ");");

        //Migracion
        this.handler.execSQL("INSERT INTO Usuario (idPersona, idTipoUsuario, nombreUsuario, pass, isBlock) " +
                "VALUES (1, 1, 'yunior', '1234', 0)," +
                "(2,2,'edi','1111',0);");
    }

    private void f_create_table_Usuario_Grupo() {
        //f_drop_if_exist(this.handler, "Usuario_Grupo");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Usuario_Grupo (\n" +
                "    idUsuariorGrupo INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idUsuario       INTEGER REFERENCES Usuario (idPersona) ON DELETE CASCADE\n" +
                "                                                           ON UPDATE CASCADE,\n" +
                "    idGrupo         INTEGER REFERENCES Grupo (idGrupo) ON DELETE CASCADE\n" +
                "                                                       ON UPDATE CASCADE\n" +
                ");");
    }

    private void f_create_table_Usuario_Permiso() {
        //f_drop_if_exist(this.handler, "Usuario_Permiso");
        this.handler.execSQL("CREATE TABLE IF NOT EXISTS Usuario_Permiso (\n" +
                "    idUsuarioPermiso INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    idPermiso        INTEGER REFERENCES Permiso (IdPermiso) ON DELETE CASCADE\n" +
                "                                                            ON UPDATE CASCADE,\n" +
                "    idUsuario        INTEGER REFERENCES Usuario (idPersona) ON DELETE CASCADE\n" +
                "                                                            ON UPDATE CASCADE\n" +
                ");");
    }

    private void f_create_table_Tiro() {
        //f_drop_if_exist(this.handler, "Tiro");
        this.handler.execSQL("CREATE TABLE Tiro (\n" +
                "    idTiro   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    centena  INTEGER,\n" +
                "    fijo     INTEGER,\n" +
                "    corrido1 INTEGER,\n" +
                "    corrido2 INTEGER,\n" +
                "    fechaIni DATE,\n" +
                "    fechaFin DATE,\n" +
                "    hora     TIME\n" +
                ");");
    }

    private void f_drop_if_exist(SQLiteDatabase db, String tabla) {
        db.execSQL("DROP TABLE IF EXISTS "+ tabla);
    }

}
