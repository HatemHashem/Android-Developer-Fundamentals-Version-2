package com.example.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView wordItemView;
    final WordListAdapter adapter;

    public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
        super(itemView);
        wordItemView=itemView.findViewById(R.id.word);
        itemView.setOnClickListener(this);
        this.adapter = adapter;
    }

    @Override
    public void onClick(View view) {
        int position=getLayoutPosition();
        String element=adapter.getWordList().get(position);
        adapter.getWordList().set(position,"Clicked! "+element);
        adapter.notifyDataSetChanged();


    }
}
