package com.blockchain.test.designMode.decorator;

public class ConcreteDecoratorB extends Decorator{

    @Override
    public void operation() {
        super.operation();
        AddedBehavivor();
        System.out.println("具体装饰着对象B的操作");
    }

    private void AddedBehavivor() {

    }
}
