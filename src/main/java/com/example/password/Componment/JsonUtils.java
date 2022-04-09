package com.example.password.Componment;

import com.alibaba.fastjson.JSONObject;
import com.example.password.Config.MyJackson2HttpMessageConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;

@Component
public class JsonUtils {

    public static HashMap<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {

        }

        return map;
    }

    public static JSONObject postRequest(String url,Object obj){
        HashMap<String,Object> map = objectToMap(obj);
        RestTemplate restTemplate = new RestTemplate();
        //解决text/html问题
        restTemplate.getMessageConverters().add(new MyJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject param = new JSONObject();
        param.putAll(map);

        HttpEntity<String> formEntity = new HttpEntity<String>(param.toJSONString(), headers);

        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url,formEntity,JSONObject.class);
        return responseEntity.getBody();
    }

    //overwirte
    public static JSONObject postRequest(String url, HashMap<String, Object> map){
        RestTemplate restTemplate = new RestTemplate();
        //解决text/html问题
        restTemplate.getMessageConverters().add(new MyJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject param = new JSONObject();
        param.putAll(map);

        HttpEntity<String> formEntity = new HttpEntity<String>(param.toJSONString(), headers);

        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url,formEntity,JSONObject.class);
        return responseEntity.getBody();
    }
}

