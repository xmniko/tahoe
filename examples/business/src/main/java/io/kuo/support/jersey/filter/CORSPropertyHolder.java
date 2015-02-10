package io.kuo.support.jersey.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CORSPropertyHolder {

    @Value("${cors.allow.origin:NA}")
    private String allowOrigin;

    public String getAllowOrigin() {
        return "NA".equalsIgnoreCase(allowOrigin) ? null : allowOrigin;
    }

}
