package site.ugaeng.localhostingserver.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectUtils {

    private static final ObjectMapper om = new ObjectMapper();

    public static String convertToString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertToObject(String json, Class<T> targetClass) {
        try {
            return om.readValue(json, targetClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
