package com.ali.aiappgenerator.api;

import android.content.Context;
import com.ali.aiappgenerator.models.ImageRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class OpenAIClient {
    private final String API_KEY = "your-openai-api-key";
    private final String API_URL = "https://api.openai.com/v1/images/generations";
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();
    private Context context;

    public OpenAIClient(Context context) {
        this.context = context;
    }

    public String generateImage(ImageRequest request) throws IOException {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("model", "dall-e-3");
        jsonBody.addProperty("prompt", request.getPrompt());
        jsonBody.addProperty("n", 1);
        jsonBody.addProperty("size", "1024x1024");
        jsonBody.addProperty("quality", request.getQuality());

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
            return jsonResponse.getAsJsonArray("data").get(0).getAsJsonObject().get("url").getAsString();
        }
    }
}