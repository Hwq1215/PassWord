package com.example.password.mapper;

import com.example.password.Pojo.PModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface PModelMapper {
    @Select("select * form model_info where modelName=#{modelName}")
    PModel getModelByName(String modelName);
}
