package com.example.cleve.mutantesws;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view){
        EditText usr = (EditText) findViewById(R.id.editText2);
        EditText snh = (EditText) findViewById(R.id.editText4);

        String usuario = usr.getText().toString();
        String senha = snh.getText().toString();
        if(usuario.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }else {
            String url = Volley.URL + "?operacao=autenticar&usuario=" + usuario + "&senha=" + senha;
            this.verificar(url, usuario);
        }
    }

    private void verificar(String url, final String usuario){
        RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
        final Context contexto = this;
        StringRequest request = new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("1")) {
                    Volley.usuario = usuario;
                    startActivity(new Intent(contexto, MainActivity.class));
                    finish();
                }else{
                    android.widget.Toast.makeText(contexto, "Senha ou usuario inválidos", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError erro) {
                android.widget.Toast.makeText(contexto, "Falha na conexão", Toast.LENGTH_LONG).show();
                System.out.println(erro);
            }
        });

        filaRequest.add(request);
    }

}
