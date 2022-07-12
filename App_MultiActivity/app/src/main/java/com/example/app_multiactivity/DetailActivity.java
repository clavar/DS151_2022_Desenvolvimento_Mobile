package com.example.app_multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        System.out.println("0 teste passou aqui");

        TextView mediaView = findViewById(R.id.textViewMedia);
        TextView nomeView = findViewById(R.id.textViewNome);
        TextView situacaoView = findViewById(R.id.textViewSituacao);

        Intent it = getIntent();
        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                String media = params.getString("Media");
                String resultado = params.getString("Resultado");
                String nome = params.getString("Nome");

                mediaView.setText(media);
                nomeView.setText(nome);
                situacaoView.setText(resultado);
            }


        }
    }
}
