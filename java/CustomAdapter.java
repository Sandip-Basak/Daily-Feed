package com.example.dailyfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyfeed.Models.NewsHeadLines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomVievHolder> {
    Context context;
    List<NewsHeadLines> headLines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadLines> headLines, SelectListener listener) {
        this.context = context;
        this.headLines = headLines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomVievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomVievHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVievHolder holder, int position) {
        holder.text_title.setText(headLines.get(position).getTitle());
        holder.text_source.setText((headLines.get(position).getSource().getName()));
        if(headLines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headLines.get(position).getUrlToImage()).into(holder.img_Headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headLines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headLines.size();
    }
}
