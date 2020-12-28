package com.zzc.luntan.demo1;

import com.zzc.luntan.demo1.dao.MyUserDetailsServiceDao;
import com.zzc.luntan.demo1.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Demo1ApplicationTests {
    @Autowired
    MyUserDetailsServiceDao myUserDetailsServiceDao;
    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        //System.out.println(myUserDetailsServiceDao.findByUserName("zz").getUsername());
        if(myUserDetailsServiceDao.findByUserName("zz")==null)
            System.out.println("========================");
        List<String> role = myUserDetailsServiceDao.findRoleByUserName("zz");
        for (String s:
             role) {
            System.out.println(s);
        }
        List<String> authority = myUserDetailsServiceDao.findAuthorityByRoleCodes(role);
        for (String s:
             authority) {
            System.out.println(s);
        }
    }
//        System.out.println(passwordEncoder.encode("123456"));
    }

