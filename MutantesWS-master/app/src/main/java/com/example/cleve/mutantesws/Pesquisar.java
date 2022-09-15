package com.example.cleve.mutantesws;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class Pesquisar extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<String> lista = new ArrayList();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        this.adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.lista);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(this.adapter);

    }
    public void pesquisar(View view) {
        final String busca = ((EditText) findViewById(R.id.editText)).getText().toString();
        if (busca.isEmpty()) {
            Toast.makeText(this, "Digite uma chave de busca", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton rb = (RadioButton) findViewById(R.id.rb1);

            if (rb.isChecked()) {
                String url =  Volley.URL + "?operacao=pegaMutante&chave=" + busca;
                RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
                final Context contexto = this;
                final List<String> temp = this.lista;
                StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        temp.clear();
                        temp.add(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError erro) {
                        android.widget.Toast.makeText(contexto, "Falha na conexão", Toast.LENGTH_LONG).show();
                    }
                });

                filaRequest.add(request);

            }else {
                String url =  Volley.URL + "?operacao=pegaMutantesPorPoder&chave=" + busca;
                RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
                final Context contexto = this;
                final List<String> temp = this.lista;
                StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        temp.clear();
                        String[] parts = response.split("\\r?\\n");
                        Mutante mutante;
                        for(String p: parts) {
                        temp.add(p);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError erro) {
                        android.widget.Toast.makeText(contexto, "Falha na conexão", Toast.LENGTH_LONG).show();
                    }
                });

                filaRequest.add(request);

            }
            this.adapter.notifyDataSetChanged();
        }
    }
}

