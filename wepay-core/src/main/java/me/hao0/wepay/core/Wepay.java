package me.hao0.wepay.core;

import me.hao0.common.util.Strings;
import me.hao0.wepay.exception.WepayException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * 微信支付核心类
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 25/11/15
 * @since 1.0.0
 */
public final class Wepay {

    /**
     * 微信APP ID
     */
    private final String appId;

    /**
     * 微信APP Key
     */
    private final String appKey;

    /**
     * 商户ID
     */
    private final String mchId;

    /**
     * 商户证书数据(p12)
     */
    byte[] certs;

    /**
     * 商户证书密码，默认为商户号mchId
     */
    String certPasswd;

    /**
     * 解析微信XML时, 使用的编码类型
     */
    String respEncode = "UTF-8";

    private SSLSocketFactory sslSocketFactory;

    /**
     * 支付组件
     */
    private Pays pays;

    /**
     * 订单组件
     */
    private Orders orders;

    /**
     * 退款组件
     */
    private Refunds refunds;

    /**
     * 通知组件
     */
    private Notifies notifies;

    /**
     * 账单组件
     */
    private Bills bills;

    Wepay(String appId, String appKey, String mchId){
        this.appId = appId;
        this.appKey = appKey;
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getMchId() {
        return mchId;
    }

    public byte[] getCerts() {
        return certs;
    }

    public String getCertPasswd() {
        return certPasswd;
    }

    public String getRespEncode() {
        return respEncode;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    /**
     * 初始化操作
     * @return this
     */
    public Wepay init(){
        pays = new Pays(this);
        orders = new Orders(this);
        refunds = new Refunds(this);
        notifies = new Notifies(this);
        bills = new Bills(this);
        if (certs != null && !Strings.isNullOrEmpty(certPasswd)){
            sslSocketFactory = initSSLSocketFactory();
        }
        return this;
    }

    private SSLSocketFactory initSSLSocketFactory() {
        try (InputStream certsInput = new ByteArrayInputStream(certs)){

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(certsInput, certPasswd.toCharArray());
            certsInput.close();

            keyManagerFactory.init(keyStore, certPasswd.toCharArray());
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

            return  context.getSocketFactory();

        } catch (Exception e) {
            throw new WepayException(e);
        }
    }

    /**
     * 调用支付组件
     * @return 支付组件
     */
    public Pays pay(){
        return pays;
    }

    /**
     * 调用订单组件
     * @return 订单组件
     */
    public Orders order(){
        return orders;
    }

    /**
     * 调用退款组件
     * @return 退款组件
     */
    public Refunds refund(){
        return refunds;
    }

    /**
     * 调用通知组件
     * @return 通知组件
     */
    public Notifies notifies(){
        return notifies;
    }

    /**
     * 调用账单组件
     * @return 账单组件
     */
    public Bills bill(){
        return bills;
    }
}
