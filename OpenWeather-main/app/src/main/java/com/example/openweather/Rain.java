package com.example.openweather;

import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("1h")
    private float h;

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

}
