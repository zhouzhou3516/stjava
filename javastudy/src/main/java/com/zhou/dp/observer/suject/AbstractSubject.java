package com.zhou.dp.observer.suject;

import com.google.common.collect.Lists;
import com.zhou.dp.observer.observer.Observer;
import java.util.List;

/**
 * Subject 抽象类，规范了subject的实现
 * Created by liqingzhou on 17/7/28.
 */
public abstract class AbstractSubject implements Subject {

    List<Observer> observerList = Lists.newArrayList();

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        int index = observerList.indexOf(observer);
        if (index > 0) {
            observerList.remove(observer);
            System.out.println(observer.getName() + "取消关注天气");
        }
    }

    @Override
    public void notifyObserver() {
        System.out.println("推送天气信息");
        for (Observer observer : observerList) {
            updateObserver(observer);
        }
        System.out.println();
    }

    /**
     * 子类只需实现updateObserver即可
     */
    protected abstract void updateObserver(Observer observer);

}
