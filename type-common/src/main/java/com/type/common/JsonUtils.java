package com.type.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author : dx
 * @Date : 2017/4/7
 * Description :
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();
    public static String getJsonString(Object o) {
        try {
            return  mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static Object getObjectFromJson(String json,Class clazz) {
        try {
            return mapper.readValue(json,clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
