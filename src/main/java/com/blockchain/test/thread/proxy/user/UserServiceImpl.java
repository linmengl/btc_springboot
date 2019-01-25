package com.blockchain.test.thread.proxy.user;

import java.lang.reflect.Proxy;

public class UserServiceImpl implements UserService {

	@Override
	public void removeUser(int userId) {
		System.out.println("模拟删除用户：" + userId);
		try {
			Thread.currentThread().sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		// 将目标业务类和横切代码编织到一起
		PerformanceHandler handler = new PerformanceHandler(userService);

		// 根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
		UserService proxy = (UserService) Proxy.newProxyInstance(userService
				.getClass().getClassLoader(), userService.getClass().getInterfaces(), handler);

		proxy.removeUser(3);

	}

}
