package com.lhamacorp.apibarbershop.infra.exception;

public class IdValidationException extends RuntimeException {
    public IdValidationException(String msg) {
        super(msg);
    }
}
