package com.example.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import com.example.whowroteit.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class FetchBook extends AsyncTask<String,Void,String> {
    private WeakReference<TextView> mAuthorName;
    private WeakReference<TextView> mBookTitle;

    public FetchBook(TextView authorName, TextView bookTitle) {
        this.mAuthorName = new WeakReference<>(authorName);
        this.mBookTitle = new WeakReference<>(bookTitle);
    }

    @Override
    protected String doInBackground(String... strings) {

        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {

            String authorName=null;
            String bookName=null;
            JSONObject jsonObject=new JSONObject(s);
            JSONArray arrayItem=jsonObject.getJSONArray("items");
            int i=0;
         while ( i<arrayItem.length()&&
                 (authorName==null&&bookName==null)){
            JSONObject book= arrayItem.getJSONObject(i);
             JSONObject volumeInfo=book.getJSONObject("volumeInfo");
             bookName=volumeInfo.getString("title");
             authorName = volumeInfo.getString("authors");
             i++;

         }
         if(authorName!=null&&bookName!=null){
             mAuthorName.get().setText(authorName);
             mBookTitle.get().setText(bookName);
         }else {
             mAuthorName.get().setText("error");
             mBookTitle.get().setText("error");

         }

        }catch (Exception e){
            mAuthorName.get().setText("error");
            mBookTitle.get().setText("error");

        }
    }
}
