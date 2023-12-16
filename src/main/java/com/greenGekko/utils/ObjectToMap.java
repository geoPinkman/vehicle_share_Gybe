package com.greenGekko.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMap {

    public static Map<String, Object> getMap(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try{
            map = mapper.readValue(mapper.writeValueAsString(o), HashMap.class);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T> T getObject(Map<String, Object> map, Class<T> myClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String value = null;
        try {
            value = mapper.writeValueAsString(map);
            return mapper.readValue(value, mapper.getTypeFactory().constructType(myClass));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON parsing failed for " + value, e);
        }
    }

}
