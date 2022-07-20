package com.example.teamsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamsapp.R;
import com.example.teamsapp.model.Team;

import java.util.List;

public class AdapterTeams extends RecyclerView.Adapter<AdapterTeams.MyViewHolder> {

    private List<Team> listTeams;

    public AdapterTeams(List<Team> list) {
        this.listTeams = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, location, titles;
        ImageView logo;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textViewName);
            location = view.findViewById(R.id.textViewLocation);
            titles = view.findViewById(R.id.textViewTitles);
            logo = view.findViewById(R.id.imageView);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_teams, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Team obj = listTeams.get(position);
        holder.name.setText(obj.getName());
        holder.name.setText(obj.getName());
        holder.location.setText(obj.getLocation());
        //holder.titles.setText(obj.getTitles());
        holder.logo.setImageResource(obj.getLogo());
    }

    @Override
    public int getItemCount() {
        return listTeams.size();
    }
}
