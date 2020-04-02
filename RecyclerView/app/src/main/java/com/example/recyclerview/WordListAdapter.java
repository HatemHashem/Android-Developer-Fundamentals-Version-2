package com.example.recyclerview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerview.WordViewHolder;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private final LinkedList<String> wordList;
    private LayoutInflater layoutInflater;

    public LinkedList<String> getWordList() {
        return wordList;
    }

    public WordListAdapter(LinkedList<String> wordList, Context context) {
        this.layoutInflater=LayoutInflater.from(context);
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=layoutInflater.inflate(R.layout.wordlist_item,parent,false);
        return new WordViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String currentWord=wordList.get(position);
        holder.wordItemView.setText(currentWord);

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
