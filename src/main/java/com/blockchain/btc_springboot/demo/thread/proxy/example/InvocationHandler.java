package com.blockchain.btc_springboot.demo.thread.proxy.example;

import java.lang.reflect.Method;

public interface InvocationHandler {
	void invoke(Object proxy, Method method, Object[] args);
}
