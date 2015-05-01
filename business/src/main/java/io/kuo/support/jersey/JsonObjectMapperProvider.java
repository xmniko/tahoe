package io.kuo.support.jersey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.ws.rs.ext.ContextResolver;
import java.text.SimpleDateFormat;

public class JsonObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public JsonObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setDateFormat(new SimpleDateFormat(DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
        return objectMapper;
    }

}
