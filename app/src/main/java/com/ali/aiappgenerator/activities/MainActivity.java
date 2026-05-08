package com.ali.aiappgenerator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.ali.aiappgenerator.R;

public class MainActivity extends AppCompatActivity {

    private CardView textToImageCard, textToVideoCard, imageEditorCard, videoEditorCard, galleryCard, settingsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        textToImageCard = findViewById(R.id.textToImageCard);
        textToVideoCard = findViewById(R.id.textToVideoCard);
        imageEditorCard = findViewById(R.id.imageEditorCard);
        videoEditorCard = findViewById(R.id.videoEditorCard);
        galleryCard = findViewById(R.id.galleryCard);
        settingsCard = findViewById(R.id.settingsCard);
    }

    private void setupClickListeners() {
        textToImageCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TextToImageActivity.class)));
        textToVideoCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TextToVideoActivity.class)));
        imageEditorCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ImageEditorActivity.class)));
        videoEditorCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, VideoEditorActivity.class)));
        galleryCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GalleryActivity.class)));
        settingsCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
}