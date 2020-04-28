package com.example.wordssample;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel  {
    private WordRepository repository;
    private LiveData<List<Word>> allWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        repository=new WordRepository(application);
        allWords=repository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }
    public void insert(Word word) {
        repository.insert(word);
    }

    public void update(Word word) {
        repository.update(word);
    }

    public void delete(Word word) {
        repository.delete(word);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
