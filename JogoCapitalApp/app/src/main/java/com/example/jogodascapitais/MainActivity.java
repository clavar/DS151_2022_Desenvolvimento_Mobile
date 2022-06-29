package com.example.jogodascapitais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] estado;
    String[] capital;
    Random r = new Random();
    int qtd;
    int aux = 1;
    int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado = new String[]{
                "Amazonas",
                "Minas Gerais",
                "Santa Catarina",
                "São Paulo",
                "Rio de Janeiro",
                "Rio Grande do Sul",
                "Acre",
                "Bahia",
                "Pará",
                "Piauí"
        };
        capital = new String[]{
                "Manaus",
                "Belo Horizonte",
                "Florianópolis",
                "São Paulo",
                "Rio de Janeiro",
                "Porto Alegre",
                "Rio Branco",
                "Salvador",
                "Belém",
                "Teresina"
        };
        Random r = new Random();
        qtd = r.nextInt(10);
        TextView outEstado = findViewById(R.id.estado);
        outEstado.setText(estado[qtd]);
    }

    public void next(View view){
        if(aux < 5) {
            qtd = r.nextInt(10);
            TextView outEstado = findViewById(R.id.estado);
            outEstado.setText(estado[qtd]);
            aux++;
            Button valid = (Button) findViewById(R.id.validador);
            valid.setEnabled(true);
            TextView output = findViewById(R.id.textViewOutput);
            output.setText("");
            EditText input = findViewById(R.id.textViewInput);
            input.setText("");
        }
        else{
            Button val1 = (Button) findViewById(R.id.validador);
            Button val2 = (Button) findViewById(R.id.next);
            val1.setEnabled(false);
            val2.setEnabled(false);
            TextView output = findViewById(R.id.textViewOutput);
            output.setText("Fim de Jogo!");
        }
    }

    public void validacao(View view){
        EditText input = findViewById(R.id.textViewInput);
        TextView output = findViewById(R.id.textViewOutput);
        TextView ponto = findViewById(R.id.pontos);
        Button val1 = (Button) findViewById(R.id.validador);

        String nome_cidade = capital[qtd];
        if(input.length() == 0){
            Toast.makeText(this, "Preencha com o nome da Capital!", Toast.LENGTH_SHORT).show();
        }
        else if (input.getText().toString().toLowerCase().equals(nome_cidade.toLowerCase())){
            output.setText("Parabéns, a resposta está correta!");
            pontos = pontos + 10;
            ponto.setText(String.valueOf(pontos));
            val1.setEnabled(false);
        }
        else{
            output.setText("A resposta está incorreta! \nResposta correta é: "+ nome_cidade);
            val1.setEnabled(false);
        }
    }
}