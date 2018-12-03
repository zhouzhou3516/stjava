package com.zhou.dp.observer.observer;

import com.zhou.dp.observer.Weather;
import com.zhou.dp.observer.suject.Subject;

/**
 * x
 * Created by liqingzhou on 17/7/28.
 */
public class WeatherObserver implements Observer, Display {

    private String name = "";
    private Weather weatherToDisplay = new Weather();
    private Subject weatherSubject;

    public WeatherObserver(String name) {
        this.name = name;
    }


    @Override
    public void update(Weather weather) {
        weatherToDisplay.setTemperature(weather.getTemperature());
        weatherToDisplay.setHumidity(weather.getHumidity());
        weatherToDisplay.setPressure(weather.getPressure());
        display();

    }


    @Override
    public void subscribe(Subject subject) {
        weatherSubject = subject;
        weatherSubject.register(this);
    }

    @Override
    public void unSubscribe() {
        if (weatherSubject != null) {
            weatherSubject.remove(this);
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    @Override
    public void display() {
        System.out.println(name + ",您好，有天气更新:" +
                "温度:" + weatherToDisplay.getTemperature() + "度，湿度:" + weatherToDisplay.getHumidity()
                + ", 气压:" + weatherToDisplay.getPressure() + "帕");
    }

}
