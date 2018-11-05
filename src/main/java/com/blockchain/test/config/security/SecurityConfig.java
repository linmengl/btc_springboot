package com.blockchain.test.config.security;

import com.blockchain.test.Constants;
import com.blockchain.test.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.util.StringUtils;


@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Value("${btc.auth.allowedUrls}")
    private String allowedUrls;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] allowedRoutesArr = allowedUrls.split(",");
        http
            .csrf().disable()  //关闭CSFR支持
            .authorizeRequests()
                .antMatchers(allowedRoutesArr).permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .authenticationEntryPoint(new OverrideAuthenticationEntryPoint())
                .and()
            .logout();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPass) {
                return PasswordUtil.MD5(rawPass.toString().toLowerCase() + Constants.PASSWORD_SALT);
            }

            @Override
            public boolean matches(CharSequence rawPass, String password) {
                return !StringUtils.isEmpty(password) && password.equals(PasswordUtil.MD5(
                        rawPass.toString().toLowerCase() + Constants.PASSWORD_SALT));
            }
        });
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }

}
