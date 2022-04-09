package com.example.password.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.password.Componment.Base64Utils;
import com.example.password.Componment.ModelTypeEnum;
import com.example.password.Componment.UUIDUtils;
import com.example.password.Pojo.FileInformation;
import com.example.password.Pojo.RetResult;
import com.example.password.Service.FileInformationService;
import com.example.password.Service.FileRecognitionRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@CrossOrigin
public class LoadModelController {

    @Autowired
    FileInformationService fileInformationService = new FileInformationService();

    @Autowired
    FileRecognitionRunnable runnable;

    final static String TempPath ="/home/passwordProject/files/";
    @PostMapping("/loadModel")
    public RetResult loadModel(@RequestBody JSONObject jsonObject
                               ){
        String fileName = jsonObject.getString("fileName");
        String userId = jsonObject.getString("userId");
        String base64String = jsonObject.getString("raw");
        String modelFrontString = jsonObject.getString("model_name");
        String FileType = fileName.toLowerCase(Locale.ROOT).substring(fileName.indexOf('.') + 1,fileName.length());
        String extFrontPath = TempPath + UUIDUtils.getUUID() +"/";
        String extFilePath = extFrontPath + fileName;
        Base64Utils.decodeBase64String(FileType,base64String,extFilePath);
        FileInformation fileInformation = new FileInformation(fileName,userId,extFrontPath);
        System.out.println("文件输出路径为" + extFilePath);
        if(fileInformationService.addFileInformation(fileInformation) ){
            FileInformation fileData = fileInformationService.getFileInformationByPath(extFrontPath);
            try{
                runnable.giveValue("http://127.0.0.1:5000/loadModel", ModelTypeEnum.modelPath(modelFrontString),fileData);//枚举model
                //runnable.giveValue("http://127.0.0.1:5000/loadModel", "/home/passwordProject/model/resnet.model",fileData);
                Thread thread =new Thread(runnable);
                thread.start();
            }catch (Exception e){
                return new RetResult(403,"多线程错误",null);
            }
            return new RetResult<>(200);
        }
        return new RetResult<>(404);
    }

}
