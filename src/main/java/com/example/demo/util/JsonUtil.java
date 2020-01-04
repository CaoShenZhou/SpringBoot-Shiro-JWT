package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @version v1.0.0
 * @className: JsonUtil
 * @author: Mr.Cao
 * @description: TODO JSON工具类
 * @date: 2019/08/20/上午 02:49
 **/
public class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @author: Mr.Cao
     * @description: TODO JSON转Entity
     * @version: v1.0.0
     * @date 2019/08/20/上午 02:51
     **/
    public static <T> T jsonToEntity(String jsonString, Class<T> clazz) {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author: Mr.Cao
     * @description: TODO JSON转Map
     * @version: v1.0.0
     * @date 2019/08/20/上午 02:52
     **/
    public static <T> Map<String, Object> jsonToMap(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author: Mr.Cao
     * @description: TODO Map转Entity
     * @version: v1.0.0
     * @date 2019/08/20/上午 02:54
     **/
    public static <T> T mapToEntity(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * @author: Mr.Cao
     * @description: TODO Object转Json
     * @version: v1.0.0
     * @date 2019/08/22/下午 11:59
     **/
    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author: Mr.Cao
     * @description: TODO Json转List
     * @version: v1.0.0
     * @date 2019/9/18/15:14
     **/
    public static <T> List<T> jsonToList(String json, Class<T> beanType) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = mapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
