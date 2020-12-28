package com.zzc.luntan.demo1.auth;

import com.zzc.luntan.demo1.dao.MyUserDetailsServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class MyUserDetailsSevice implements UserDetailsService {
    @Autowired
    MyUserDetailsServiceDao myUserDetailsServiceDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUserDetails myUserDetails = myUserDetailsServiceDao.findByUserName(username);

        List<String> roleCodes = myUserDetailsServiceDao.findRoleByUserName(username);


        List<String> authorities = myUserDetailsServiceDao.findAuthorityByRoleCodes(roleCodes);

        roleCodes=roleCodes.stream().map(rc -> "ROLE_"+rc).collect(Collectors.toList());
        authorities.addAll(roleCodes);
        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",",authorities)
        ));

        return myUserDetails;
    }
}
