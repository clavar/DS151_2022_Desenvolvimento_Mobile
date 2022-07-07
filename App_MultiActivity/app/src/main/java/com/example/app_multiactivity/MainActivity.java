package com.example.app_multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nome = findViewById(R.id.et_nome);

    EditText nota1 = findViewById(R.id.et_nota1);
    double var_nota1;

    EditText nota2 = findViewById(R.id.et_nota2);
    double var_nota2;

    EditText freq = findViewById(R.id.et_freq);
    int var_freq;

    TextView resultado = findViewById(R.id.tv_resultado);
    String var_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calcular (View view) {

        //
        // Validar entradas
        //

        if (nome.length() == 0)
            Toast.makeText(this,"Digite o nome do aluno", Toast.LENGTH_SHORT).show();

        if (nota1.length() == 0)
            Toast.makeText(this,"Digite a primeira nota do aluno", Toast.LENGTH_SHORT).show();
        else {
            var_nota1 = Double.parseDouble(nota1.getText().toString());
            validarNota(var_nota1);
        }

        if (nota2.length() == 0)
            Toast.makeText(this,"Digite a segunda nota do aluno", Toast.LENGTH_SHORT).show();
        else{
            var_nota2 = Double.parseDouble(nota1.getText().toString());
            validarNota(var_nota2);
        }

        if (freq.length() == 0)
            Toast.makeText(this,"Digite a frequencia do aluno", Toast.LENGTH_SHORT).show();
        else {
            var_freq = Integer.parseInt(freq.getText().toString());
            validarFreq(var_freq);
        }

        //
        // aprovado = Media final >= 7 e freq >=75
        // final = 4 <= media final <= 6.9 e freq >=75
        // rep por falta = freq < 75
        // rep por nota = media final < 4
        //
        double media = (var_nota1 + var_nota2) / 2;

        if (media < 4) {
            var_resultado = "Reprovado por nota";
        }

        if (var_freq < 75) {
            var_resultado = "Reprovado por falta";
        }

        if (media >= 7 && var_freq >= 75){
            var_resultado = "Aprovado";
        }

        if (media < 7 && media >= 4 && var_freq >= 75){
            var_resultado = "Exame Final";
        }

        resultado.setText(var_resultado);
    }

    public void validarNota(double nota){
        if (nota < 0 || nota > 10)
            Toast.makeText(this,"Nota invalida, digite nota de 0 a 10", Toast.LENGTH_SHORT).show();
    }

    public void validarFreq(int frequencia){
        if (frequencia < 0 || frequencia > 100)
            Toast.makeText(this,"Frequencia invalida, digite valor entre 0 e 100", Toast.LENGTH_SHORT).show();
    }

}