package me.hao0.wepay.demo.support;

import com.google.common.io.ByteStreams;
import me.hao0.common.date.Dates;
import me.hao0.wepay.core.Wepay;
import me.hao0.wepay.core.WepayBuilder;
import me.hao0.wepay.model.pay.QrPayRequest;
import me.hao0.wepay.model.refund.RefundApplyRequest;
import me.hao0.wepay.model.refund.RefundApplyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Map;

/**
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 2/12/15
 */
@Component
public class WepaySupport {

    @Value("${appId}")
    private String appId;

    @Value("${appKey}")
    private String appKey;

    @Value("${mchId}")
    private String mchId;

    @Value("${payNotifyUrl}")
    private String payNotifyUrl;

    private Wepay wepay;

    @PostConstruct
    public void initWepay() {
        try(InputStream in = this.getClass().getClassLoader().getResourceAsStream("cert.p12")) {
            // 加载证书文件
            byte[] certs = ByteStreams.toByteArray(in);
            wepay = WepayBuilder.newBuilder(appId, appKey, mchId)
                    .certPasswd(mchId)
                    .certs(certs)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 动态二维码支付
     * @param orderNumber 订单号
     * @return 二维码链接
     */
    public String qrPay(String orderNumber){
        QrPayRequest request = new QrPayRequest();
        request.setBody("测试订单");
        request.setClientId("127.0.0.1");
        request.setTotalFee(1);
        request.setNotifyUrl(payNotifyUrl);
        request.setOutTradeNo(orderNumber);
        request.setTimeStart(Dates.now("yyyyMMddHHmmss"));
        return wepay.pay().qrPay(request);
    }

    /**
     * 校验签名
     * @param params 参数(包含sign)
     * @return 校验成功返回true，反之false
     */
    public Boolean verifySign(Map<String, ?> params){
        return wepay.notifies().verifySign(params);
    }

    /**
     * 通知成功
     */
    public String notifyOk(){
        return wepay.notifies().ok();
    }

    /**
     * 通知不成功
     * @param errMsg 错误消息
     */
    public String notifyNotOk(String errMsg){
        return wepay.notifies().notOk(errMsg);
    }

    public RefundApplyResponse refundApply(String orderNumber){
        RefundApplyRequest req = new RefundApplyRequest();
        req.setOutTradeNo(orderNumber);
        req.setOutRefundNo(orderNumber);
        req.setTotalFee(1);
        req.setRefundFee(1);
        req.setOpUserId(wepay.getMchId());
        return wepay.refund().apply(req);
    }
}
