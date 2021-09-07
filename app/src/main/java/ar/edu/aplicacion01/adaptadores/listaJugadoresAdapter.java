package ar.edu.aplicacion01.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ar.edu.aplicacion01.R;
import ar.edu.aplicacion01.entidades.Jugadores;

public class listaJugadoresAdapter extends RecyclerView.Adapter<listaJugadoresAdapter.JugadorViewHolder>{
    ArrayList<Jugadores> listaJugadores;
    public listaJugadoresAdapter(ArrayList<Jugadores> listaJugadores){
        this.listaJugadores = listaJugadores;
    }
    @NonNull
    @Override
    public listaJugadoresAdapter.JugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_jugador,null,false);
        return new JugadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaJugadoresAdapter.JugadorViewHolder holder, int position) {
        holder.viewNombre.setText(listaJugadores.get(position).getNombre());
        holder.viewVictorias.setText(listaJugadores.get(position).getCont_victorias());
        holder.viewDerrotas.setText(listaJugadores.get(position).getCont_derrotas());
        holder.viewEmpates.setText(listaJugadores.get(position).getCont_empates());
    }

    @Override
    public int getItemCount() {
       return listaJugadores.size();

    }

    public class JugadorViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre,viewVictorias,viewDerrotas,viewEmpates;
        public JugadorViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewVictorias = itemView.findViewById(R.id.cont_victorias);
            viewDerrotas = itemView.findViewById(R.id.cont_derrotas);
            viewEmpates = itemView.findViewById(R.id.cont_empate);
        }
    }
}
