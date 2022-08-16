package com.example.openweather;

import com.google.gson.annotations.SerializedName;

public class Dados {
    private Main main;
    private Rain rain;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }
}
