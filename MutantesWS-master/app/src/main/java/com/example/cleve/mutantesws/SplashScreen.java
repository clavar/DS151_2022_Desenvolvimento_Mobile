package com.example.cleve.mutantesws;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toast.makeText(this, "Mutando...", Toast.LENGTH_SHORT).show();
        Handler h = new Handler();
        h.postDelayed(this, 3000);

    }
    @Override
    public void run(){
        startActivity(new Intent(this, Login.class));
        finish();
    }

}
