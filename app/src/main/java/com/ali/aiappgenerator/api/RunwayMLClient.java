package com.ali.aiappgenerator.api;

import android.content.Context;
import com.ali.aiappgenerator.models.VideoRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class RunwayMLClient {
    private final String API_KEY = "your-runwayml-api-key";
    private final String API_URL = "https://api.runwayml.com/v1/video/generations";
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();
    private Context context;

    public RunwayMLClient(Context context) {
        this.context = context;
    }

    public String generateVideo(VideoRequest request) throws IOException {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("prompt", request.getPrompt());
        jsonBody.addProperty("duration", request.getDuration());
        jsonBody.addProperty("quality", request.getQuality());
        jsonBody.addProperty("format", "mp4");

        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json"));

        Request req = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(req).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API Error: " + response.code());
            }
            String responseBody = response.body().string();
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
            return jsonResponse.get("video_url").getAsString();
        }
    }
}