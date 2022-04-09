package com.example.password.Componment;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}

