package io.kuo.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author nikog
 * 
 */
public class GsonUtil {
    /**
     * Serialize to Json string.
     * 
     * @param bean
     * @param type
     * @return json string
     */
    public static String bean2json(Object bean, Type type) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateSerializer())
                .setDateFormat(DateFormat.LONG).create();
        return gson.toJson(bean);
    }

    /**
     * Deserialize from json string.
     *
     * @param json
     * @param type
     * @return bean
     */
    public static <T> T json2bean(String json, Type type) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateDeserializer())
                .setDateFormat(DateFormat.LONG).create();
        //FIXME: to fix ant build error.
        return (T) gson.fromJson(json, type);
    }

    public static String map2json(Map<String, Object> map) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateDeserializer())
                .setDateFormat(DateFormat.LONG).create();
        return gson.toJson(map);
    }

    //----------------- util inner class ---------------------------------
    static class GsonDateSerializer implements JsonSerializer<Date> {

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc,
                JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }

    }

    static class GsonDateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }
}
