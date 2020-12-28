package com.zzc.luntan.demo1.service;

import com.zzc.luntan.demo1.pojo.User;

public interface MyBatisUserService {
         User getUser(long id);
         String getUsernameByUsername(String username);
        String getPasswordByUsername(String username);
        void insertUser(User user);
        boolean login(User user);
        boolean register(User user);
}
