package com.picsarttraining.b;

import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView imagesGridView;
    private ArrayList<Uri> imageUris;
    private ImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagesGridView = (GridView) findViewById(R.id.images_grid_view);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent);
            }
        } else {
            Toast.makeText(MainActivity.this, "Nothing to Show",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void handleSendMultipleImages(Intent intent) {
        imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris == null) {
            Toast.makeText(MainActivity.this, "Nothing to Show",
                    Toast.LENGTH_LONG).show();
            return;
        }
        imagesAdapter = new ImagesAdapter(this);
        imagesGridView.setAdapter(imagesAdapter);
        imagesAdapter.setImages(imageUris);
    }

}
