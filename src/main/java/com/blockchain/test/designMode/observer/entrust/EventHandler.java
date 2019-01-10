package com.blockchain.test.designMode.observer.entrust;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    //是用一个List
    private List<Event> events;

    public EventHandler() {
        events = new ArrayList<Event>();
    }

    //添加某个对象要执行的事件，及需要的参数
    public void addEvent(Object object, String methodName, Object... args) {
        events.add(new Event(object, methodName, args));
    }

    //通知所有的对象执行指定的事件
    public void notifyX() throws Exception {
        for (Event e : events) {
            e.invoke();
        }
    }
}
