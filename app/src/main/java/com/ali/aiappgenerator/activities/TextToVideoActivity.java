package com.ali.aiappgenerator.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.ali.aiappgenerator.R;
import com.ali.aiappgenerator.api.RunwayMLClient;
import com.ali.aiappgenerator.models.VideoRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextToVideoActivity extends AppCompatActivity {

    private EditText promptInput;
    private Spinner qualitySpinner, durationSpinner;
    private Button generateButton, downloadButton;
    private VideoView resultVideo;
    private ProgressBar progressBar;
    private RunwayMLClient runwayMLClient;
    private ExecutorService executorService;
    private String currentVideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_video);

        initializeViews();
        setupListeners();
        runwayMLClient = new RunwayMLClient(this);
        executorService = Executors.newSingleThreadExecutor();
    }

    private void initializeViews() {
        promptInput = findViewById(R.id.promptInput);
        qualitySpinner = findViewById(R.id.qualitySpinner);
        durationSpinner = findViewById(R.id.durationSpinner);
        generateButton = findViewById(R.id.generateButton);
        downloadButton = findViewById(R.id.downloadButton);
        resultVideo = findViewById(R.id.resultVideo);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupListeners() {
        generateButton.setOnClickListener(v -> generateVideo());
        downloadButton.setOnClickListener(v -> downloadVideo());
    }

    private void generateVideo() {
        String prompt = promptInput.getText().toString().trim();
        if (prompt.isEmpty()) {
            Toast.makeText(this, "براہ کرم متن درج کریں", Toast.LENGTH_SHORT).show();
            return;
        }

        String quality = qualitySpinner.getSelectedItem().toString();
        int duration = Integer.parseInt(durationSpinner.getSelectedItem().toString());
        
        progressBar.setVisibility(android.view.View.VISIBLE);
        generateButton.setEnabled(false);
        Toast.makeText(this, "ویڈیو بنائی جا رہی ہے... یہ کچھ وقت لے سکتا ہے ⏳", Toast.LENGTH_SHORT).show();

        executorService.execute(() -> {
            try {
                VideoRequest request = new VideoRequest(prompt, quality, duration);
                String videoUrl = runwayMLClient.generateVideo(request);
                currentVideoUrl = videoUrl;

                runOnUiThread(() -> {
                    progressBar.setVisibility(android.view.View.GONE);
                    generateButton.setEnabled(true);
                    resultVideo.setVideoPath(videoUrl);
                    resultVideo.start();
                    downloadButton.setEnabled(true);
                    Toast.makeText(TextToVideoActivity.this, "ویڈیو کامیابی سے بن گئی! 🎬", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(android.view.View.GONE);
                    generateButton.setEnabled(true);
                    Toast.makeText(TextToVideoActivity.this, "خرابی: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void downloadVideo() {
        if (currentVideoUrl == null || currentVideoUrl.isEmpty()) {
            Toast.makeText(this, "پہلے ویڈیو بنائیں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "ویڈیو ڈاؤن لوڈ ہو رہی ہے...", Toast.LENGTH_SHORT).show();
        // Download logic will be implemented
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}