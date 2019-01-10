package com.blockchain.test.designMode.observer.entrust;

import lombok.Data;

@Data
public abstract class Notifier {
    private EventHandler eventHandler = new EventHandler();

    //增加需要帮忙放哨的学生
    public abstract void addListener(Object object, String methodName, Object... args);

    //告诉所有要帮忙放哨的学生：老师来了
    public abstract void notifyX();
}
