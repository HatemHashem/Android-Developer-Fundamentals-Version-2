package com.example.hellocompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private TextView helloTextView;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private String[] colors = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTextView = findViewById(R.id.hello_textview);
        if (savedInstanceState != null) {
            helloTextView.setTextColor(savedInstanceState.getInt("color"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", helloTextView.getCurrentTextColor());
    }

    public void changeColor(View view) {
        Random random = new Random();
        String color = colors[random.nextInt(colors.length)];
        int colorResourceName = getResources().getIdentifier(color,
                "color", getApplicationContext().getPackageName());
        int colorRes = ContextCompat.getColor(this, colorResourceName);
        helloTextView.setTextColor(colorRes);
        Log.d(LOG_TAG, "changeColor: " + color);

    }
}
