package com.zzc.luntan.demo1;

import com.zzc.luntan.demo1.dao.MyUserDetailsServiceDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication(scanBasePackages = {"com.zzc.luntan.demo1"},exclude= SecurityAutoConfiguration.class)
@MapperScan(basePackages = "com.zzc.luntan.demo1.*")
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);

    }

}
