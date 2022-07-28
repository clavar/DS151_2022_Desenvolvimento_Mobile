package com.example.prova_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ExtratoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
    }

    public void cadastrar(View view) {

        EditText descricao = findViewById(R.id.editTextDescricaoCadastro);
        EditText valor = findViewById(R.id.editTextValorCadastro);

        double valor_double = Double.parseDouble(valor.getText().toString());
        String descricao_string;

    }
}