package com.blockchain.test.designMode.state;

public class ConcreteStateC implements State {

    @Override
    public void handle(Context context) {
        System.out.println("当前的状态是"+new ConcreteStateC()+"finish…………");
    }
}
