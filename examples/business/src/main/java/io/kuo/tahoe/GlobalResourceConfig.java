package io.kuo.tahoe;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by nikog on 1/30/2015.
 */
public class GlobalResourceConfig extends ResourceConfig {

    public GlobalResourceConfig() {
        super(JacksonFeature.class, JsonObjectMapperProvider.class);
        super.setApplicationName("tahoe");

        register(RequestContextFilter.class);
        register(LoggingFilter.class);

        packages("io.kuo.tahoe");
    }
}
