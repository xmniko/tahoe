package io.kuo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * Created by nikog on 2/5/2015.
 */
public class JsonUtil {

    private static ObjectMapper objectMapper;

    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        } else if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static String bean2json(Object object, Type type) {
        try {
            ObjectMapper objectMapper = JsonUtil.getMapperInstance(false);
            objectMapper.setDateFormat(new SimpleDateFormat(DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO
        }
        return null;
    }

    public static <T> T json2bean(String json, Type type) {
        try {
            ObjectMapper mapper = JsonUtil.getMapperInstance(false);
            return (T) mapper.readValue(json, type.getClass());
        } catch (IOException e) {
            e.printStackTrace();
            // TODO
        }

        return null;
    }
}
