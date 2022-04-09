package com.example.password.mapper;

import com.example.password.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user_info where userId=#{userId}")
    User getUserById(String userId);

    @Select("select * from user_info where userId=#{userId} and password=#{password}")
    User getUserByIdByPassword(String userId,String password);

    @Update("update user_info set password=#{password} where userId=#{userId}")
    void updateUser(String userId,String password);
}
