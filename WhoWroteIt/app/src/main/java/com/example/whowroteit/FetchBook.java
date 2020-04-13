package com.example.whowroteit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mAuthorName;
    private WeakReference<TextView> mBookTitle;
    private WeakReference<ImageView> mThumbnail;
    private WeakReference<Context> context;
    private WeakReference<RatingBar> mRating;
    private WeakReference<TextView> mdescription;
    private WeakReference<TextView> mPublishedDate;
    private static final String TAG = "FetchBook";


    public FetchBook(Context context, TextView authorName, TextView bookTitle, ImageView thumbnail,
                     RatingBar rating, TextView publishedDate,TextView description) {
        this.mAuthorName = new WeakReference<>(authorName);
        this.mBookTitle = new WeakReference<>(bookTitle);
        this.mThumbnail = new WeakReference<>(thumbnail);
        this.context = new WeakReference<>(context);
        this.mRating = new WeakReference<>(rating);
        this.mPublishedDate = new WeakReference<>(publishedDate);
        this.mdescription=new WeakReference<>(description);
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
            String publishedDate = null;
            String description = null;
            double averageRating = 0;
            JSONObject jsonObject = new JSONObject(s);
            JSONArray arrayItem = jsonObject.getJSONArray("items");
            int i = 0;
            while (i < arrayItem.length() &&
                    (authorName == null && bookName == null)) {
                JSONObject book = arrayItem.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String thumbnail = imageLinks.getString("thumbnail");
                Log.d(TAG, "onPostExecute: " + thumbnail);
                GlideApp.with(context.get()).load(thumbnail).into(mThumbnail.get());
                bookName = volumeInfo.getString("title");
                authorName = volumeInfo.getString("authors");
                publishedDate = volumeInfo.getString("publishedDate");
                averageRating = volumeInfo.getDouble("averageRating");
                description = volumeInfo.getString("description");
                System.out.println(publishedDate + "test" + averageRating);
                i++;

            }
            if (authorName != null && bookName != null) {
                mAuthorName.get().setText(authorName);
                mBookTitle.get().setText(bookName);
                mPublishedDate.get().setText(publishedDate);
                mRating.get().setRating((float) averageRating);
                mdescription.get().setText(description);
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
