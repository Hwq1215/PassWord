package com.example.password.Componment;

import org.springframework.stereotype.Component;


public enum ModelTypeEnum {
    Model_CNN_RF("CNN-RF","no.model"),
    Model_CNN_SVM("CNN-SVM","no.model"),
    Model_AlexNet("AlexNet","no.model"),
    Model_SCNN("SCNN","no.model"),
    Model_ResNet("ResNet","resnet.model"),
    Model_VGG("VGG","no.model");

    static final String modelPath = "/home/passwordProject/model/";

    private String frontString;
    private String modelName;

    ModelTypeEnum(String frontString, String pathName) {
        this.frontString = frontString;
        this.modelName = pathName;
    }

    public String getFrontString() {
        return frontString;
    }

    public String getModelName() {
        return modelName;
    }

    public static String modelPath(String frontString){
        ModelTypeEnum[] modelTypes = values();
        for(ModelTypeEnum x:modelTypes){
            if(x.getFrontString().equals(frontString)){
                return modelPath + x.getModelName();
            }
        }
        return modelPath + "no.model";
    }
}
