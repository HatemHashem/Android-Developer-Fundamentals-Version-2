package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private static final int CHUNK_SIZE = 5;

    public SimpleAsyncTask(TextView tv, ProgressBar pb) {
        this.mTextView = new WeakReference<>(tv);
        this.mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int s = random.nextInt(11) * 200;
        int chunkSize = s / CHUNK_SIZE;
        for (int i = 0; i < CHUNK_SIZE; i++) {
            try {
                Thread.sleep(chunkSize);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100) / CHUNK_SIZE);
        }

        return "Awake after: " + s + "MS!";
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
    }
}
