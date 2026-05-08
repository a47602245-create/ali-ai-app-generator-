package com.ali.aiappgenerator.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ali.aiappgenerator.R;
import com.ali.aiappgenerator.api.OpenAIClient;
import com.ali.aiappgenerator.models.ImageRequest;
import com.bumptech.glide.Glide;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextToImageActivity extends AppCompatActivity {

    private EditText promptInput;
    private Spinner qualitySpinner;
    private Button generateButton, downloadButton;
    private ImageView resultImage;
    private ProgressBar progressBar;
    private OpenAIClient openAIClient;
    private ExecutorService executorService;
    private String currentImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_image);

        initializeViews();
        setupListeners();
        openAIClient = new OpenAIClient(this);
        executorService = Executors.newSingleThreadExecutor();
    }

    private void initializeViews() {
        promptInput = findViewById(R.id.promptInput);
        qualitySpinner = findViewById(R.id.qualitySpinner);
        generateButton = findViewById(R.id.generateButton);
        downloadButton = findViewById(R.id.downloadButton);
        resultImage = findViewById(R.id.resultImage);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupListeners() {
        generateButton.setOnClickListener(v -> generateImage());
        downloadButton.setOnClickListener(v -> downloadImage());
    }

    private void generateImage() {
        String prompt = promptInput.getText().toString().trim();
        if (prompt.isEmpty()) {
            Toast.makeText(this, "براہ کرم متن درج کریں", Toast.LENGTH_SHORT).show();
            return;
        }

        String quality = qualitySpinner.getSelectedItem().toString();
        progressBar.setVisibility(android.view.View.VISIBLE);
        generateButton.setEnabled(false);

        executorService.execute(() -> {
            try {
                ImageRequest request = new ImageRequest(prompt, quality);
                String imageUrl = openAIClient.generateImage(request);
                currentImageUrl = imageUrl;

                runOnUiThread(() -> {
                    progressBar.setVisibility(android.view.View.GONE);
                    generateButton.setEnabled(true);
                    Glide.with(TextToImageActivity.this)
                            .load(imageUrl)
                            .into(resultImage);
                    downloadButton.setEnabled(true);
                    Toast.makeText(TextToImageActivity.this, "تصویر کامیابی سے بن گئی! ✅", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(android.view.View.GONE);
                    generateButton.setEnabled(true);
                    Toast.makeText(TextToImageActivity.this, "خرابی: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void downloadImage() {
        if (currentImageUrl == null || currentImageUrl.isEmpty()) {
            Toast.makeText(this, "پہلے تصویر بنائیں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "تصویر ڈاؤن لوڈ ہو رہی ہے...", Toast.LENGTH_SHORT).show();
        // Download logic will be implemented
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}