package com.example.manchild; // Mude para o pacote do seu projeto se for diferente

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GuessActivity extends AppCompatActivity {

    private TextView tvSomaResultado, tvMensagemFinal;
    private EditText etPalpite1, etPalpite2;
    private Button btnVerificar, btnVoltar; // btnVoltar adicionado
    private int numeroOriginal1, numeroOriginal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        tvSomaResultado = findViewById(R.id.tvSomaResultado);
        tvMensagemFinal = findViewById(R.id.tvMensagemFinal);
        etPalpite1 = findViewById(R.id.etPalpite1);
        etPalpite2 = findViewById(R.id.etPalpite2);
        btnVerificar = findViewById(R.id.btnVerificar);
        btnVoltar = findViewById(R.id.btnVoltar); // Referência do novo botão

        // Recebe os dados da MainActivity
        int soma = getIntent().getIntExtra("SOMA", 0);
        numeroOriginal1 = getIntent().getIntExtra("NUM1", 0);
        numeroOriginal2 = getIntent().getIntExtra("NUM2", 0);

        tvSomaResultado.setText(String.valueOf(soma));

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

                // A lógica abaixo permite que o usuário acerte independente da ordem
                // que ele digitou (ex: se era 5 e 3, ele ganha digitando 3 e 5 também)
                boolean acertouOrdemCerta = (palpite1 == numeroOriginal1 && palpite2 == numeroOriginal2);
                boolean acertouOrdemInvertida = (palpite1 == numeroOriginal2 && palpite2 == numeroOriginal1);

                if (acertouOrdemCerta || acertouOrdemInvertida) {
                    tvMensagemFinal.setText("Parabéns! Você acertou.");
                    tvMensagemFinal.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    tvMensagemFinal.setText("Errou *Risada maléfica do Dipp*");
                    tvMensagemFinal.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });

        // Ação do botão voltar
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // O método finish() encerra esta Activity e o Android automaticamente
                // retorna para a Activity anterior na pilha (MainActivity).
                finish();
            }
        });
    }
}