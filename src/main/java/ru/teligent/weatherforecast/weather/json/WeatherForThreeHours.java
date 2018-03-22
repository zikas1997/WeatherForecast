package ru.teligent.weatherforecast.weather.json;

import java.util.Date;

public class WeatherForThreeHours {
    
    private Date date;
    private double temperature;
    private double minTemperature;

    public WeatherForThreeHours(Date date, double temperature, double minTemperature) {
        this.date = date;
        this.temperature = temperature;
        this.minTemperature = minTemperature;
    }

    public WeatherForThreeHours(Date date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }
}
