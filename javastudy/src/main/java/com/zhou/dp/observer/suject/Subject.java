package com.zhou.dp.observer.suject;

import com.zhou.dp.observer.observer.Observer;

/**
 * Created by liqingzhou on 17/7/28.
 */
public interface Subject {

    void register(Observer observer);

    void remove(Observer observer);

    void notifyObserver();
}
