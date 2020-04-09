package com.example.implicitintents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MainActivity extends AppCompatActivity {
    private EditText websiteEditText;
    private EditText locationEditText;
    private EditText shareEditText;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int REQ_CODE = 1;
    private ImageView capturedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        websiteEditText = findViewById(R.id.website_edittext);
        locationEditText = findViewById(R.id.location_edittext);
        shareEditText = findViewById(R.id.share_text_edittext);
        capturedImageView = findViewById(R.id.captured_imageview);
    }

    public void openWebsite(View view) {
        String uri = websiteEditText.getText().toString();
        Uri webpage = Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "openWebsite: Can't handle this intent!");
        }
    }

    public void openLocation(View view) {
        String uri = locationEditText.getText().toString();
        Uri location = Uri.parse("geo:0,0?q=" + uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, location);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "openLocation: ");
        }
    }

    public void shareText(View view) {
        String text = shareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("share this text with: ")
                .setText(text)
                .startChooser();
    }

    public void captureImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            capturedImageView.setImageBitmap(photo);


        }
    }
}
