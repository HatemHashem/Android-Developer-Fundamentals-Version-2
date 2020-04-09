package com.example.twoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Intent intent;
    private TextView messageTextView;
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivity.extra.REPLY";
    private EditText reply;
    private static final String LOG_TAG=SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        messageTextView = findViewById(R.id.text_message_second);
        messageTextView.setText(message);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }

    public void returnReply(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        reply = findViewById(R.id.message_second);
        String replyMessage = reply.getText().toString();
        intent.putExtra(EXTRA_REPLY, replyMessage);
        setResult(RESULT_OK, intent);
        Log.d(LOG_TAG, "End SecondActivity");
        finish();

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }
}
