package ar.edu.aplicacion01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

import ar.edu.aplicacion01.db.DbHelper;


public class SegundaActivity extends AppCompatActivity implements View.OnClickListener {
    String nombre;
    TextView texto;
    TextView resultText;
    boolean cruces;
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn;
    private int roundCount;

    Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        nombre = getIntent().getExtras().getString("nombreIngresado");
        cruces = getIntent().getExtras().getBoolean("cruces");
        player1Turn = cruces;
        this.texto = findViewById(R.id.titleText);
        this.resultText = findViewById(R.id.resultText);
        this.buttonReset = findViewById(R.id.buttonReset);

        if( cruces )
            texto.setText("Bienvenido " + nombre + " \nJuegas con cruces");
        else
            texto.setText("Bienvenido " + nombre + " \nJuegas con círculos");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Turn = cruces;
                roundCount = 0;
                resultText.setText("Turno de" + nombre);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }

            }
        });


    }
    private void cpuPlay(String x){
    int i = 0;
    int j = 0;
    int flag = 0;
        while (i<3 && flag == 0)
        {
            while (j<3 && flag == 0)
            {
                if (  buttons[i][j].getText() == "")
                {
                    buttons[i][j].setText(x);
                    flag = 1;
                }
                j++;
            }
            i++;
            j=0;
        }
        player1Turn = !player1Turn;
        resultText.setText("Turno de "+nombre);
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            if (cruces)
            {
                resultText.setText("Turno de la maquinaa");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        cpuPlay("O");
                    }
                }, 5000);   //5 seconds

            }
            else
            {
                resultText.setText("Turno de "+nombre);
            }

            ((Button) v).setText("X");
        } else {
            if (cruces)
            {
                resultText.setText("Turno de "+nombre);
            }
            else
            {
                resultText.setText("Turno de la maquina");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        cpuPlay("X");
                    }
                }, 5000);   //5 seconds
            }

            ((Button) v).setText("O");
        }

        roundCount++;
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1Wins() {
        resultText.setText( nombre + " ganó la partida" );
        DbHelper db = new DbHelper(SegundaActivity.this);
        db.player1win(nombre);
        db.player2lost();
    }
    private void player2Wins() {
        DbHelper db = new DbHelper(SegundaActivity.this);
        db.player2win();
        resultText.setText( "La maquina ganó la partida" );
    }
    private void draw() {
        DbHelper db = new DbHelper(SegundaActivity.this);
        db.player1draw(nombre);
        db.player2draw();
    }
}
