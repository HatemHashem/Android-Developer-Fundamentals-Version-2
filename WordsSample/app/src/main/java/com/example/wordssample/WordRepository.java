package com.example.wordssample;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordDatabase database=WordDatabase.getDatabase(application);
        wordDao=database.wordDao();
        allWords=wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }
    public void insert( Word word){
        WordDatabase.databaseWriterSERVICE.execute(() -> {
            wordDao.insert(word);
        });
    }
    public void update(Word word){
        WordDatabase.databaseWriterSERVICE.execute(() -> {
            wordDao.update(word);
        });
    }
    public void delete(Word word){
        WordDatabase.databaseWriterSERVICE.execute(() -> {
            wordDao.delete(word);
        });
    }
    public void deleteAll(){
        WordDatabase.databaseWriterSERVICE.execute(() -> {
            wordDao.deleteAllWords();
        });
    }
}
