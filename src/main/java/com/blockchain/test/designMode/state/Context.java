package com.blockchain.test.designMode.state;

import lombok.Data;

@Data
public class Context {
    private State state;

    public Context(State state){
        this.state = state;
    }

    public void request(){
        state.handle(this);
    }

}
