package com.example.cleve.mutantesws;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class Listar extends ListActivity {

    private MutantesAdapter mt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        List<Mutante> mutantes = new ArrayList();
        Mutante mutante = new Mutante();
        mutante.setNome("carregando...");
        mutantes.add(mutante);
        this.mt = new MutantesAdapter(this, mutantes) {
            @Override
            public void mais(Mutante mutante) {
                Intent it = new Intent(getApplicationContext(), Editar.class);
                Bundle mut = new Bundle();
                mut.putString("nome", mutante.getNome());
                mut.putParcelable("foto", mutante.getFoto());
                it.putExtras(mut);
                startActivityForResult(it, 1);
            }

            @Override
            public void remove(Mutante mutante) {
                deletarMutante(mutante);
            }

        };
        if(mutantes.size() > 0){
            setListAdapter(this.mt);
        }

        this.listarMutantes();

    }
    private void listarMutantes(){

        final List<Mutante> mutantes = new ArrayList();
        final MutantesAdapter tempMT = this.mt;

        String url =  Volley.URL + "?operacao=listar";
        RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
        final Context contexto = this;

        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()) {
                    String[] parts = response.split("\\n~");
                    String[] parts2;
                    Mutante mutante;
                    for (String p : parts) {
                        parts2 = p.split(" ; ",2);
                        mutante = new Mutante();
                        mutante.setNome(parts2[0]);
                        mutante.setFoto(parts2[1]);
                        mutantes.add(mutante);
                    }
                    tempMT.novosDados(mutantes);
                }
                else{
                    Mutante mutante = null;
                    tempMT.novosDados(mutantes);
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

    public void deletarMutante(Mutante mutante){

        String url =  Volley.URL + "?operacao=remover&nome="+mutante.getNome();
        RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
        final Context contexto = this;

        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ((Listar) contexto).listarMutantes();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError erro) {
                android.widget.Toast.makeText(contexto, "Falha na conexão", Toast.LENGTH_LONG).show();
            }
        });

        filaRequest.add(request);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if (resultCode ==1){
                this.listarMutantes();
            }

        }
    }

}