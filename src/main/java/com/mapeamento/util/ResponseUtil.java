package com.mapeamento.util;

import java.util.HashMap;

public class ResponseUtil {

    public static Object error (String error){
        HashMap<String, Object> result = new HashMap<>();
        result.put("state","error");
        result.put("data",error);
        return result;
    }
    public static Object success(String message){
        HashMap<String, Object> result = new HashMap<>();
        result.put("state","success");
        result.put("data", message);
        return result;
    }
    public static Object success(Object data){
        HashMap<String, Object> result = new HashMap<>();
        result.put("state","success");
        result.put("data",data);
        return result;
    }

}
