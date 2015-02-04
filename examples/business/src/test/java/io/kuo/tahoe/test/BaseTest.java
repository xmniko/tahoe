package io.kuo.tahoe.test;

import io.kuo.tahoe.GlobalResourceConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.ws.rs.core.Application;

/**
 * Created by nikog on 1/30/2015.
 */
public class BaseTest extends JerseyTest {
    @Override
    protected Application configure() {
        // Enable logging.
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        // Create an instance of MyApplication ...
        ResourceConfig resourceConfig = new GlobalResourceConfig();
        resourceConfig.property("contextConfigLocation", "classpath:spring-test.xml");

        return resourceConfig;
    }
}
