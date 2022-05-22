package com.example.slaughterhouseapp;

import com.google.gson.annotations.SerializedName;

public class RequestBody {
    @SerializedName("chickenId")
    private String chickenId;
    @SerializedName("chickenFarm")
    private String chickenFarm;
    @SerializedName("feedingCompany")
    private String feedingCompany;
    @SerializedName("motherCompany")
    private String motherCompany;
    @SerializedName("slaughterHouse")
    private String slaughterHouse;
    @SerializedName("owner")
    private String owner;

    public RequestBody(
            String chickenId,
            String chickenFarm,
            String feedingCompany,
            String motherCompany,
            String slaughterHouse,
            String owner
    ) {
        this.chickenId = chickenId;
        this.chickenFarm = chickenFarm;
        this.motherCompany = motherCompany;
        this.feedingCompany = feedingCompany;
        this.slaughterHouse = slaughterHouse;
        this.owner = owner;
    }

}
