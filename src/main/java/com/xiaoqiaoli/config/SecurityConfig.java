package com.xiaoqiaoli.config;

import com.xiaoqiaoli.service.impl.ChocolateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ChocolateUserDetailsService chocolateUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/media/**", "/favicon.ico", "/scripts/**", "/styles/**", "/assets/**", "/maps/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .failureUrl("/login-error").defaultSuccessUrl("/")
                .and().csrf().disable().logout().logoutUrl("/logout").logoutSuccessUrl("/index");
//        .invalidateHttpSession(true).deleteCookies();
    }
    // @formatter:on

    // @formatter:off
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT USERNAME, PASSWORD FROM MDC_ACCOUNT WHERE USERNAME=?")
//                .authoritiesByUsernameQuery("SELECT T1.USERNAME, T4.CODE AS ROLE FROM MDC_ACCOUNT T1 INNER JOIN MDC_USER T2 ON T1.USER_ID=T2.ID INNER JOIN MDC_USER_ROLE T3 ON T2.ID=T3.USER_ID INNER JOIN MDC_ROLE T4 ON T3.ROLE_ID=T4.ID WHERE T1.USERNAME=?");
        auth.userDetailsService(chocolateUserDetailsService).and()
                .inMemoryAuthentication()
                .withUser("admin").password("123456").roles("ADMIN");
    }

}
