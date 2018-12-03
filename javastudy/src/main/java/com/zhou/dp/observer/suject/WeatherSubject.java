package com.zhou.dp.observer.suject;

import com.zhou.dp.observer.Weather;
import com.zhou.dp.observer.observer.Observer;

/**
 * 天气 subject
 * Created by liqingzhou on 17/7/28.
 */
public class WeatherSubject extends AbstractSubject {

    public Weather weather;

    public WeatherSubject(Weather weather) {
        this.weather = weather;
    }

    @Override
    public void updateObserver(Observer observer) {
        observer.update(weather);
    }

    public void setNewWeather(Weather weather) {
        this.weather = weather;
        notifyObserver();
    }


}
