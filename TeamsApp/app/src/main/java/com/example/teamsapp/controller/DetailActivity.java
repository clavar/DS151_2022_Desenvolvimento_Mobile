package com.example.teamsapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teamsapp.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView logoView = findViewById(R.id.logoImageView);
        TextView nameView = findViewById(R.id.nameTextView);
        TextView locationView = findViewById(R.id.locationTextView);
        TextView titlesView = findViewById(R.id.titlesTextView);

        Intent it = getIntent();
        Bundle params = it.getExtras();

        int logo = Integer.parseInt(params.getString("logo"));
        String name = params.getString("name");
        String location = params.getString("location");
        String titles = params.getString("titles");

        logoView.setImageResource(logo);
        nameView.setText(name);
        locationView.setText(location);
        titlesView.setText(titles);
    }
}