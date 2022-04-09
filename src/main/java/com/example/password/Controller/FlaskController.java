package com.example.password.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.password.Componment.JsonUtils;
import com.example.password.Pojo.FileInformation;
import com.example.password.Pojo.PModel;
import com.example.password.Service.FileInformationService;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class FlaskController {

    public static boolean trainModel(PModel model){
        JSONObject jsonObject = JsonUtils.postRequest("",model);
        boolean backValue = jsonObject.getBooleanValue("");
        return backValue;
    }

    public static String loadModel(FileInformation fileInformation,String url,String modelPath){
        HashMap<String,Object> map = new HashMap<>();
        map.put("fileDir",fileInformation.getPath());
        map.put("modelPath",modelPath);
        JSONObject jsonObject = JsonUtils.postRequest(url,map);
        String backValue = jsonObject.getString("label");
        return backValue;
    }

}
