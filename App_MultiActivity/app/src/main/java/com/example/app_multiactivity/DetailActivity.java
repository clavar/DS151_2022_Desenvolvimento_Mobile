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

        TextView mediaView = findViewById(R.id.mediaTextView);
        TextView nomeView = findViewById(R.id.nomeTextView);
        TextView situacaoView = findViewById(R.id.situacaoTextView);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        String media = params.getString("Media");
        String resultado = params.getString("Resultado");
        String nome = params.getString("Nome");
        mediaView.setText(media);
        nomeView.setText(nome);
        situacaoView.setText(resultado);


    }

}
