package com.ali.aiappgenerator.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.ali.aiappgenerator.R;
import com.ali.aiappgenerator.utils.ImageProcessor;
import com.bumptech.glide.Glide;

public class ImageEditorActivity extends AppCompatActivity {

    private ImageView imageView;
    private SeekBar brightnessSeekBar, contrastSeekBar, saturationSeekBar;
    private Button selectImageButton, cropButton, rotateButton, saveButton, filterButton;
    private ImageProcessor imageProcessor;
    private Uri currentImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_editor);

        initializeViews();
        setupListeners();
        imageProcessor = new ImageProcessor(this);
        checkPermissions();
    }

    private void initializeViews() {
        imageView = findViewById(R.id.imageView);
        brightnessSeekBar = findViewById(R.id.brightnessSeekBar);
        contrastSeekBar = findViewById(R.id.contrastSeekBar);
        saturationSeekBar = findViewById(R.id.saturationSeekBar);
        selectImageButton = findViewById(R.id.selectImageButton);
        cropButton = findViewById(R.id.cropButton);
        rotateButton = findViewById(R.id.rotateButton);
        saveButton = findViewById(R.id.saveButton);
        filterButton = findViewById(R.id.filterButton);
    }

    private void setupListeners() {
        selectImageButton.setOnClickListener(v -> selectImage());
        cropButton.setOnClickListener(v -> cropImage());
        rotateButton.setOnClickListener(v -> rotateImage());
        saveButton.setOnClickListener(v -> saveImage());
        filterButton.setOnClickListener(v -> applyFilter());

        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateImage();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void cropImage() {
        if (currentImageUri == null) {
            Toast.makeText(this, "پہلے تصویر منتخب کریں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "تصویر کاٹی جا رہی ہے...", Toast.LENGTH_SHORT).show();
        // Crop implementation
    }

    private void rotateImage() {
        if (currentImageUri == null) {
            Toast.makeText(this, "پہلے تصویر منتخب کریں", Toast.LENGTH_SHORT).show();
            return;
        }
        imageProcessor.rotateImage();
        updateImage();
    }

    private void saveImage() {
        if (currentImageUri == null) {
            Toast.makeText(this, "پہلے تصویر منتخب کریں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "تصویر محفوظ ہو رہی ہے...", Toast.LENGTH_SHORT).show();
        // Save implementation
    }

    private void applyFilter() {
        if (currentImageUri == null) {
            Toast.makeText(this, "پہلے تصویر منتخب کریں", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "فلٹر لگایا جا رہا ہے...", Toast.LENGTH_SHORT).show();
        // Filter implementation
    }

    private void updateImage() {
        if (currentImageUri != null) {
            Glide.with(this).load(currentImageUri).into(imageView);
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            currentImageUri = data.getData();
            Glide.with(this).load(currentImageUri).into(imageView);
        }
    }
}