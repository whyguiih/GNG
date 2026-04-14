package com.example.manchild;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText etSomaPalpite;
    private Button btnJogar;
    private TextView tvResultadoAnterior;
    private int numero1, numero2, somaCorreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSomaPalpite = findViewById(R.id.etSomaPalpite);
        btnJogar = findViewById(R.id.btnJogar);
        tvResultadoAnterior = findViewById(R.id.tvResultadoAnterior);

        sortearNumeros();

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPalpite = etSomaPalpite.getText().toString();

                if (strPalpite.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, digite sua adivinhação.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int palpite = Integer.parseInt(strPalpite);

                if (palpite == somaCorreta) {
                    tvResultadoAnterior.setText("Você acertou a soma anterior!");
                    tvResultadoAnterior.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                } else {
                    tvResultadoAnterior.setText("Você errou a soma anterior. Era " + somaCorreta);
                    tvResultadoAnterior.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }

                etSomaPalpite.setText(""); // Limpa o campo de texto

                Intent intent = new Intent(MainActivity.this, GuessActivity.class);
                intent.putExtra("SOMA", somaCorreta);
                intent.putExtra("NUM1", numero1);
                intent.putExtra("NUM2", numero2);
                startActivity(intent);

                sortearNumeros(); // Sorteia novos números para a próxima rodada na MainActivity
            }
        });
    }

    private void sortearNumeros() {
        Random random = new Random();
        numero1 = random.nextInt(11); // Gera um número entre 0 e 10
        numero2 = random.nextInt(11); // Gera um número entre 0 e 10
        somaCorreta = numero1 + numero2;
    }
}