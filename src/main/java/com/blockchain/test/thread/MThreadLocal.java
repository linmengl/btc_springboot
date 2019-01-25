package com.blockchain.test.thread;

class MThreadLocal {
	public static void main(String[] args) {
		ThreadLocal<String> s = new ThreadLocal<>();
		ThreadLocal<Long> s2 = new ThreadLocal<>();
		s.set("ss");
		s.set("aa");
		s2.set(2L);
		System.out.println(s.get());
		System.out.println(s2.get());
	}


}


