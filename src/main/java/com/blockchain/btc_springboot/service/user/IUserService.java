package com.blockchain.btc_springboot.service.user;

import com.blockchain.btc_springboot.entity.User;

public interface IUserService {

	User findByPhoneNo(String phone);
}
