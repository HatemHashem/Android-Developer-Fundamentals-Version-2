package com.example.wordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUpdateActivity extends AppCompatActivity {
    public static final String WORD_EXTRA="com.example.wordssample.WORD_EXTRA";
    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);
        editText=findViewById(R.id.editText);
        saveButton=findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=editText.getText().toString().trim();
                if (word.isEmpty()){
                    Toast.makeText(AddUpdateActivity.this,"please enter a word",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent=new Intent();
                    intent.putExtra(WORD_EXTRA,word);
                    setResult(RESULT_OK,intent);
                    finish();
                }


            }
        });
    }
}
