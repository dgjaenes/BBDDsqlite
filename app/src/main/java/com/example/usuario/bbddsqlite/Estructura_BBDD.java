package com.example.usuario.bbddsqlite;

import android.provider.BaseColumns;

/**
 * Created by usuario on 03/10/2017.
 */

public class Estructura_BBDD {

    private Estructura_BBDD() {
    }
   // public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "DatosUsuario";
        public static final String COLUMN_NAME1 = "ID";
        public static final String COLUMN_NAME2 = "Nombre";
        public static final String COLUMN_NAME3 = "Apellidos";

    //}

    //sentencia sql para generar la tabla en la base de datos
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BBDD.TABLE_NAME + " (" +
                    Estructura_BBDD.COLUMN_NAME1 + " INTEGER PRIMARY KEY," +
                    Estructura_BBDD.COLUMN_NAME2 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMN_NAME3 + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;
}
