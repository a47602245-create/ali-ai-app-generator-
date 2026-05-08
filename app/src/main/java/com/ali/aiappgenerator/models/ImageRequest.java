package com.ali.aiappgenerator.models;

public class ImageRequest {
    private String prompt;
    private String quality;
    private String size;

    public ImageRequest(String prompt, String quality) {
        this.prompt = prompt;
        this.quality = quality;
        this.size = "1024x1024";
    }

    public String getPrompt() { return prompt; }
    public String getQuality() { return quality; }
    public String getSize() { return size; }

    public void setPrompt(String prompt) { this.prompt = prompt; }
    public void setQuality(String quality) { this.quality = quality; }
    public void setSize(String size) { this.size = size; }
}