package com.ali.aiappgenerator.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ali.aiappgenerator.R;
import com.ali.aiappgenerator.adapters.MediaAdapter;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView mediaRecyclerView;
    private SearchView searchView;
    private Button deleteButton, shareButton, downloadButton;
    private MediaAdapter mediaAdapter;
    private List<String> mediaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initializeViews();
        setupRecyclerView();
        setupSearchView();
        setupButtons();
        loadMedia();
    }

    private void initializeViews() {
        mediaRecyclerView = findViewById(R.id.mediaRecyclerView);
        searchView = findViewById(R.id.searchView);
        deleteButton = findViewById(R.id.deleteButton);
        shareButton = findViewById(R.id.shareButton);
        downloadButton = findViewById(R.id.downloadButton);
    }

    private void setupRecyclerView() {
        mediaList = new ArrayList<>();
        mediaAdapter = new MediaAdapter(mediaList, this);
        mediaRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mediaRecyclerView.setAdapter(mediaAdapter);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMedia(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setupButtons() {
        deleteButton.setOnClickListener(v -> deleteSelected());
        shareButton.setOnClickListener(v -> shareSelected());
        downloadButton.setOnClickListener(v -> downloadSelected());
    }

    private void loadMedia() {
        // Load media from Firebase or local storage
        // mediaList.add(...);
        // mediaAdapter.notifyDataSetChanged();
    }

    private void searchMedia(String query) {
        // Implement search logic
    }

    private void deleteSelected() {
        // Implement delete logic
    }

    private void shareSelected() {
        // Implement share logic
    }

    private void downloadSelected() {
        // Implement download logic
    }
}