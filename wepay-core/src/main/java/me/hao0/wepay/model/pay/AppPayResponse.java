package me.hao0.wepay.model.pay;

import java.io.Serializable;

/**
 * 公众号支付响应对象
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 */
public class AppPayResponse implements Serializable {

    private static final long serialVersionUID = 2540820967097836895L;

    /**
     * 微信APPID
     */
    private String appId;

    /**
     * 商户Id
     */
    private String partnerId;

    /**
     * 预支付ID
     */
    private String prepayId;

    /**
     * 时间戳(s)
     */
    private String timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * package
     */
    private String pkg = "Sign=WXPay";

    /**
     * 签名
     */
    private String paySign;

    public AppPayResponse(String appId, String partnerId, String prepayId, String timeStamp, String nonceStr, String paySign) {
        this.appId = appId;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.paySign = paySign;
    }

    public String getAppId() {
        return appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getPkg() {
        return pkg;
    }

    public String getPaySign() {
        return paySign;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    @Override
    public String toString() {
        return "AppPayResponse{" +
                "appId='" + appId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", pkg='" + pkg + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}
