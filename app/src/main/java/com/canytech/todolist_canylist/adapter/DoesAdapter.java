package com.canytech.todolist_canylist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canytech.todolist_canylist.EditTaskDesk;
import com.canytech.todolist_canylist.R;
import com.canytech.todolist_canylist.database.MyDoes;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter <DoesAdapter.MyViewHolder>{
    Context context;
    ArrayList<MyDoes> myDoes;

    public DoesAdapter(Context c, ArrayList<MyDoes> p) {
        context = c;
        myDoes = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.titleDoes.setText(myDoes.get(i).getTitleDoes());
        holder.descDoes.setText(myDoes.get(i).getDescDoes());
        holder.dateDoes.setText(myDoes.get(i).getDateDoes());

        final String getTitleDoes = myDoes.get(i).getTitleDoes();
        final String getDescDoes = myDoes.get(i).getDescDoes();
        final String getDateDoes = myDoes.get(i).getDateDoes();
        final String getKeyDoes = myDoes.get(i).getKeyDoes();

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTaskDesk.class);
            intent.putExtra("titleDoes", getTitleDoes);
            intent.putExtra("descDoes", getDescDoes);
            intent.putExtra("dateDoes", getDateDoes);
            intent.putExtra("keyDoes", getKeyDoes);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleDoes, descDoes, dateDoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleDoes = (TextView) itemView.findViewById(R.id.title_does_item);
            descDoes = (TextView) itemView.findViewById(R.id.desc_does_item);
            dateDoes = (TextView) itemView.findViewById(R.id.date_does_item);
        }
    }
}
