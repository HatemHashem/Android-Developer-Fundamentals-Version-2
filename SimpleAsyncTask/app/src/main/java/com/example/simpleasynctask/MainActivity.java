package com.example.simpleasynctask;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView sleepTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sleepTextView = findViewById(R.id.sleep_textview);
        progressBar = findViewById(R.id.progressBar);
    }

    public void startTask(View view) {
        sleepTextView.setText(R.string.napping);

        new SimpleAsyncTask(sleepTextView, progressBar).execute();

    }
}
