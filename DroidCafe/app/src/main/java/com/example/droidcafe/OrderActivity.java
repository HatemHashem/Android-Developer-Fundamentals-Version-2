package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.droidcafe.MainActivity.EXTRA_MESSAGE;

public class OrderActivity extends AppCompatActivity {
    private TextView receivedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        receivedTextView=findViewById(R.id.received_message_textView);
        Intent intent=getIntent();
        if(intent.getStringExtra(EXTRA_MESSAGE)!=null){
            receivedTextView.setText(intent.getStringExtra(EXTRA_MESSAGE));
        }else {
            receivedTextView.setText(R.string.no_order);
        }
    }
}
