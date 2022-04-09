package com.example.password.Pojo;

import com.example.password.Config.DateConverterConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInformation {
    Integer fileId;     //文件id
    String userId;      //提交的用户id
    String name;        //文件名字
    String size;        //大小
    String path;        //文件夹的路径
    String result;      //结果
    String status;      //状态
    String time;        //提交时间

    public FileInformation(String name,String userId,String path){
        this.fileId = 1;
        this.name = name;
        this.userId = userId;
        File file = new File(path+ "/" + name);
        long fileSize = file.length()/1024 + 1;
        this.size = String.valueOf(fileSize) + "K";
        this.path = path;
        this.result = "default";
        this.status = "false";
        this.time = DateConverterConfig.ft.format(new Date());
    }
}
