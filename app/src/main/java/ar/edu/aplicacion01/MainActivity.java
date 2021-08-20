package ar.edu.aplicacion01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnBoton, btnSiguiente;
    private EditText txtTexto;
    private TextView lblTexto;
    private String nombre = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // this.btnBoton = findViewById(R.id.btnBoton);
        this.btnSiguiente = findViewById(R.id.btnSiguiente);
        this.txtTexto = findViewById(R.id.txtTexto);
        this.lblTexto = findViewById(R.id.lblTexto);

        btnBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = txtTexto.getText().toString();
                lblTexto.setText("Hola " + nombre);
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SegundaActivity.class);
                if(nombre == null || nombre.isEmpty())
                    i.putExtra("nombreIngresado","Extraño");
                else
                    i.putExtra("nombreIngresado", nombre);
                startActivity(i);
            }
        });
    }

}
