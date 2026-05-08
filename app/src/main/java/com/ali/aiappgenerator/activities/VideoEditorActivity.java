package com.ali.aiappgenerator.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.ali.aiappgenerator.R;
import com.ali.aiappgenerator.utils.VideoProcessor;

public class VideoEditorActivity extends AppCompatActivity {

    private VideoView videoView;
    private SeekBar trimStartSeekBar, trimEndSeekBar, speedSeekBar;
    private Button selectVideoButton, trimButton, mergeButton, addTextButton, saveButton;
    private VideoProcessor videoProcessor;
    private Uri currentVideoUri;
    private static final int PICK_VIDEO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_editor);

        initializeViews();
        setupListeners();
        videoProcessor = new VideoProcessor(this);
    }

    private void initializeViews() {
        videoView = findViewById(R.id.videoView);
        trimStartSeekBar = findViewById(R.id.trimStartSeekBar);
        trimEndSeekBar = findViewById(R.id.trimEndSeekBar);
        speedSeekBar = findViewById(R.id.speedSeekBar);
        selectVideoButton = findViewById(R.id.selectVideoButton);
        trimButton = findViewById(R.id.trimButton);
        mergeButton = findViewById(R.id.mergeButton);
        addTextButton = findViewById(R.id.addTextButton);
        saveButton = findViewById(R.id.saveButton);
    }

    private void setupListeners() {
        selectVideoButton.setOnClickListener(v -> selectVideo());
        trimButton.setOnClickListener(v -> trimVideo());
        mergeButton.setOnClickListener(v -> mergeVideos());
        addTextButton.setOnClickListener(v -> addTextOverlay());
        saveButton.setOnClickListener(v -> saveVideo());
    }

    private void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_VIDEO_REQUEST);
    }

    private void trimVideo() {
        if (currentVideoUri == null) {
            Toast.makeText(this, "پہلے ویڈیو منتخب کریں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "ویڈیو کاٹی جا رہی ہے...", Toast.LENGTH_SHORT).show();
    }

    private void mergeVideos() {
        Toast.makeText(this, "ویڈیوز کو منسلک کیا جا رہا ہے...", Toast.LENGTH_SHORT).show();
    }

    private void addTextOverlay() {
        Toast.makeText(this, "متن شامل کیا جا رہا ہے...", Toast.LENGTH_SHORT).show();
    }

    private void saveVideo() {
        if (currentVideoUri == null) {
            Toast.makeText(this, "پہلے ویڈیو منتخب کریں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "ویڈیو محفوظ ہو رہی ہے...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null) {
            currentVideoUri = data.getData();
            videoView.setVideoURI(currentVideoUri);
        }
    }
}