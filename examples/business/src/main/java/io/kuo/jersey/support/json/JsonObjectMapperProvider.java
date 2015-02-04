package io.kuo.jersey.support.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.ws.rs.ext.ContextResolver;

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
        ObjectMapper result = new ObjectMapper();
        result.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        return result;
    }

}
