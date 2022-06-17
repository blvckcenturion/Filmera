package com.example.filmera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmera.Models.Cast;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsViewHolder> {
    Context context;
    List<Cast> actorList;

    public DetailsAdapter(Context context, List<Cast> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.details_container, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.textView_cast_name.setText(actorList.get(position).getName());
        holder.textView_cast_character.setText(actorList.get(position).getAsCharacter());
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }
}
