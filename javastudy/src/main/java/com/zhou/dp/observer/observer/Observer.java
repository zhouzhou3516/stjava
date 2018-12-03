package com.zhou.dp.observer.observer;

import com.zhou.dp.observer.Weather;
import com.zhou.dp.observer.suject.Subject;

/**
 * Created by liqingzhou on 17/7/28.
 */
public interface Observer {

    /**
     * 更新天气
     */
    void update(Weather weather);

    /**
     * 关注天气
     */
    void subscribe(Subject subject);
    /**
     * 取消关注天气
     */
    void unSubscribe();

    /**
     * 观察者名字
     */
    String getName();
}
