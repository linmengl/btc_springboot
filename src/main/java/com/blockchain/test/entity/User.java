package com.blockchain.test.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

@Data
@ToString
//@Entity
//@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "phone_no"))
public class User implements UserDetails {


    private static final long serialVersionUID = -1L;

    //@Id
    //@Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
	//
    //@Column(name = "phone_no")
    //private String phoneNo;
	//
    //@Column(name = "password")
    //private String password;
	//
    //@Column(name = "uuid")
    //private String uuid;
	//
    ///**
    // * 是否注销
    // */
    //@Column(name = "enable")
    //private Boolean enable;
	//
    ///**
    // * 创建时间
    // */
    //@Column(name = "created_at")
    //private Timestamp createdAt;
	//
    ///**
    // * 上一次修改时间
    // */
    //@Column(name = "updated_at")
    //private Timestamp updatedAt;


    private Long id;

    private String phoneNo;

    private String password;

    private String uuid;

    private Boolean enable;

    private Timestamp createdAt;

    private Timestamp updatedAt;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return phoneNo;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
