package com.blockchain.test.designMode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题或者抽象通知者
 */
public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifys(){
        for (Observer o:observers) {
            o.update();
        }
    }
}
