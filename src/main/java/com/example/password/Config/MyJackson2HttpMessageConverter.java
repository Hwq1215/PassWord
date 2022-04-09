package com.example.password.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public MyJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
        setSupportedMediaTypes(mediaTypes);// tag6
    }
}
