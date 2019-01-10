package com.blockchain.service.user.impl;

import com.blockchain.entity.User;
import com.blockchain.service.user.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        User user = userService.findByPhoneNo(phoneNo);
        if (user == null) {
            throw new UsernameNotFoundException("未找到手机号：" + phoneNo);
        }
        return user;
    }
}
