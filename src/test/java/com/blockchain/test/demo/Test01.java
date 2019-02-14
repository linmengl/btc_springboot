package com.blockchain.test.demo;

import org.junit.Test;

public class Test01 {

	@Test
	public void test01(){
		String s1 = "abc";
		String s2 = "a";
		String s3 = "bc";
		String s4 = s2 + s3;
		System.out.println(s1 == s4);
	}

	@Test
	public void test02(){
		int n = 12;
		System.out.println(n&(n-1));
	}
}
