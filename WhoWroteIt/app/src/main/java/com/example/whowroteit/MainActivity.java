package com.example.whowroteit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //ui component
    private EditText mBookInput;
    private TextView mBookTitle;
    private TextView mBookAuthor;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.bookinput_edittext);
        mBookTitle=findViewById(R.id.book_title_textview);
        mBookAuthor=findViewById(R.id.author_textview);
    }

    public void searchBooks(View view) {
        String queryString=mBookInput.getText().toString();
        Log.d(TAG, "searchBooks: "+queryString);
        new FetchBook(mBookAuthor,mBookTitle).execute(queryString);
    }
}
