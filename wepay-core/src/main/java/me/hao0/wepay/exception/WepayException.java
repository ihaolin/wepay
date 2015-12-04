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
     * 当微信发生错误时，对应的错误码
     */
    private String errorCode;

    /**
     * 当微信发生错误时，对应的错误消息
     */
    private String errorMsg;

    public WepayException(Throwable cause) {
        super(cause);
    }

    public WepayException(String errorCode, String errorMsg){
        super("[" + errorCode + "]"+ errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
