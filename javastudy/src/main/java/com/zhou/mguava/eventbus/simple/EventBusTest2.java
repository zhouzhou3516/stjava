package com.zhou.mguava.eventbus.simple;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liqingzhou on 17/8/14.
 */
public class EventBusTest2 {
    private static class EventA {
        public String toString() {
            return "A 类型事件";
        }
    }
    private static class EventB extends EventA {
        public String toString() {
            return "B 类型事件";
        }
    }

    private static class EventListener {
        @Subscribe
        public void onEvent(EventA e) {
            System.out.println("我订阅的是 A事件,接收到:" + e);
        }
        @Subscribe
        public void onEvent(EventB e) {
            System.out.println("我订阅的是B事件,接收到:" + e);
        }


    }
    private static class EventListenerB {
        @Subscribe
        public void onEvent(EventA e) {
            System.out.println("BBBBBB Listener 我订阅的是 A事件,接收到:" + e);
        }
        @Subscribe
        public void onEvent(EventB e) {
            System.out.println("BBBBBBBBB Listener 我订阅的是B事件,接收到:" + e);
        }


    }

    public static void main(String[] args) throws InterruptedException {
        EventBus eb = new EventBus();
        eb.register(new EventListener());
        eb.register(new EventListenerB());
        System.out.println("----------发送事件A---------");
        eb.post(new EventA());
        System.out.println("----------发送事件B---------");
        eb.post(new EventB());
        

    }
}
