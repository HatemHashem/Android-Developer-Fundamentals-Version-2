package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView showCount;
    private Counter counter = new Counter();
    private Button countButton;
    private Button toastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "hello world ");
        countButton = findViewById(R.id.button_count);
        toastButton = findViewById(R.id.button_toast);
        showCount=findViewById(R.id.show_count);
        toastButton.setOnClickListener(this);
        countButton.setOnClickListener(this);
        if(savedInstanceState!=null){
            showCount.setText(savedInstanceState.getString("counter_value"));
            counter.setCount(savedInstanceState.getInt("counter_val"));
        }


    }

    @Override
    public void onClick(View view) {
        System.out.println("test");
        if (view.getId() == R.id.button_toast) {

            Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.button_count) {
            showCount = findViewById(R.id.show_count);
            counter.increment();
            showCount.setText(String.valueOf(counter.getCount()));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("counter_value",showCount.getText().toString());
       outState.putInt("counter_val",counter.getCount());

    }
}
