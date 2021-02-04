package com.technocopy.androidonelesson8.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technocopy.androidonelesson8.R;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.MyViewHolder> {

    private String[] dataSource;
    public MyNoteAdapter(String[] dataSource){
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new MyViewHolder(v);
    }

    //передаем данные в созданный ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(dataSource[position]);

    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.tvNote);
            imageView = itemView.findViewById(R.id.imNoteName);
        }

        public void onBind(String s){
            textView.setText(s);
            imageView.setImageResource(R.drawable.daily_notes);
        }
    }
}