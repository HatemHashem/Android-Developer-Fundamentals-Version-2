package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int score1;
    private int score2;
    private TextView scoreText1;
    private TextView scoreText2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.light_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText1 = findViewById(R.id.score_1_textview);
        scoreText2 = findViewById(R.id.score_2_textview);
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt("score1_value");
            score2 = savedInstanceState.getInt("score2_value");
            scoreText1.setText(String.valueOf(score1));
            scoreText2.setText(String.valueOf(score2));
        }

    }


    public void decreaseScore(View view) {
        switch (view.getId()) {
            case R.id.decrease_t1_button:
                if (score1 - 1 >= 0) {
                    score1--;
                }
                scoreText1.setText(String.valueOf(score1));
                break;
            case R.id.decrease_t2_button:
                if (score2 - 1 >= 0) {
                    score2--;
                }
                scoreText2.setText(String.valueOf(score2));
                break;

        }
    }

    public void increaseScore(View view) {
        switch (view.getId()) {
            case R.id.increase_t1_button:
                score1++;
                scoreText1.setText(String.valueOf(score1));
                break;
            case R.id.increase_t2_button:
                score2++;
                scoreText2.setText(String.valueOf(score2));
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score1_value", score1);
        outState.putInt("score2_value", score2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }

        }
        recreate();

        return super.onOptionsItemSelected(item);
    }
}
