package io.kuo.tahoe;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.kuo.support.jersey.JsonObjectMapperProvider;
import io.kuo.support.jersey.filter.CORSResponseFilter;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * Created by nikog on 1/30/2015.
 */
public class TahoeApplication extends ResourceConfig {

    public TahoeApplication() {
        super.setApplicationName("tahoe");

        register(RequestContextFilter.class);
        register(CORSResponseFilter.class);
        register(LoggingFilter.class);
        register(JsonObjectMapperProvider.class);
        register(JacksonJaxbJsonProvider.class, new Class[]{MessageBodyReader.class, MessageBodyWriter.class});

        packages("io.kuo.tahoe.resource,io.kuo.support.jersey,com.fasterxml.jackson.jaxrs.base");
    }
}
