package com.catalog.spring.config.exception;

import org.springframework.beans.BeansException;

/**
 * Created by Lance on 14/02/2015.
 */
public class SpringConfigException extends BeansException {

    public SpringConfigException(String message) {
        super(message);
    }

    public SpringConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
