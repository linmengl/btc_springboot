package com.blockchain.test.designMode.state;

public class StateMain {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request();
        context.request();
        context.request();
        context.request();
        context.request();
    }
}
