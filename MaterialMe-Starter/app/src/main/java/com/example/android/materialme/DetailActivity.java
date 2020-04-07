package com.example.android.materialme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
   private Intent intent;
   private TextView title;
   ImageView resourceImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent=getIntent();
        title=findViewById(R.id.title_details);
        resourceImage=findViewById(R.id.sports_image_details);
        title.setText(intent.getStringExtra("title_text"));
        Glide.with(this).load(intent.getIntExtra("image_resource",0)).into(resourceImage);
    }
}
