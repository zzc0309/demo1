package com.zzc.luntan.demo1.controller;

import com.zzc.luntan.demo1.auth.MyUserDetails;
import com.zzc.luntan.demo1.dao.MyBatisUserDao;
import com.zzc.luntan.demo1.pojo.Result;
import com.zzc.luntan.demo1.pojo.User;
import com.zzc.luntan.demo1.service.impl.MyBatisUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller

//@RequestMapping("/01")
public class MyBatisUserController {
    @Resource
    PasswordEncoder passwordEncoder;

    @Autowired
    MyBatisUserDao myBatisUserDao;
    @Autowired
    MyBatisUserServiceImpl myBatisUserService;

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
       return myBatisUserService.getUser(id);
    }

    //被废弃的接口
    @ResponseBody
    @PostMapping("/login01")
    public Result login(@RequestBody User user, HttpServletResponse resp, HttpServletRequest req, Model model){

        if(myBatisUserService.login(user)){
            return  new Result(400,"用户名或密码错误");
        }
        model.addAttribute("status","200");
        HttpSession session = req.getSession();
        session.setAttribute(session.getId(),user.getUsername());

        return  new Result(200,"登陆成功");
    }

    @ResponseBody
    @PostMapping("/registe")
    public Result registe(@RequestBody User user){
    if(myBatisUserService.register(user)){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myBatisUserService.insertUser(user);
        myBatisUserDao.insertUserRole(myBatisUserDao.getId(user.getUsername()));
            return new Result(200,"注册成功");
    }
    return new Result(400,"该用户名已存在!");
    }

    //密码正确跳转的接口
    @ResponseBody
    @PostMapping("/login02")
    public Result login02(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails=(MyUserDetails)principal;
        if(myUserDetails.toString().contains("ROLE_admin"))
            return new Result(200,"是VIP");   //200是VIP
        return new Result(400,"不是VIP");
    }

    //密码错误跳转的接口
    @ResponseBody
    @PostMapping("/login03")
    public Result login03(){
        return new Result(404,"登录失败");
    }


}
