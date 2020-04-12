package com.example.whowroteit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mAuthorName;
    private WeakReference<TextView> mBookTitle;
    private WeakReference<ImageView> mThumbnail;
    private WeakReference<Context> context;
    private static final String TAG = "FetchBook";

    public FetchBook(Context context, TextView authorName, TextView bookTitle, ImageView thumbnail) {
        this.mAuthorName = new WeakReference<>(authorName);
        this.mBookTitle = new WeakReference<>(bookTitle);
        this.mThumbnail = new WeakReference<>(thumbnail);
        this.context = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(String... strings) {

        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {

            String authorName = null;
            String bookName = null;
            JSONObject jsonObject = new JSONObject(s);
            JSONArray arrayItem = jsonObject.getJSONArray("items");
            int i = 0;
            while (i < arrayItem.length() &&
                    (authorName == null && bookName == null)) {
                JSONObject book = arrayItem.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String thumbnail = imageLinks.getString("thumbnail");
                Log.d(TAG, "onPostExecute: "+thumbnail);
                GlideApp.with(context.get()).load(thumbnail).into(mThumbnail.get());
                bookName = volumeInfo.getString("title");
                authorName = volumeInfo.getString("authors");
                i++;

            }
            if (authorName != null && bookName != null) {
                mAuthorName.get().setText(authorName);
                mBookTitle.get().setText(bookName);
            } else {
                mAuthorName.get().setText("error");
                mBookTitle.get().setText("error");

            }

        } catch (Exception e) {
            mAuthorName.get().setText("error");
            mBookTitle.get().setText("error");

        }
    }
}
