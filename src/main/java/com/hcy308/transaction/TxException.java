package com.hcy308.transaction;

public class TxException extends RuntimeException {

    public TxException(String message) {
        super(message);
    }

    @Override
    public TxException initCause(Throwable cause) {
        super.initCause(cause);
        return this;
    }

}
