package com.blockchain.test.designMode.decorator;

import lombok.Data;

@Data
public class Decorator extends Component {

    protected Component component;

    @Override
    public void operation() {
        if (component != null){
            component.operation();
        }
    }
}
