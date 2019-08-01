package com.czxy.dao;

import com.czxy.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Select("select * from user where phone=#{user.phone} and password=#{user.password} or email=#{user.phone}")
    public List<User> findUserByUser(@Param("user") User user);


    @Select("select * from user where phone=#{phone}")
    public List<User> findUserByPhone(@Param("phone") String phone);
//
//
    @Select("select * from user where email=#{email}")
    public List<User> findUserByEmail(@Param("email") String email);

    @Select("select distinct sex from user")
    public List<User> findUserBySex();

}
