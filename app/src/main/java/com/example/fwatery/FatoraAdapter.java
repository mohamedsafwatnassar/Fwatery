package com.example.fwatery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Models.Fatora;

import java.util.List;

public class FatoraAdapter extends RecyclerView.Adapter<FatoraAdapter.FatoraViewHolder> {

    List<Fatora> fatoraList;
    Context context;

    public FatoraAdapter(Context context , List<Fatora> fatoraList) {
        this.fatoraList = fatoraList;
        this.context = context;
    }

    @NonNull
    @Override
    public FatoraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fatora, parent,false);
        return new FatoraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FatoraViewHolder holder, int position) {
        final Fatora fatora = fatoraList.get(position);
        holder.name.setText(fatora.getName());
        holder.phone.setText(fatora.getPhone());
        holder.price.setText(new StringBuilder("$ ").append(fatora.getPrice()));
        holder.date.setText(fatora.getDate());
    }

    @Override
    public int getItemCount() {
        if(fatoraList ==null)
            return 0;
        return fatoraList.size();
    }

    public class FatoraViewHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView name, phone, price, date;

        public FatoraViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            price = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date);

        }

    }
}
