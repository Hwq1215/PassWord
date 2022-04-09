package com.example.password.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PModel {
    Integer modelId;        //模板id
    String userId;          //用户名
    String modelName;       //模型名称
    String modelType;       //模型类型
    String modelPath;      //模型位置
    String time;            //日期
    float ratio;            //训练集合/总数据集合
    int epoch;
    int batchSize;
    int cipherMatrixSize;   //密文矩阵大小
    int bitcountwise;       //比特计数宽度

}
