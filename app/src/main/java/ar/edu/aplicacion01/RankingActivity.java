package ar.edu.aplicacion01;

import android.database.Cursor;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

//import ar.edu.aplicacion01.adaptadores.listaJugacdoresAdapter.ListaJugadoresAdapter;
import ar.edu.aplicacion01.adaptadores.listaJugadoresAdapter;
import ar.edu.aplicacion01.databinding.ActivityRankingBinding;
import ar.edu.aplicacion01.db.DbHelper;
import ar.edu.aplicacion01.entidades.Jugadores;

public class RankingActivity extends AppCompatActivity {

    RecyclerView listaJugadores;
    private AppBarConfiguration appBarConfiguration;
    private ActivityRankingBinding binding;
    ArrayList<Jugadores> listaArrayJugadores;
    DbHelper db;
    ArrayList<String> id, cont_victorias,cont_derrotas,cont_empate,nombre;
//    adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_ranking);
        super.onCreate(savedInstanceState);
        listaJugadores = findViewById(R.id.listaJugadores);
        listaJugadores.setLayoutManager(new LinearLayoutManager(this));
        DbHelper dbJugadores= new DbHelper (RankingActivity.this);
        listaArrayJugadores = new ArrayList<>();
        listaJugadoresAdapter adapter = new listaJugadoresAdapter(dbJugadores.mostrarJugadores());
        listaJugadores.setAdapter(adapter);
        binding = ActivityRankingBinding.inflate(getLayoutInflater());


       // setSupportActionBar(binding.toolbar);



      //  binding.fab.setOnClickListener(new View.OnClickListener() {
  /*          @Override
   public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        db = new DbHelper(RankingActivity.this);
        id = new ArrayList<>();
        cont_victorias = new ArrayList<>();
        cont_derrotas = new ArrayList<>();
        cont_empate = new ArrayList<>();
        storeDataArrays();
        //adaptador = new adaptador(RankingActivity.this,id,cont_victorias,cont_derrotas,cont_empate );
      //  RecyclerView.setAdapter(adaptador);
       // RecyclerView.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
    }

    void storeDataArrays (){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                cont_victorias.add(cursor.getString(1));
                cont_derrotas.add(cursor.getString(2));
                cont_empate.add(cursor.getString(3));

            }
        }

    }

}