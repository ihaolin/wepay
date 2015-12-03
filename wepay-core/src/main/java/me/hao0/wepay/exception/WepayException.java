package me.hao0.wepay.exception;

/**
 * 微信支付异常
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 * @since 1.0.0
 */
public class WepayException extends RuntimeException {

    /**
     * 当微信支付业务错误时，会有对应的CODE值
     */
    private String errorCode;

    public WepayException(Throwable cause) {
        super(cause);
    }

    public WepayException(String message) {
        super(message);
    }

    public WepayException(String errorCode, String message){
        super("[" + errorCode + "]"+ message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
