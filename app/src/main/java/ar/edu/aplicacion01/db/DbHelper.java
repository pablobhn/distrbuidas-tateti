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

import ar.edu.aplicacion01.entidades.Jugadores;

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
                "nombre TEXT NOT NULL," +
                "UNIQUE(nombre)" +
                ")"
                ;
        Log.d(null,query);
        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE" + TABLE_JUGADORES);
        onCreate(sqLiteDatabase);
    }

    public void player1win(String jugador){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre",jugador);
        String query = "SELECT * FROM t_contactos";

        Cursor cursor = null;
        if (db != null){
            db.insert(TABLE_JUGADORES,null,cv);
        }
        cv.put("cont_empate",70);
        String[] a1=new String[1];
        a1[0] = jugador;

       Cursor aux = db.rawQuery("UPDATE t_contactos SET cont_victorias = cont_victorias + 1 WHERE nombre = ?",a1);
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


    public void player1draw(String jugador){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre",jugador);
        String query = "SELECT * FROM t_contactos";

        Cursor cursor = null;
        if (db != null){
            db.insert(TABLE_JUGADORES,null,cv);
        }
        cv.put("cont_empate",70);
        String[] a1=new String[1];
        a1[0] = jugador;

        Cursor aux = db.rawQuery("UPDATE t_contactos SET cont_empate = cont_empate + 1 WHERE nombre = ?",a1);
        //db.update(TABLE_JUGADORES,cv,"nombre = ",a1);
        if (aux.getCount() > 0)
        {
            long result = db.update (TABLE_JUGADORES,cv,"id = 1",null);
        }
    }
    public void player2draw(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre","CPU");
        db.insert(TABLE_JUGADORES,null,cv);

        String[] a1=new String[1];
        a1[0] = "CPU";

        Cursor aux = db.rawQuery("UPDATE t_contactos SET cont_empate = cont_empate + 1 WHERE nombre = 'CPU'",null);
        //db.update(TABLE_JUGADORES,cv,"nombre = ",a1);
        if (aux.getCount() > 0)
        {
            long result = db.update (TABLE_JUGADORES,cv,"nombre = 'CPU'",null);
        }
    }
    public void player2lost(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre","CPU");
        db.insert(TABLE_JUGADORES,null,cv);

        String[] a1=new String[1];
        a1[0] = "CPU";

        Cursor aux = db.rawQuery("UPDATE t_contactos SET cont_derrotas = cont_derrotas + 1 WHERE nombre = 'CPU'",null);
        //db.update(TABLE_JUGADORES,cv,"nombre = ",a1);
        if (aux.getCount() > 0)
        {
            long result = db.update (TABLE_JUGADORES,cv,"nombre = 'CPU'",null);
        }
    }

    public void player2win ( ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre","CPU");
       db.insert(TABLE_JUGADORES,null,cv);

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

    public ArrayList <Jugadores> mostrarJugadores (){
        DbHelper dbHelper = new DbHelper (context);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Jugadores> listaJugadores = new ArrayList <>();
        Jugadores jugador = null;
        Cursor cursorJugadores = null;
        cursorJugadores = db.rawQuery("SELECT * FROM t_contactos",null);
                if (cursorJugadores.moveToFirst())
                {
                    do{
                        jugador = new Jugadores();
                        jugador.setId(cursorJugadores.getInt(0));
                        jugador.setNombre(cursorJugadores.getString(1));
                        jugador.setCont_victorias(cursorJugadores.getString(2));
                        jugador.setCont_derrotas(cursorJugadores.getString(3));
                        jugador.setCont_empates(cursorJugadores.getString(4));
                        listaJugadores.add(jugador);
                    }while(cursorJugadores.moveToNext());
                }
                cursorJugadores.close();
                return listaJugadores;
    }


}
