package com.example.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //ui component
    private EditText mBookInput;
    private TextView mBookTitle;
    private TextView mBookAuthor;
    private ImageView thumbnailImage;
    private RatingBar ratingBar;
    private TextView publishedDate;
    private TextView description;
    //log
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.bookinput_edittext);
        mBookTitle = findViewById(R.id.book_title_textview);
        mBookAuthor = findViewById(R.id.author_textview);
        thumbnailImage = findViewById(R.id.thumbnail_imageview);
        ratingBar=findViewById(R.id.rate);
        publishedDate=findViewById(R.id.published_date_textview);
        description=findViewById(R.id.description_textview);
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();
        Log.d(TAG, "searchBooks: " + queryString);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        InputMethodManager inputManger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManger != null) {
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            new FetchBook(getApplicationContext(), mBookAuthor, mBookTitle, thumbnailImage,ratingBar,publishedDate,description).execute(queryString);
            mBookAuthor.setText("");
            mBookTitle.setText(R.string.loading);
            ratingBar.setVisibility(View.VISIBLE);
            publishedDate.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
            thumbnailImage.setVisibility(View.VISIBLE);

        } else {
            thumbnailImage.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            publishedDate.setVisibility(View.GONE);
            description.setVisibility(View.GONE);

            if (queryString.length() == 0) {
                mBookAuthor.setText("");
                mBookTitle.setText(R.string.book_not_found);
            } else {
                mBookAuthor.setText("");
                mBookTitle.setText(R.string.no_internet);

            }
        }

    }
}
