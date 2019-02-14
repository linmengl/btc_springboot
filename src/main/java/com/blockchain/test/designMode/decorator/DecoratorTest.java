package com.blockchain.test.designMode.decorator;

public class DecoratorTest {

    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA ca = new ConcreteDecoratorA();
        ConcreteDecoratorB cb = new ConcreteDecoratorB();

        ca.setComponent(c);
        cb.setComponent(ca);
        ca.operation();
        cb.operation();
    }
}
