package me.hao0.wepay.core;

import me.hao0.common.date.Dates;
import me.hao0.common.security.MD5;
import me.hao0.wepay.exception.WepayException;
import me.hao0.wepay.model.enums.TradeType;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.model.pay.AppPayResponse;
import me.hao0.wepay.model.pay.JsPayRequest;
import me.hao0.wepay.model.pay.JsPayResponse;
import me.hao0.wepay.model.pay.PayRequest;
import me.hao0.wepay.model.pay.QrPayRequest;
import me.hao0.wepay.util.RandomStrs;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import static me.hao0.common.util.Preconditions.*;

/**
 * 支付组件
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 * @since 1.0.0
 */
public final class Pays extends Component {

    /**
     * 统一下单接口
     */
    private static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 联图二维码转换
     */
    private static final String LIANTU_URL = "http://qr.liantu.com/api.php?text=";

    protected Pays(Wepay wepay) {
        super(wepay);
    }

    /**
     * JS支付(公众号支付)
     * @param request 支付请求对象
     * @return JsPayResponse对象，或抛WepayException
     */
    public JsPayResponse jsPay(JsPayRequest request){
        checkJsPayParams(request);
        Map<String, Object> respData = doJsPay(request, TradeType.JSAPI);
        return buildJsPayResp(respData);
    }

    /**
     * 动态二维码支付(NATIVE)
     * @param request 支付请求对象
     * @return 使用联图生成的二维码链接，或抛WepayException
     */
    public String qrPay(QrPayRequest request){
        return qrPay(request, Boolean.TRUE);
    }

    /**
     * 动态二维码支付(NATIVE)
     * @param request 支付请求对象
     * @param convert 是否转换为二维码图片链接(使用联图)
     * @return 可访问的二维码链接，或抛WepayException
     */
    public String qrPay(QrPayRequest request, Boolean convert){
        checkPayParams(request);
        Map<String, Object> respData = doQrPay(request, TradeType.NATIVE);
        String codeUrl = String.valueOf(respData.get(WepayField.CODE_URL));
        if (convert){
            try {
                return LIANTU_URL + URLEncoder.encode(codeUrl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new WepayException(e);
            }
        }
        return codeUrl;
    }

    /**
     * app支付
     * @param request 支付请求对象
     * @return AppPayResponse对象，或抛WepayException
     */
    public AppPayResponse appPay(PayRequest request){
        checkPayParams(request);
        Map<String, Object> respData = doAppPay(request, TradeType.APP);
        return buildAppPayResp(respData);
    }

    /**
     * JS支付
     * @param request 支付信息
     * @return 支付结果
     */
    private Map<String, Object> doJsPay(JsPayRequest request, TradeType tradeType){
        Map<String, String> payParams = buildPayParams(request, tradeType);
        payParams.put(WepayField.OPEN_ID, request.getOpenId());
        return doPay(payParams);
    }

    /**
     * APP支付
     * @param request 支付信
     * @return 支付结
     */
    private Map<String, Object> doAppPay(PayRequest request, TradeType tradeType){
        Map<String, String> payParams = buildPayParams(request, tradeType);
        return doPay(payParams);
    }

    /**
     * 二维码支付
     * @param request 支付信息
     * @return 支付结果
     */
    private Map<String, Object> doQrPay(QrPayRequest request, TradeType tradeType){
        Map<String, String> payParams = buildPayParams(request, tradeType);
        putIfNotEmpty(payParams, WepayField.PRODUCT_ID, request.getProductId());
        return doPay(payParams);
    }

    private Map<String, Object> doPay(Map<String, String> payParams) {
        buildSignParams(payParams);
        return doPost(PAY_URL, payParams);
    }

    private JsPayResponse buildJsPayResp(Map<String, Object> data) {

        String appId = wepay.getAppId();
        String nonceStr = RandomStrs.generate(16);
        String timeStamp = String.valueOf(Dates.now().getTime() / 1000);
        String pkg = WepayField.PREPAY_ID + "=" +
                data.get(WepayField.PREPAY_ID);

        String signing =
                WepayField.APPID + "=" + appId +
                "&"+ WepayField.NONCESTR2 +"=" + nonceStr +
                "&" + WepayField.PKG + "=" + pkg +
                "&" + WepayField.SIGN_TYPE + "=MD5" +
                "&" + WepayField.TIME_STAMP + "=" + timeStamp +
                "&" + WepayField.KEY + "=" + wepay.getAppKey();

        String signed = MD5.generate(signing, false).toUpperCase();

        return new JsPayResponse(appId, timeStamp, nonceStr, pkg, "MD5", signed);
    }

    private AppPayResponse buildAppPayResp(Map<String, Object> data) {
        String appId = wepay.getAppId();
        String partnerId= wepay.getMchId();
        String nonceStr = RandomStrs.generate(16);
        String timeStamp = String.valueOf(Dates.now().getTime() / 1000);
        String prepayId = String.valueOf(data.get(WepayField.PREPAY_ID));

        String signing =
                WepayField.APP_ID + "=" + appId +
                "&"+ WepayField.NONCESTR +"=" + nonceStr +
                "&" + WepayField.PKG + "=Sign=WXPay" +
                "&" + WepayField.PARTNERID + "=" + partnerId +
                "&" + WepayField.PREPAYID + "=" + prepayId +
                "&" + WepayField.TIMESTAMP + "=" + timeStamp +
                "&" + WepayField.KEY + "=" + wepay.getAppKey();

        String signed = MD5.generate(signing, false).toUpperCase();

        return new AppPayResponse(appId, partnerId, prepayId, timeStamp, nonceStr, signed);
    }

    /**
     * 检查支付参数合法性
     * @param request 支付请求对象
     */
    private void checkJsPayParams(JsPayRequest request) {
        checkPayParams(request);
        checkNotNullAndEmpty(request.getOpenId(), "openId");
    }

    private void checkPayParams(PayRequest request) {
        checkNotNull(request, "pay detail can't be null");
        checkNotNullAndEmpty(request.getBody(), "body");
        checkNotNullAndEmpty(request.getOutTradeNo(), "outTradeNo");
        Integer totalFee = request.getTotalFee();
        checkArgument(totalFee != null && totalFee > 0, "totalFee must > 0");
        checkNotNullAndEmpty(request.getClientId(), "clientId");
        checkNotNullAndEmpty(request.getNotifyUrl(), "notifyUrl");
        checkNotNull(request.getFeeType(), "feeType can't be null");
        checkNotNullAndEmpty(request.getTimeStart(), "timeStart");
    }

    /**
     * 构建公共支付参数
     * @param request 支付请求对象
     * @param tradeType 交易类型
     * @return 支付MAP参数
     */
    private Map<String, String> buildPayParams(PayRequest request, TradeType tradeType) {
        Map<String, String> payParams = new TreeMap<>();

        // 配置参数
        buildConfigParams(payParams);

        // 业务必需参数
        put(payParams, WepayField.BODY, request.getBody());
        put(payParams, WepayField.OUT_TRADE_NO, request.getOutTradeNo());
        put(payParams, WepayField.TOTAL_FEE, request.getTotalFee() + "");
        put(payParams, WepayField.SPBILL_CREATE_IP, request.getClientId());
        put(payParams, WepayField.NOTIFY_URL, request.getNotifyUrl());
        put(payParams, WepayField.FEE_TYPE, request.getFeeType().type());
        put(payParams, WepayField.NONCE_STR, RandomStrs.generate(16));
        put(payParams, WepayField.TIME_START, request.getTimeStart());
        put(payParams, WepayField.TRADE_TYPE, tradeType.type());

        // 业务可选参数
        putIfNotEmpty(payParams, WepayField.DEVICE_INFO, request.getDeviceInfo());
        putIfNotEmpty(payParams, WepayField.ATTACH, request.getAttach());
        putIfNotEmpty(payParams, WepayField.DETAIL, request.getDetail());
        putIfNotEmpty(payParams, WepayField.GOODS_TAG, request.getGoodsTag());
        putIfNotEmpty(payParams, WepayField.TIME_EXPIRE, request.getTimeExpire());
        putIfNotEmpty(payParams, WepayField.LIMIT_PAY, request.getLimitPay());

        return payParams;
    }
}
