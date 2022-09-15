package com.example.cleve.mutantesws;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Editar extends AppCompatActivity {

    private Mutante mutante = new Mutante();
    private TextView nome;
    private int cont =0;
    private EditText parent;
    private ConstraintLayout layout;
    private String nomeAnterior;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        this.parent =  (EditText) findViewById(R.id.nome);
        this.layout = (ConstraintLayout) findViewById(R.id.Clay);
        Intent it = getIntent();
        Bundle mut = it.getExtras();
        TextView nome = (EditText) findViewById(R.id.nome);
        nomeAnterior = (mut.getString("nome"));
        this.foto = (ImageView) findViewById(R.id.foto);
        this.foto.setImageBitmap((Bitmap) mut.getParcelable("foto"));
        nome.setText(nomeAnterior);

     //pegar os poderes
        final List<String> poderes = new ArrayList();

        RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
        final Context contexto = this;
        String url = Volley.URL + "?operacao=pegaPoderes&nome=" + nomeAnterior;
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               String[] parts = response.split("\\r?\\n");
               for(String p: parts) {
                   poderes.add(p);
               }
                //parte dinâmica
                EditText et;
                for(String p : poderes) {
                    et = ((Editar) contexto).adicionarCampo(null);
                    et.setText(p);
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

    public void atualizar(View view){

        TextView nome = (EditText) findViewById(R.id.nome);
        this.mutante.setNome(nome.getText().toString());
        Boolean vazio = false;
        if(mutante.getNome().isEmpty()){
            vazio = true;
        }

        List<String> poderes = new ArrayList();
        EditText poder;

        for(int i = 1; i <= cont; i++){
            poder = (EditText) findViewById(i);
            if(poder.getText().toString().isEmpty()){
                vazio = true;
            }
            poderes.add(poder.getText().toString());
        }
        this.mutante.setPoderes(poderes);
        if(vazio){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }else {

            RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
            final Context contexto = this;
            String url = Volley.URL + "?operacao=atualizar&nome="+mutante.getNome()+"&anterior="+this.nomeAnterior+"&habilidades=";
            for(String h: mutante.getPoderes()){
                url += h+',';
            }
            url += "&foto=temp&usuario="+Volley.usuario;

            StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    android.widget.Toast.makeText(contexto, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    ((Editar) contexto).nomeAnterior = ((Editar) contexto).mutante.getNome();
                    ((Editar) contexto).setResult(1);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError erro) {
                    android.widget.Toast.makeText(contexto, "Falha na conexão", Toast.LENGTH_LONG).show();

                }
            });

            filaRequest.add(request);

        }
    }

    public EditText adicionarCampo(View view){
        cont++;

        EditText et = new EditText(this);
        et.setId(cont);
        et.setHint("Poder");
        et.setText("");

        ConstraintLayout.LayoutParams param = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(param);
        this.layout.addView(et);

        ConstraintSet cs = new ConstraintSet();
        cs.clone(this.layout);
        cs.connect((cont), ConstraintSet.TOP, parent.getId(), ConstraintSet.BOTTOM, 48);
        cs.connect((cont), ConstraintSet.LEFT, parent.getId(), ConstraintSet.LEFT, 0);
        cs.connect((cont), ConstraintSet.RIGHT, parent.getId(), ConstraintSet.RIGHT, 0);
        cs.applyTo(this.layout);
        this.parent = et;
        return et;

    }

    public void removerCampo(View view){
        if (cont == 1){
            Toast.makeText(this, "Deve ter ao menos um poder para ser um mutante!", Toast.LENGTH_LONG).show();
        } else{
            this.layout.removeView((EditText) findViewById(cont));
            if(cont==2){
                this.parent =(EditText) findViewById(R.id.nome);
            } else {
                this.parent = (EditText) findViewById(cont - 1);
            }
            cont--;
        }

    }

    public void voltar(View view){
        finish();
    }
}
