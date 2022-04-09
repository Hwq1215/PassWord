package com.example.password.Service;

import com.example.password.Pojo.User;
import com.example.password.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(String id){
        return userMapper.getUserById(id);
    }

    public Integer checkUser(String userId, String password){
            User user;
            user = userMapper.getUserByIdByPassword(userId, password);
            if(user == null){
                user = userMapper.getUserById(userId);
                if(user == null){
                    return 220;
                }else{
                    return 210;
                }
            }else{
                return 200;
            }
        }

    public Integer updateUser(String userId, String password){
        User user = userMapper.getUserById(userId);
        if(user == null){
            return 404;
        }else {
            userMapper.updateUser(userId, password);
            return 200;
        }
    }
    }

