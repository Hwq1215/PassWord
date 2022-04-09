package com.example.password.mapper;

import com.example.password.Pojo.IndexImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface InstructionMapper {
    @Select("select * from index_img_info")
    List<IndexImg> getIndexImg();
}