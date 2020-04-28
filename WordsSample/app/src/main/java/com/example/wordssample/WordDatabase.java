package com.example.wordssample;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase instance;

    public abstract WordDao wordDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterSERVICE = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriterSERVICE.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = instance.wordDao();
                dao.deleteAllWords();
                System.out.println("test here");

                Word word =new Word("text");
                dao.insert(word);
            });
        }

    };

    public static synchronized WordDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, WordDatabase.class, "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }

}
