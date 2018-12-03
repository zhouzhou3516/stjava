package com.zhou.dp.observer;

import com.zhou.dp.observer.observer.Observer;
import com.zhou.dp.observer.observer.WeatherObserver;
import com.zhou.dp.observer.suject.WeatherSubject;

/**
 * Created by liqingzhou on 17/7/28.
 */
public class Client {

    public static void main(String[] args) {

        WeatherSubject weatherSubject = new WeatherSubject(new Weather());
        //李枭雄和李庆洲预定了天气信息
        Observer lxx = new WeatherObserver("李枭雄");
        Observer lqz = new WeatherObserver("李庆州");
        lxx.subscribe(weatherSubject);
        lqz.subscribe(weatherSubject);
        //如果天气变化，天气信息自动推送给预定订者
        Weather newWeather = new Weather(25.0f, 0.8f, 120.0f);
        weatherSubject.setNewWeather(newWeather);

        //李庆洲取消关注
        lqz.unSubscribe();
        //重新推送天气
        weatherSubject.notifyObserver();

    }
}
