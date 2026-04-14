package com.example.manchild; // Mude para o pacote do seu projeto

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1, etNumero2;
    private Button btnJogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        btnJogar = findViewById(R.id.btnJogar);

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNum1 = etNumero1.getText().toString();
                String strNum2 = etNumero2.getText().toString();

                if (strNum1.isEmpty() || strNum2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha os dois números!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int num1 = Integer.parseInt(strNum1);
                int num2 = Integer.parseInt(strNum2);
                int soma = num1 + num2;

                // Envia os dados para a próxima Activity
                Intent intent = new Intent(MainActivity.this, GuessActivity.class);
                intent.putExtra("SOMA", soma);
                intent.putExtra("NUM1", num1);
                intent.putExtra("NUM2", num2);
                startActivity(intent);

                // Limpa os campos para quando o usuário voltar
                etNumero1.setText("");
                etNumero2.setText("");
            }
        });
    }
}