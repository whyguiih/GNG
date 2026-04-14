package com.example.manchild;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GuessActivity extends AppCompatActivity {

    private TextView tvSomaMostrada, tvResultadoFinal;
    private EditText etPalpite1, etPalpite2;
    private Button btnVerificar, btnVoltar;
    private int numeroOriginal1, numeroOriginal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        tvSomaMostrada = findViewById(R.id.tvSomaMostrada);
        tvResultadoFinal = findViewById(R.id.tvResultadoFinal);
        etPalpite1 = findViewById(R.id.etPalpite1);
        etPalpite2 = findViewById(R.id.etPalpite2);
        btnVerificar = findViewById(R.id.btnVerificar);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Recebe os dados da MainActivity
        int soma = getIntent().getIntExtra("SOMA", 0);
        numeroOriginal1 = getIntent().getIntExtra("NUM1", 0);
        numeroOriginal2 = getIntent().getIntExtra("NUM2", 0);

        tvSomaMostrada.setText("Soma mostrada: " + soma);

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPalpite1 = etPalpite1.getText().toString();
                String strPalpite2 = etPalpite2.getText().toString();

                if (strPalpite1.isEmpty() || strPalpite2.isEmpty()) {
                    Toast.makeText(GuessActivity.this, "Digite os dois palpites!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int palpite1 = Integer.parseInt(strPalpite1);
                int palpite2 = Integer.parseInt(strPalpite2);

                boolean acertouOrdemCerta = (palpite1 == numeroOriginal1 && palpite2 == numeroOriginal2);
                boolean acertouOrdemInvertida = (palpite1 == numeroOriginal2 && palpite2 == numeroOriginal1);

                if (acertouOrdemCerta || acertouOrdemInvertida) {
                    tvResultadoFinal.setText("Parabéns! Você acertou.");
                    tvResultadoFinal.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                } else {
                    tvResultadoFinal.setText("Errou kkkkkkkk otario");
                    tvResultadoFinal.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a Activity atual e volta para a anterior (MainActivity)
            }
        });
    }
}