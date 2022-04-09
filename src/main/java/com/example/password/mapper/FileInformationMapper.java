package com.example.password.mapper;

import com.example.password.Pojo.FileInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileInformationMapper {
    @Options(keyProperty = "fileId",useGeneratedKeys = true)
    @Insert("insert file_info(userId,name,size,path,result,status,time) values " +
            "(#{userId},#{name},#{size},#{path},#{result},#{status},#{time}) ")
    int addFileInformation(FileInformation fileInformation);

    @Select("select * from file_info where fileId=#{fileId}")
    FileInformation getFileInformationByFileId(Integer fileId);

    @Select("select * from file_info where userId=#{userId}")
    List<FileInformation> getFileInformationByUserId(String userId);

    @Update("update file_info set status=#{status},result=#{result} where fileId=#{fileId}")
    void updateFileInformation(Integer fileId,String status,String result);

    @Select("select * from file_info where path=#{path}")
    FileInformation getFileInformationByPath(String path);
}
