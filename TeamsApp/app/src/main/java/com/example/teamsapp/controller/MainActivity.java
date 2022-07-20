package com.example.teamsapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.teamsapp.R;
import com.example.teamsapp.RecyclerItemClickListener;
import com.example.teamsapp.adapter.AdapterTeams;
import com.example.teamsapp.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTeams;
    private List<Team> listTeams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTeams = findViewById(R.id.recyclerViewTeams);

        this.createTeams();
        AdapterTeams adapter = new AdapterTeams(listTeams);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerViewTeams.setLayoutManager(layoutManager);
        recyclerViewTeams.setHasFixedSize(true);
        recyclerViewTeams.addItemDecoration(
                new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewTeams.setAdapter(adapter);
        recyclerViewTeams.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewTeams,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Team obj = listTeams.get(position);
                                Intent it = new Intent(getApplicationContext(), DetailActivity.class);
                                Bundle params = new Bundle();
                                params.putString("logo", String.valueOf(obj.getLogo()));
                                params.putString("name", obj.getName());
                                params.putString("titles", obj.getTitles());
                                params.putString("location", obj.getLocation());
                                it.putExtras(params);
                                startActivity(it);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }


                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }

    public void createTeams() {
        Team obj = new Team(R.drawable.flamengo, "Flamengo", "Rio de Janeiro - RJ", "Série A: 7 títulos");
        listTeams.add(obj);
        obj = new Team(R.drawable.cam, "Atlético-MG", "Belo Horizonte - MG", "Série A: 2 títulos");
        listTeams.add(obj);
        obj = new Team(R.drawable.cascavel, "FC Cascavel", "Cascavel - PR", "Títulos: 0");
        listTeams.add(obj);
        obj = new Team(R.drawable.parana, "Paraná Clube", "Curitiba - PR", "Série B: 1 título");
        listTeams.add(obj);
    }
}