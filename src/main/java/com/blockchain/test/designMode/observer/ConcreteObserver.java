package com.blockchain.test.designMode.observer;

public class ConcreteObserver implements Observer {
    private String name;
    private Subject subject;

    public ConcreteObserver(ConcreteSubject subject,String name){
        this.subject = subject;
        this.name = name;
    }
    @Override
    public void update() {
        System.out.println("ConcreteObserver……收到通知");
    }
}
