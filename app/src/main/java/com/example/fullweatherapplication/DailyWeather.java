package com.example.fullweatherapplication;

public class DailyWeather{

    private String temp;
    private String weather;
    private String city;

    private DailyWeather(String temp, String weather, String city) {
        this.temp = temp;
        this.weather = weather;
        this.city = city;
    }

    public String getCity() {
        return temp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
