package me.hao0.wepay.exception;

/**
 * 签名异常
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public class SignException extends RuntimeException {

    public SignException() {
        super();
    }

    public SignException(String message) {
        super(message);
    }

    public SignException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignException(Throwable cause) {
        super(cause);
    }

    protected SignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
