package me.hao0.wepay.model.pay;

import java.io.Serializable;

/**
 * H5支付响应对象
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/25.
 */
public class H5PayResponse implements Serializable {

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
     * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
     */
    private String mwebUrl;

    /**
     * 预支付ID
     */
    private String prepayId;

    /**
     * 签名
     */
    private String paySign;

    private String returnCode;

    private String returnMsg;

    private String resultCode;

    private String errCode;

    private String errCodeDes;

    public H5PayResponse(String appId, String timeStamp, String nonceStr, String pkg, String signType,
                         String paySign, String returnCode, String resultCode, String errCode,
                         String errCodeDes, String returnMsg, String prepayId, String mwebUrl) {
        this.appId = appId;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.pkg = pkg;
        this.signType = signType;
        this.paySign = paySign;
        this.returnCode = returnCode;
        this.resultCode = resultCode;
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.returnMsg = returnMsg;
        this.prepayId = prepayId;
        this.mwebUrl = mwebUrl;

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getMwebUrl() {
        return mwebUrl;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getPaySign() {
        return paySign;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
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
                ", returnCode='" + returnCode + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errCodeDes='" + errCodeDes + '\'' +
                '}';
    }
}
