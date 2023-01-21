package com.mihainour.finalproject.api;

import com.google.gson.annotations.SerializedName;

public class Weather {

    private int id;

    @SerializedName("main")
    private String info;

    private String description;

    private String icon;

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
