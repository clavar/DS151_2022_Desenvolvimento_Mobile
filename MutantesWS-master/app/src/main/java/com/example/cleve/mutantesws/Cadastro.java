package com.example.cleve.mutantesws;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    private int cont = 1;
    private EditText parent;
    private ConstraintLayout layout;
    private ImageView foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.parent =(EditText) findViewById(R.id.poderes);
        this.layout = (ConstraintLayout) findViewById(R.id.CLayout);

        this.foto= (ImageView) findViewById(R.id.foto);
    }

    public void adicionarCampo(View view){
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
        cs.connect((cont), ConstraintSet.TOP, parent.getId(), ConstraintSet.BOTTOM, 8);
        cs.connect((cont), ConstraintSet.LEFT, parent.getId(), ConstraintSet.LEFT, 0);
        cs.connect((cont), ConstraintSet.RIGHT, parent.getId(), ConstraintSet.RIGHT, 0);
        cs.applyTo(this.layout);

        this.parent = et;
    }

    public void removerCampo(View view){
        if (cont == 1){
            Toast.makeText(this, "Deve ter ao menos um poder para ser um mutante!", Toast.LENGTH_LONG).show();
        } else{
            this.layout.removeView((EditText) findViewById(cont));
            if(cont==2){
                this.parent =(EditText) findViewById(R.id.poderes);
            } else {
                this.parent = (EditText) findViewById(cont - 1);
            }
            cont--;
        }
    }

    public void foto(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    public void adicionar(View view){
        Mutante mutante = new Mutante();
        Boolean vazio = false;
        TextView nome = (EditText) findViewById(R.id.nome);
        mutante.setNome(nome.getText().toString());
        if(mutante.getNome().isEmpty()){
            vazio = true;
        }

        //imagem
        Bitmap bm= ((BitmapDrawable)foto.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
        byte[] b = outputStream.toByteArray();
        final String img=  Base64.encodeToString(b, Base64.DEFAULT);

        List<String> poderes = new ArrayList();
        TextView poder = (TextView) findViewById(R.id.poderes);
        if(poder.getText().toString().isEmpty()){
            vazio = true;
        }
        poderes.add(poder.getText().toString());

        for(int i = 2; i <= cont; i++){
            poder = (TextView) findViewById(i);
            if(poder.getText().toString().isEmpty()){
                vazio = true;
            }
            poderes.add(poder.getText().toString());
        }
        mutante.setPoderes(poderes);

        if(vazio){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }else {

            RequestQueue filaRequest = Volley.getInstancia(this).getFilaRequest();
            final Context contexto = this;

            final String mutNome = mutante.getNome();
            String hab ="";
            for(String h: mutante.getPoderes()){
                hab += h+',';
            }
            final String habilidades = hab;
            final String usr= Volley.usuario;

            StringRequest request = new StringRequest(StringRequest.Method.POST, Volley.URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("1")) {
                        android.widget.Toast.makeText(contexto, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
                    }
                    else{
                        android.widget.Toast.makeText(contexto, "Mutante já cadastrado", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError erro) {
                    System.out.println(erro);
                    android.widget.Toast.makeText(contexto, "Falha na conexão", Toast.LENGTH_LONG).show();
                    android.widget.Toast.makeText(contexto, erro.toString(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("operacao", "adicionar");
                    params.put("nome", mutNome);
                    params.put("habilidades", habilidades);
                    params.put("foto", img);
                    params.put("usuario", usr);
                    return params;
                }
            };
            filaRequest.add(request);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
      //  super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView temp = this.foto;
                this.foto.setImageBitmap(bitmap);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
