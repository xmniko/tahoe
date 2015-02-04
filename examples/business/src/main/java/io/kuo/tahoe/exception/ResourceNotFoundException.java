package io.kuo.tahoe.exception;

import javax.validation.ValidationException;

/**
 * @author nikog
 */
public class ResourceNotFoundException extends ValidationException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
