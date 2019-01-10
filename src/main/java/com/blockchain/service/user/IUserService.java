package com.blockchain.service.user;

import com.blockchain.entity.User;

public interface IUserService {

	User findByPhoneNo(String phone);
}
