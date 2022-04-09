package com.example.password.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelLabel {
    Integer modelId;    //对应模型id
    Integer label;      //标签（0，1一样的序号）
    String algorithmName;   //算法名字
}
