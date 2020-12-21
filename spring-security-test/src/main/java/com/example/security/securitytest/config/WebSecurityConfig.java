package com.example.security.securitytest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/12/18 15:38
 * @version: v0.0.1
 * @history:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
         * 配置为从内存中进行加载认证信息.
         * 这里配置了两个用户 admin和user
         */
        auth.inMemoryAuthentication()
        .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles();
    }


}
