package io.kuo.jersey.support.exception;

/**
 * @author nikog
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
