package me.hao0.wepay.model.pay;

import java.io.Serializable;

/**
 * 二维码支付(NATIVE)响应结果
 * Author: haolin
 * Email: haolin.h0@gmail.com
 */
public class QrPayResponse implements Serializable {

    private static final long serialVersionUID = 1383835653965951921L;

    /**
     * 预支付ID
     */
    private String prepayId;

    /**
     * 支付二维码链接
     */
    private String codeUrl;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "QrPayResponse{" +
                "prepayId='" + prepayId + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                '}';
    }
}

