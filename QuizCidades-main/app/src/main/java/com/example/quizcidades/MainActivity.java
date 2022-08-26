package com.example.quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] cidades = {"barcelona", "brasilia", "curitiba", "lasvegas", "montreal", "paris", "riodejaneiro", "salvador", "saopaulo", "toquio"};
    String[] nomesCorretosCidade = {"Barcelona", "Brasília", "Curitiba", "Las Vegas", "Montreal", "Paris", "Rio de Janeiro", "Salvador", "São Paulo", "Tóquio"};
    String[] cidadesUrl = {"01_barcelona", "02_brasilia", "03_curitiba", "04_lasvegas", "05_montreal", "06_paris", "07_riodejaneiro", "08_salvador", "09_saopaulo", "10_toquio"};
    ArrayList<Integer> sorteados = new ArrayList<>();
    TextView textViewProgresso, textViewResultado;
    EditText editTextCidade;
    ImageView imageViewCidade;

    int sorteado;
    String cidadeSorteada;
    Button button;
    int pontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewProgresso = findViewById(R.id.textViewProgresso);
        textViewResultado = findViewById(R.id.textViewResultado);
        editTextCidade = findViewById(R.id.editTextCidade);
        imageViewCidade = findViewById(R.id.imageViewCidade);

        textViewResultado.setText("");
        button = findViewById(R.id.button);
        sortear();

    }

    public void adivinhar(View view) {
        String resposta = editTextCidade.getText().toString();
        if (resposta.length() == 0) {
            Toast.makeText(this, "Digite uma resposta", Toast.LENGTH_SHORT).show();
        } else {
            resposta = resposta.toLowerCase();
            resposta = resposta.trim().replace(" ", "");
            resposta = Normalizer.normalize(resposta, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (resposta.equals(cidadeSorteada)) {
                textViewResultado.setText("Você acertou!");
                pontuacao += 25;
            } else {
                textViewResultado.setText("Você errou, a resposta certa era: " + nomesCorretosCidade[sorteado]);
            }
        }

        if (sorteados.size() == 4) {
            editTextCidade.setFocusable(false);
            button.setText("Ver resultado");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, ResultadoActivity.class);
                    i.putExtra("resultado", pontuacao);
                    i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(i);
                }
            });
        } else {
            editTextCidade.setText("");
            sortear();
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
         ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void sortear(){
        Random gerador = new Random();
        sorteado = gerador.nextInt(10);
        while(sorteados.contains(sorteado)){
            sorteado = gerador.nextInt(10);
        }
        sorteados.add(sorteado);
        cidadeSorteada = cidades[sorteado];

        /* Se fosse usar drawable seria por aqui

        int resourceId = getResources().getIdentifier(cidadeSorteada, "drawable", getPackageName());
        imageViewCidade.setImageResource(resourceId);
        */

        new DownloadImageTask((ImageView) imageViewCidade)
                .execute("http://200.236.3.202/android/cidades/"+ cidadesUrl[sorteado] + ".jpg");

        textViewProgresso.setText(sorteados.size()+"/4");
    }

}