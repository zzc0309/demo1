package com.zzc.luntan.demo1.dao;

import com.zzc.luntan.demo1.auth.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserDetailsServiceDao {
    @Select("Select username,password,enable\n"+
            "FROM sys_user u\n"+
            "WHERE u.username=#{userId}")
    MyUserDetails findByUserName(@Param("userId") String userId);

    @Select("SELECT role_code\n" +
            "FROM sys_role r\n" +
            "LEFT JOIN sys_user_role ur ON r.id=ur.role_id\n" +
            "LEFT JOIN sys_user u ON u.id=ur.user_id\n" +
            "WHERE u.username=#{userId}")
    List<String> findRoleByUserName(@Param("userId") String userId);

    @Select({
            "<script>",
            "SELECT url",
                    "FROM sys_menu m",
                    "LEFT JOIN sys_role_menu rm ON m.id=rm.menu_id",
                    "LEFT JOIN sys_role r ON r.id=rm.role_id",
                    "WHERE 1=1 " ,
            "<if test='roleCodes.size>0'>",
            "and r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</if>",
            "</script>"
    })
        List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}
