package com.example.openweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
    }

    public void pesquisar(View view){
        if(editText.getText().length()==0){
            Toast.makeText(this,"Digite a cidade e a sigla do país.",Toast.LENGTH_SHORT).show();
        }else {
            String cidade = editText.getText().toString().trim();

            Call<Dados> call = new RetrofitConfig().getAPIService().getDados(cidade, "c4267ab357b2d3da0844c93f1fd199f5");

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Buscando dados...");
            progressDialog.show();

            call.enqueue(new Callback<Dados>() {
                @Override
                public void onResponse(Call<Dados> call, Response<Dados> response) {
                    if (response.isSuccessful()) {
                        Dados d = response.body();
                        String dados = "";
                        dados += "Umidade: " + d.getMain().getHumidity() + "%";
                        dados += "\nTemperatura atual: " + kelvinToCelsius(d.getMain().getTemp()) + "ºC";
                        dados += "\nTemperatura mínima: " + kelvinToCelsius(d.getMain().getTemp_min()) + "ºC";
                        dados += "\nTemperatura máxima: " + kelvinToCelsius(d.getMain().getTemp_max()) + "ºC";
                        dados += "\nPrevisão de chuva para próxima hora: ";
                        try {
                            dados += d.getRain().getH() + "%";
                        } catch (Exception e) {
                            dados += "Não disponível";
                        }

                        textView.setText(dados);
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao buscar dados.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Dados> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Erro ao buscar dados.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

    private String kelvinToCelsius(float kelvin){
        return String.format("%.2f",kelvin - 273.15F);
    }
}