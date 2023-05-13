package com.android.networkbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    private ArrayList<ThemeData> items = new ArrayList<>();
    private Context context;
    private int type=0;

    public ThemeAdapter(ArrayList<ThemeData> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public ThemeAdapter(ArrayList<ThemeData> items, Context context, int type) {
        this.items = items;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.theme_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThemeData item = items.get(holder.getAbsoluteAdapterPosition());
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getShort_desc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type==1){
                    Intent intent=new Intent(context, QuizPage.class);
                    intent.putExtra("pos",holder.getAbsoluteAdapterPosition()+1);
                    intent.putExtra("name",item.getTitle());
                    context.startActivity(intent);
                } else {
                    ReadBook.themeData=item;
                    Intent intent=new Intent(context, ReadBook.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc=itemView.findViewById(R.id.desc);
            title=itemView.findViewById(R.id.title);
        }
    }
}
