package com.example.fwatery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fwatery.Models.A3tal;
import com.example.fwatery.Models.Fatora;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class A3taalAdapter extends RecyclerView.Adapter<A3taalAdapter.A3talViewHolder> {

    List<A3tal> a3talList;
    Context context;

    public A3taalAdapter(Context context , List<A3tal> a3talList) {
        this.a3talList = a3talList;
        this.context = context;
    }

    public void Change(List<A3tal> a3tal){
        a3talList = a3tal ;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public A3talViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        if(Hawk.get("User").equals(true)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_3tl, parent,false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_3tl, parent,false);
        }
        return new A3taalAdapter.A3talViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull A3talViewHolder holder, int position) {
        final A3tal a3taal = a3talList.get(position);
        holder.name.setText(a3taal.getName());
        holder.phone.setText(a3taal.getPhone());
        holder.address.setText(a3taal.getAddress());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String date = simpleDateFormat.format(calendar.getTime());
        holder.date.setText(date);

        if (onItem3tlOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItem3tlOnClickListener.onClick(position, a3taal);
                }
            });
        }
    }

    onItem3tlOnClickListener onItem3tlOnClickListener;

    public A3taalAdapter.onItem3tlOnClickListener getOnItem3tlOnClickListener() {
        return onItem3tlOnClickListener;
    }

    public void setOnItem3tlOnClickListener(A3taalAdapter.onItem3tlOnClickListener onItem3tlOnClickListener) {
        this.onItem3tlOnClickListener = onItem3tlOnClickListener;
    }

    public interface onItem3tlOnClickListener {
        public void onClick(int postion, A3tal a3tal);
    }
    @Override
    public int getItemCount() {
        if(a3talList ==null)
            return 0;
        return a3talList.size();
    }

    public class A3talViewHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView name, phone, address, date;

        public A3talViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            address = (TextView) itemView.findViewById(R.id.address);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
