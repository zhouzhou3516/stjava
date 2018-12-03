package com.zhou.dp.observer;

/**
 * 天气 bean
 * Created by liqingzhou on 17/7/28.
 */
public class Weather {

    private float temperature = 0.0f;//温度
    private float humidity = 0.0f;//湿度
    private float pressure = 0.0f;//气压

    public Weather() {
    }

    public Weather(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
