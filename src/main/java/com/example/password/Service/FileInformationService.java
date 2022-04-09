package com.example.password.Service;

import com.example.password.Pojo.FileInformation;
import com.example.password.mapper.FileInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileInformationService {

    @Autowired
    FileInformationMapper fileInformationMapper;

    public boolean addFileInformation(FileInformation fileInformation){
        int enable = fileInformationMapper.addFileInformation(fileInformation);
        if(enable>0){
            return true;
        }
        return false;
    }

    public List<FileInformation> getFileInformation(String userId){
        List<FileInformation> fileInformationList =
                fileInformationMapper.getFileInformationByUserId(userId);
        return fileInformationList;
    }

    public FileInformation getFileInformationByFileId(Integer fileId){
        FileInformation fileInformation =
                fileInformationMapper.getFileInformationByFileId(fileId);
        return  fileInformation;
    }


    public FileInformation setFileInformationResult(Integer fileId,String result){
        System.out.println(fileId + result);
        fileInformationMapper.updateFileInformation(fileId,"true",result);
        FileInformation fileInformation = fileInformationMapper.getFileInformationByFileId(fileId);
        return fileInformation;
    }

    public FileInformation getFileInformationByPath(String path){
        return fileInformationMapper.getFileInformationByPath(path);
    }


}
