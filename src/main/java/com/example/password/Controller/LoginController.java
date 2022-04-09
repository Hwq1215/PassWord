package com.example.password.Controller;

import com.example.password.Pojo.RetResult;
import com.example.password.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    UserService userService = new UserService();

    @GetMapping("login/in")
    public RetResult loginIn(@RequestParam("userId") String userId,
                             @RequestParam("password") String password){
        RetResult result = null;
        switch (userService.checkUser(userId,password)){
            case 200:
                result = new RetResult(200,"user exist and password correct",userService.checkUser(userId,password));
                System.out.println("userId " + userId + " login in");
                break;
            case 210:
                result = new RetResult(210,"user exist but password incorrect",null);
                break;
            case 220:
                result = new RetResult(220,"user no exist",null);
                break;
            default:break;
        }
        return result;
    }
}
