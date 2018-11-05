package com.blockchain.test.service.user;

import com.blockchain.test.entity.User;

public interface IUserService {

	User findByPhoneNo(String phone);
}
