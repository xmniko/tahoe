package io.kuo.support.jersey;

import io.kuo.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author nikog
 */
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Context
    private HttpServletRequest httpServletRequest;

    @Override
    public Response toResponse(RuntimeException exception) {
        ExceptionResponse exceptionResponse = sanitize(exception, httpServletRequest);

        String entity = JsonUtil.bean2json(exceptionResponse, ExceptionResponse.class);
        return Response.status(exceptionResponse.getStatus())
                .entity(entity).type(MediaType.APPLICATION_JSON).build();
    }

    private ExceptionResponse sanitize(Exception exception, HttpServletRequest httpServletRequest) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setType(exception.getClass().getSimpleName());
        exceptionResponse.setMessage(exception.getMessage());

        exceptionResponse.setStatus(500);

        logger.error(String.format("Exception occurred at %s", getFullRequestURL(httpServletRequest)), exception);

        return exceptionResponse;
    }

    private Object getFullRequestURL(HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(httpServletRequest.getQueryString())) {
            return httpServletRequest.getRequestURL();
        }

        return String.format("%s?%s", httpServletRequest.getRequestURL(), httpServletRequest.getQueryString());
    }

    class ExceptionResponse {
        private Integer status;
        private String message;
        private String type;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
