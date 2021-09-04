package ar.edu.aplicacion01.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_JUGADORES = "t_contactos";
    public static  Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query = "CREATE TABLE "+TABLE_JUGADORES+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cont_victorias INTEGER DEFAULT 0," +
                "cont_derrotas INTEGER DEFAULT 0," +
                "cont_empate INTEGER DEFAULT 0 ,"+
                "nombre TEXT NOT NULL)"
                ;
        Log.d(null,query);
        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE" + TABLE_JUGADORES);
        onCreate(sqLiteDatabase);
    }

    public void addPlayer(String jugador){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre",jugador);
         db.insert(TABLE_JUGADORES,null,cv);
        cv.put("cont_empate",70);
        String[] a1=new String[1];
        a1[0] = jugador;

       Cursor aux = db.rawQuery("UPDATE t_contactos SET cont_victorias = cont_victorias + 1 WHERE id = 1",null);
        //db.update(TABLE_JUGADORES,cv,"nombre = ",a1);
        if (aux.getCount() > 0)
        {
            long result = db.update (TABLE_JUGADORES,cv,"id = 1",null);
        }
   //  long result =  db.insert(TABLE_JUGADORES,null,cv);
     /*  if (aux == -1)
       {
          Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();

       }else{
           Toast.makeText(context,"Agregado exitosamente",Toast.LENGTH_SHORT).show();
       }
    */}

    public void player2win ( ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre","CPU");
       // db.insert(TABLE_JUGADORES,null,cv);

        String[] a1=new String[1];
        a1[0] = "CPU";

        Cursor aux = db.rawQuery("UPDATE t_contactos SET cont_victorias = cont_victorias + 1 WHERE nombre = 'CPU'",null);
        //db.update(TABLE_JUGADORES,cv,"nombre = ",a1);
        if (aux.getCount() > 0)
        {
            long result = db.update (TABLE_JUGADORES,cv,"nombre = 'CPU'",null);
        }
        //  long result =  db.insert(TABLE_JUGADORES,null,cv);
     /*  if (aux == -1)
       {
          Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();

       }else{
           Toast.makeText(context,"Agregado exitosamente",Toast.LENGTH_SHORT).show();
       }
    */}

    public Cursor readAllData(){
        String query = "SELECT * FROM t_contactos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }


}
