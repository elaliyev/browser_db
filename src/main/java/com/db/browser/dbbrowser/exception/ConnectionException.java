package com.db.browser.dbbrowser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ConnectionException extends RuntimeException{

    private static final long serialVersionUID = 2L;

    public ConnectionException(Object message){
        super(String.valueOf(message));
    }
}
