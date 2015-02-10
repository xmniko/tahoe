package io.kuo.support.jersey.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Adds Cross-Origin Resource Sharing (CORS) to all REST services by processing the Origin header request if it exists
 *
 * @author nikog
 */
@Component
public class CORSResponseFilter implements ContainerResponseFilter {
    private String allowOrigin;
    private List<String> allowedOrigins;

    @Autowired
    private CORSPropertyHolder holder;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        allowOrigin = holder.getAllowOrigin();
        allowedOrigins = allowOrigin != null ? Arrays.asList(allowOrigin.split(",")) : null;

        String origin = getOrigin(requestContext);

        // process origin request only if it is on header and we have a list of allowed origins
        if (origin != null && allowOrigin != null) {
            if (isOriginAllowed(origin)) {
                MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();

                // allow ulrs coming the request origin
                responseHeaders.add("Access-Control-Allow-Origin", origin);
                responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

                // add all headers for pre-flight
                MultivaluedMap<String, String> requestHeaders = requestContext.getHeaders();
                List<String> allowHeaders = requestHeaders.get("Access-Control-Request-Headers");
                if (allowHeaders != null && allowHeaders.size() > 0) {
                    // just echo what is on the request
                    responseHeaders.add("Access-Control-Allow-Headers", StringUtils.join(allowHeaders, ','));
                }
            }
        }
    }

    private String getOrigin(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> requestHeaders = requestContext.getHeaders();
        List<String> origins = requestHeaders.get("Origin");
        if (origins == null || origins.size() == 0) {
            return null;
        }

        // only expecting 1 origin
        return origins.get(0) != null ? origins.get(0).trim() : null;
    }

    private boolean isOriginAllowed(String origin) {
        if (origin == null || "".equals(origin)) {
            return false;
        }

        // loop through the list of allowed origins
        for (String allowedOrigin : allowedOrigins) {
            if (allowedOrigin.equalsIgnoreCase(origin)) {
                return true;
            }
        }

        return false;
    }
}
