package com.blockchain.service.user.impl;

import com.blockchain.entity.User;
import com.blockchain.service.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {


	@Override
	public User findByPhoneNo(String phone) {
		User user = new User();
		user.setId(123L);
		user.setEnable(true);
		user.setPassword("123.");
		user.setPhoneNo("13843838438");
		user.setUuid(UUID.randomUUID().toString());
		return user;
	}
}
