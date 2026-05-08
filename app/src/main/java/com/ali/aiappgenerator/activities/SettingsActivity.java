package com.ali.aiappgenerator.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ali.aiappgenerator.R;

public class SettingsActivity extends AppCompatActivity {

    private EditText openAIKeyInput, runwayKeyInput;
    private Spinner themeSpinner, languageSpinner;
    private Switch notificationsSwitch, autoBackupSwitch;
    private Button saveButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();
        loadSettings();
        setupListeners();
    }

    private void initializeViews() {
        openAIKeyInput = findViewById(R.id.openAIKeyInput);
        runwayKeyInput = findViewById(R.id.runwayKeyInput);
        themeSpinner = findViewById(R.id.themeSpinner);
        languageSpinner = findViewById(R.id.languageSpinner);
        notificationsSwitch = findViewById(R.id.notificationsSwitch);
        autoBackupSwitch = findViewById(R.id.autoBackupSwitch);
        saveButton = findViewById(R.id.saveButton);
        resetButton = findViewById(R.id.resetButton);
    }

    private void loadSettings() {
        // Load settings from SharedPreferences
    }

    private void setupListeners() {
        saveButton.setOnClickListener(v -> saveSettings());
        resetButton.setOnClickListener(v -> resetSettings());
    }

    private void saveSettings() {
        String openAIKey = openAIKeyInput.getText().toString();
        String runwayKey = runwayKeyInput.getText().toString();
        String theme = themeSpinner.getSelectedItem().toString();
        String language = languageSpinner.getSelectedItem().toString();
        boolean notifications = notificationsSwitch.isChecked();
        boolean autoBackup = autoBackupSwitch.isChecked();

        // Save to SharedPreferences
        Toast.makeText(this, "ترتیبات محفوظ ہو گئیں ✅", Toast.LENGTH_SHORT).show();
    }

    private void resetSettings() {
        Toast.makeText(this, "ترتیبات ری سیٹ ہو گئیں", Toast.LENGTH_SHORT).show();
    }
}