package com.example.usuario.bbddsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    BBDD_help helper;
    EditText txtnombre,txtapellidos, iduser;

//MEJOR HACERLO CON CURSOSWRAPPER


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnombre= (EditText) findViewById(R.id.nombre);
        txtapellidos= (EditText) findViewById(R.id.apellidos);
        iduser= (EditText) findViewById(R.id.iddatos);
        Button insertar=(Button)findViewById(R.id.btninsert);
        insertar.setOnClickListener(this);
        Button actualizar=(Button)findViewById(R.id.btnactualizar);
        actualizar.setOnClickListener(this);
        Button buscar=(Button)findViewById(R.id.btnbuscar);
        buscar.setOnClickListener(this);
        Button borrar=(Button)findViewById(R.id.btnborrar);
        borrar.setOnClickListener(this);

        helper= new BBDD_help(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnactualizar:
                actualizar();
                break;
            case R.id.btnborrar:
                eliminar();
                break;
            case R.id.btnbuscar:
                buscar();
                break;
            case R.id.btninsert:
                insertar();
                break;
        }
    }

    public void insertar(){
// Gets the data repository in write mode
        SQLiteDatabase db = helper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMN_NAME1, iduser.getText().toString());
        values.put(Estructura_BBDD.COLUMN_NAME2, txtnombre.getText().toString());
        values.put(Estructura_BBDD.COLUMN_NAME3, txtapellidos.getText().toString());

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);
        Toast.makeText(getApplicationContext(), "se guardo registro con clave: "+newRowId,Toast.LENGTH_LONG).show();
        db.close();

    }

    public void buscar(){
        SQLiteDatabase db = helper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                Estructura_BBDD.COLUMN_NAME2,
                Estructura_BBDD.COLUMN_NAME3,
        };

// Filter results WHERE
        String selection = Estructura_BBDD.COLUMN_NAME1 + " = ?";
        String[] selectionArgs = { iduser.getText().toString() };

// How you want the results sorted in the resulting Cursor
        //String sortOrder = Estructura_BBDD.COLUMN_NAME2 + " DESC";

        Cursor c = db.query(
                Estructura_BBDD.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null    //sortOrder                                 // The sort order
        );
        c.moveToFirst();
        //inserta el resultado de la busquedad por id en los campos de nombre y apellidos
       txtnombre.setText(c.getString(0));
        txtapellidos.setText(c.getString(1));
        c.close();
        db.close();

    }
    public void eliminar(){
        SQLiteDatabase db = helper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = Estructura_BBDD.COLUMN_NAME1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { iduser.getText().toString() };
// Issue SQL statement.
        db.delete(Estructura_BBDD.TABLE_NAME, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "se borró registro con clave: "+ iduser.getText().toString(),Toast.LENGTH_LONG).show();
        db.close();
    }
    public void actualizar(){
        SQLiteDatabase db = helper.getReadableDatabase();

// New value for column
        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMN_NAME2, txtnombre.getText().toString() );
        values.put(Estructura_BBDD.COLUMN_NAME3, txtapellidos.getText().toString() );

// Which row to update, based on the title
        String selection = Estructura_BBDD.COLUMN_NAME1 + " LIKE ?";
        String[] selectionArgs = { iduser.getText().toString()};

        int count = db.update(
                Estructura_BBDD.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        Toast.makeText(getApplicationContext(), "se actualizo registro con clave: "+ iduser.getText().toString(),Toast.LENGTH_LONG).show();
        db.close();

    }
}
