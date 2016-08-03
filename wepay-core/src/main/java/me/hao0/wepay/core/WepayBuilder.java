package me.hao0.wepay.core;

/**
 * Wepay构建器
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 25/11/15
 * @since 1.0.0
 */
public final class WepayBuilder {

    private Wepay wepay;

    private WepayBuilder(){}

    public static WepayBuilder newBuilder(String appId, String appSecret, String mchId){
        WepayBuilder builder = new WepayBuilder();
        builder.wepay = new Wepay(appId, appSecret, mchId);
        return builder;
    }

    /**
     * 设置证书密码
     * @param certPasswd 证书密码
     * @return this
     */
    public WepayBuilder certPasswd(String certPasswd){
        wepay.certPasswd = certPasswd;
        return this;
    }

    /**
     * 设置证书数据(p12文件)
     * @param certs 二进制数据
     * @return this
     */
    public WepayBuilder certs(byte[] certs){
        wepay.certs = certs;
        return this;
    }

    /**
     * 设置解析微信XML时, 使用的编码类型
     * @param encode 编码类型
     * @return this
     */
    public WepayBuilder respEncode(String encode){
        wepay.respEncode = encode;
        return this;
    }

    public Wepay build(){
        return wepay.init();
    }
}
