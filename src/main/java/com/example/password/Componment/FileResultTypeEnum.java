package com.example.password.Componment;

import org.springframework.stereotype.Component;

public enum FileResultTypeEnum {
    Type_3des("0","3des"),
    Type_AES("1","AES");

    private String code;
    private String value;

    FileResultTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String value(String code) {
        FileResultTypeEnum[] types = values();
        for (FileResultTypeEnum x : types) {
            if (x.getCode().equals(code)) {
                return x.getValue();
            }
        }
        return "default";
    }
}
