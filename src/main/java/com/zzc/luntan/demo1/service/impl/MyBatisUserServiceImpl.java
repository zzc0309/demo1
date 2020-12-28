package com.zzc.luntan.demo1.service.impl;

import com.zzc.luntan.demo1.dao.MyBatisUserDao;
import com.zzc.luntan.demo1.pojo.User;
import com.zzc.luntan.demo1.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {
    @Autowired
    MyBatisUserDao myBatisUserDao;

    @Override
    public User getUser(long id) {
        return myBatisUserDao.getUser(id);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return myBatisUserDao.getPasswordByUsername(username);
    }

    @Override
    public String getUsernameByUsername(String username) {
        if(myBatisUserDao.getUsernameByUsername(username)==null){
            return null;}
            //return "";}
        return myBatisUserDao.getUsernameByUsername(username);
    }



    @Override
    public void insertUser(User user){
        myBatisUserDao.insertUser(user);
    }

    //废弃接口
    @Override
    public boolean login(User user) {    //判断用户是否存在,密码是否正确
        if(!user.getPassword().equals(this.getPasswordByUsername(user.getUsername()))||this.getPasswordByUsername(user.getUsername())==null)
        {return true;}
        return false;
    }

    @Override
    public boolean register(User user) {
        if(this.getUsernameByUsername(user.getUsername())==null){
            return true;
        }
        return false;
    }
}
