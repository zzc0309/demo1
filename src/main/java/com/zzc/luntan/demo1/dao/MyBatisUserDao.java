package com.zzc.luntan.demo1.dao;

import com.zzc.luntan.demo1.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisUserDao {
     User getUser(Long id);
     String getPasswordByUsername(String username);
     String getUsernameByUsername(String username);
     void insertUser(User user);
     int getId(String username);
     void insertUserRole(int id);
}
