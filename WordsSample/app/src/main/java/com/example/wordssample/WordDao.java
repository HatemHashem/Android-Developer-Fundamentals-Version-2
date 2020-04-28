package com.example.wordssample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WordDao {
    @Update
    void update(Word word);

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("SELECT * FROM word_table ORDER BY word Asc")
    LiveData<List<Word>> getAllWords();

    @Query("DELETE FROM word_table")
    void deleteAllWords();
}
