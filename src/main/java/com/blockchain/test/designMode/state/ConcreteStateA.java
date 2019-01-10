package com.blockchain.test.designMode.state;

public class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前状态是……"+new ConcreteStateA());
        context.setState(new ConcreteStateB());
    }
}
