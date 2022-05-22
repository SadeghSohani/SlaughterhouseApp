package com.example.slaughterhouseapp;

import com.google.gson.annotations.SerializedName;

public class RequestResponse {
    @SerializedName("message")
    private String message;

    public RequestResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
