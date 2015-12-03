package me.hao0.wepay.model.pay;

import java.io.Serializable;

/**
 * 公众号支付响应对象
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 */
public class JsPayResponse implements Serializable {

    private static final long serialVersionUID = 2540820967097836895L;

    /**
     * 微信APPID
     */
    private String appId;

    /**
     * 时间戳(s)
     */
    private String timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 预支付ID串，如: prepare_id=xxx
     * 注意: JS前端调用时的字段名为package，与java关键字冲突
     */
    private String pkg;

    /**
     * 签名类型MD5
     */
    private String signType;

    /**
     * 签名
     */
    private String paySign;

    public JsPayResponse(String appId, String timeStamp, String nonceStr, String pkg, String signType, String paySign) {
        this.appId = appId;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.pkg = pkg;
        this.signType = signType;
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

    public String getSignType() {
        return signType;
    }

    public String getPaySign() {
        return paySign;
    }

    @Override
    public String toString() {
        return "JsPayResponse{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", pkg='" + pkg + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}
