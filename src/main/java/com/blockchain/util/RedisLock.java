package com.blockchain.util;


import redis.clients.jedis.Jedis;

public class RedisLock {
	private static final String LOCK_SUCCESS = "OK";
	//NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
	private static final String SET_NX = "NX";
	//EX = seconds; PX = milliseconds
	private static final String PX = "PX";

	public static boolean getLock(Jedis jedis, String key, String value, int expireTime) {
		String result = jedis.set(key, value, SET_NX, PX, expireTime);
		return LOCK_SUCCESS.equals(result);
	}
}
