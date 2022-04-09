package com.example.password.Controller;

import com.example.password.Pojo.FileInformation;
import com.example.password.Pojo.RetResult;
import com.example.password.Pojo.User;
import com.example.password.Service.FileInformationService;
import com.example.password.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService = new UserService();
    @Autowired
    FileInformationService fileInformationService = new FileInformationService();

    @GetMapping("user/get")
    public RetResult<User> getUserById(@RequestParam("userId") String userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return new RetResult<>(404);
        }
        return new RetResult<>(200,"success",user);
    }


    @PostMapping("user/change")
    public RetResult<User> changeUserNew(@RequestBody User user){
        RetResult<User> result;
        switch (userService.updateUser(user.getUserId(),user.getPassword())){
            case 200:
                result = new RetResult<User>(200,"change success",new User(user.getUserId(),user.getPassword()));
                break;
            case 404:
                result = new RetResult<User>(404,"user no exist",null);
                break;
            default:
                result = new RetResult<User>(404,"fault",null);
                break;
        }
        return result;
    }

    @GetMapping("user/files")
    public RetResult<List<FileInformation>> getFilesByUserId(@RequestParam("userId") String userId){
        RetResult result;
        List<FileInformation> fileInformationList = fileInformationService.getFileInformation(userId);
        if(fileInformationList == null){
            result = new RetResult<>(404,"no have files",null);
        }else{
            result = new RetResult(200,"success",fileInformationList);
        }
        return result;
    }
}
