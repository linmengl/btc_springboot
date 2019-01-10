package com.blockchain.test.designMode.state;

public class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前状态是……"+new ConcreteStateB());
        context.setState(new ConcreteStateC());
    }
}
