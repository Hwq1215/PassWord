package com.example.password.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.password.Componment.FileResultTypeEnum;
import com.example.password.Componment.JsonUtils;
import com.example.password.Pojo.FileInformation;
import com.example.password.Service.FileInformationService;
import com.example.password.mapper.FileInformationMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FileRecognitionRunnable implements Runnable{
    @Autowired
    FileInformationService fileInformationService = new FileInformationService();
    @Autowired
    FileInformationMapper fileInformationMapper;

    private String url;
    private String modelPath;
    private FileInformation fileInformation;

    public void giveValue(String url, String modelPath, FileInformation fileInformation) {
        this.url = url;
        this.modelPath = modelPath;
        this.fileInformation = fileInformation;
    }

    @Override
    public void run() {
        String result = FileResultTypeEnum.value(loadModel(fileInformation, url, modelPath));
        System.out.println("文件编号" + fileInformation.getFileId() + "结果是" + result);
        fileInformationMapper.updateFileInformation(fileInformation.getFileId(),"true",result);
    }

    public String loadModel(FileInformation fileInformation,String url,String modelPath){
        HashMap<String,Object> map = new HashMap<>();
        map.put("fileDir",fileInformation.getPath());
        map.put("modelPath",modelPath);
        JSONObject jsonObject = JsonUtils.postRequest(url,map);
        String backValue = jsonObject.getString("label");
        return backValue;
    }
}
