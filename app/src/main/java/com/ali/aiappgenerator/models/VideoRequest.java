package com.ali.aiappgenerator.models;

public class VideoRequest {
    private String prompt;
    private String quality;
    private int duration;

    public VideoRequest(String prompt, String quality, int duration) {
        this.prompt = prompt;
        this.quality = quality;
        this.duration = duration;
    }

    public String getPrompt() { return prompt; }
    public String getQuality() { return quality; }
    public int getDuration() { return duration; }

    public void setPrompt(String prompt) { this.prompt = prompt; }
    public void setQuality(String quality) { this.quality = quality; }
    public void setDuration(int duration) { this.duration = duration; }
}