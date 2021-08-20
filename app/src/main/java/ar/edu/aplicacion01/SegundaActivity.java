package ar.edu.aplicacion01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;


public class SegundaActivity extends AppCompatActivity {
    String nombre;
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        nombre = getIntent().getExtras().getString("nombreIngresado");
        texto = findViewById(R.id.mensaje);
        texto.setText("Hola a todos, soy " + nombre);
    }
}
