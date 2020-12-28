package com.zzc.luntan.demo1.config;

import com.zzc.luntan.demo1.auth.MyUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Resource
    MyUserDetailsSevice myUserDetailsSevice;
    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//            .withUser("user")
//            .password(passwordEncoder().encode("123456"))
//            .roles("user");
//    auth.inMemoryAuthentication()
//            .withUser("admin")
//            .password(passwordEncoder().encode("123456"))
//            .roles("admin");
        auth.userDetailsService(myUserDetailsSevice).passwordEncoder(passwordEncoder());
    }

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index","/swagger-ui.html").permitAll()
                .antMatchers("/loginTake","/upload.do").hasAnyAuthority("ROLE_user","ROLE_admin");


        http.csrf().disable();
        http
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/login")
                .successForwardUrl("/login02")
                .failureForwardUrl("/login03")
                .and()
                .logout()
                .logoutSuccessUrl("/index")
                .and()
                .rememberMe();
    }
}


