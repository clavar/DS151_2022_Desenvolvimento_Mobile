package com.example.app_multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calcular(View view) {

        EditText nome = findViewById(R.id.et_nome);
        EditText nota1 = findViewById(R.id.et_nota1);
        EditText nota2 = findViewById(R.id.et_nota2);
        EditText freq = findViewById(R.id.et_freq);

        try {
            String nomeAluno = nome.getText().toString();
            int frequencia = Integer.parseInt(freq.getText().toString());
            Double notaum = Double.parseDouble(nota1.getText().toString());
            Double notadois = Double.parseDouble(nota2.getText().toString());
            Double media = (notaum + notadois) / 2;
            String resultado;

            if (100 < frequencia || 0 > frequencia || 10 < notaum || 0 > notaum || 10 < notadois || 0 > notadois) {
                Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show();
            } else {
                if (media < 4) {
                    resultado = "Reprovado por nota!";
                } else if (frequencia < 75) {
                    resultado = "Reprovado por falta!";
                } else {
                    if (media >= 7) {
                        resultado = "Aprovado!";
                    } else {
                        resultado = "Final!";
                    }
                }
                Intent it = new Intent(this,DetailActivity.class);
                Bundle params = new Bundle();
                params.putString("Media", String.valueOf(media));
                params.putString("Resultado", String.valueOf(resultado));
                params.putString("Nome", String.valueOf(nome));
                it.putExtras(params);
                startActivity(it);

                //AQUI PASSAMOS PARA A OUTRA ACTIVITY
            }
        } catch (Exception e) {
            Toast.makeText(this, "Os campos não podem estar em branco", Toast.LENGTH_SHORT).show();
        }
    }
}