package com.example.password.Pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Data
public class RetResult<T> {

    private int code;
    private String msg;
    private T data;

    public RetResult(int code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public RetResult(int code){
        this.code = code;
        if(code == 200){
            this.msg = "success";
        }else if(code == 404){
            this.msg = "fail";
        }else{
            this.msg = "other";
        }
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}


