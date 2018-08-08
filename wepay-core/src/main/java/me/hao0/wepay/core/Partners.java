package me.hao0.wepay.core;

import me.hao0.common.date.Dates;
import me.hao0.common.security.MD5;
import me.hao0.wepay.model.Partner.*;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.util.RSA;
import me.hao0.wepay.util.RandomStrs;

import javax.annotation.PostConstruct;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.TreeMap;

import static me.hao0.common.util.Preconditions.*;

/**
 * 企业相关业务组件
 * <p>
 * 用于企业向微信用户银行卡付款
 * 目前支持接口API的方式向指定微信用户的银行卡付款。
 *
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/8/7
 */
public final class Partners extends Component {

    /**
     * 企业付款到银行卡url
     */
    private static final String PAY_BANK = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank";

    /**
     * 企业付款到银行卡查询url
     */
    private static final String QUERY_BANK = "https://api.mch.weixin.qq.com/mmpaysptrans/query_bank";

    /**
     * 获取RSA公钥url
     */
    private static final String PUB_KEY = "https://fraud.mch.weixin.qq.com/risk/getpublickey";

    // TODO 随便配啦
    private static final String KEY_FILE_PATH = "";

    private static RSAPublicKey PUBLIC_KEY;

    Partners(Wepay wepay) {
        super(wepay);
    }

    /**
     * 公钥只加载一次，防止每次请求都加载
     *
     * @throws Exception
     */
    @PostConstruct
    private void loadPublicKey() throws Exception {
        RSAPublicKey publicKey;
        try {
            String publicKeyStr = RSA.loadPublicKeyByFile(KEY_FILE_PATH);
            publicKey = RSA.loadPublicKeyByStr(publicKeyStr);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        PUBLIC_KEY = publicKey;
    }


    /**
     * 企业付款到银行卡API
     *
     * @param request
     * @return
     */
    public PayBankResponse payBank(PayBankRequest request) throws Exception {
        checkPayBankParams(request);
        Map<String, Object> response = doPayBank(request);
        return buildPayBankResp(response);
    }

    /**
     * 企业付款到银行卡查询API
     *
     * @param request
     * @return
     */
    public QueryBankResponse queryBank(QueryBankRequest request) {
        checkNotNullAndEmpty(request.getPartnerTradeNo(), "partnerTradeNo");
        Map<String, Object> response = doQueryBank(request);
        return buildQueryBankResp(response);
    }

    /**
     * 获取RSA公钥API
     * <p>
     * 1、 调用获取RSA公钥API获取RSA公钥，落地成本地文件，假设为public.pem
     * 2、 确定public.pem文件的存放路径，同时修改代码中文件的输入路径，加载RSA公钥
     * 3、 用标准的RSA加密库对敏感信息进行加密，选择RSA_PKCS1_OAEP_PADDING填充模式
     * （eg：Java的填充方式要选 " RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING"）
     * 4、 得到进行rsa加密并转base64之后的密文
     * 5、 将密文传给微信侧相应字段，如付款接口（enc_bank_no/enc_true_name）
     *
     * @return
     */
    public Map<String, Object> getPublicKey() {
        Map<String, String> queryBankParams = buildGetPublicKeyParams();
        buildSignParams(queryBankParams);
        return doPost(PUB_KEY, queryBankParams);
    }


    private Map<String, Object> doPayBank(PayBankRequest request) throws Exception {
        Map<String, String> payBankParams = buildPayBankParams(request);
        buildSignParams(payBankParams);
        return doPost(PAY_BANK, payBankParams);
    }

    private Map<String, Object> doQueryBank(QueryBankRequest request) {
        Map<String, String> queryBankParams = buildQueryBankParams(request);
        buildSignParams(queryBankParams);
        return doPost(QUERY_BANK, queryBankParams);
    }


    private PayBankResponse buildPayBankResp(Map<String, Object> data) {
        String partnerId = wepay.getMchId();
        String nonceStr = RandomStrs.generate(16);
        String timeStamp = String.valueOf(Dates.now().getTime() / 1000);
        String partnerTardeNo = String.valueOf(data.get(WepayField.PARTNER_TRADE_NO));
        String amount = String.valueOf(data.get(WepayField.AMOUNT));
        String paymentNo = String.valueOf(data.get(WepayField.PAYMENT_NO));
        Integer cmmsAmt = Integer.parseInt(String.valueOf(data.get(WepayField.CMMS_AMT)));

        // 加签
        String signing =
                WepayField.MCH_ID + "=" + partnerId +
                        "&" + WepayField.NONCESTR + "=" + nonceStr +
                        "&" + WepayField.PKG + "=Sign=WXPay" +
                        "&" + WepayField.PARTNERID + "=" + partnerId +
                        "&" + WepayField.TIMESTAMP + "=" + timeStamp +

                        "&" + WepayField.PARTNER_TRADE_NO + "=" + partnerTardeNo +
                        "&" + WepayField.AMOUNT + "=" + amount +
                        "&" + WepayField.PAYMENT_NO + "=" + paymentNo +
                        "&" + WepayField.CMMS_AMT + "=" + cmmsAmt +

                        "&" + WepayField.KEY + "=" + wepay.getAppKey();

        String signed = MD5.generate(signing, false).toUpperCase();

        return new PayBankResponse(paymentNo, cmmsAmt, partnerId, partnerTardeNo, amount, nonceStr, signed);
    }


    private QueryBankResponse buildQueryBankResp(Map<String, Object> data) {
        QueryBankResponse response = new QueryBankResponse();
        response.setMchId(wepay.getMchId());
        response.setPartnerTradeNo(String.valueOf(data.get(WepayField.PARTNER_TRADE_NO)));
        response.setPaymentNo(String.valueOf(data.get(WepayField.PAYMENT_NO)));
        response.setBankNoMd5(String.valueOf(data.get(WepayField.BANK_NO_MD5))); // TODO MD5解密
        response.setTrueNameMd5(String.valueOf(data.get(WepayField.TRUE_NAME_MD5))); // TODO MD5解密
        response.setAmount(String.valueOf(data.get(WepayField.AMOUNT)));
        response.setStatus(String.valueOf(data.get(WepayField.STATUS)));
        response.setCmmsAmt(String.valueOf(data.get(WepayField.CMMS_AMT)));
        response.setCreateTime(String.valueOf(data.get(WepayField.CREATE_TIME)));
        response.setPaySuccTime(String.valueOf(data.get(WepayField.PAY_SUCC_TIME)));
        response.setReason(String.valueOf(data.get(WepayField.REASON)));
        return response;
    }


    private Map<String, String> buildPayBankParams(PayBankRequest request) throws Exception {
        Map<String, String> payBankParams = new TreeMap<>();

        // 配置参数
        buildPartnerConfigParams(payBankParams);

        /**
         * 参数加密
         */
        String encBankNo;
        String encTrueName;
        try {
            encBankNo = RSA.encrypt(PUBLIC_KEY, request.getEncBankNo().getBytes());
            encTrueName = RSA.encrypt(PUBLIC_KEY, request.getEncTrueName().getBytes());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        // 业务必需参数
        put(payBankParams, WepayField.PARTNER_TRADE_NO, request.getPartnerTradeNo());
        put(payBankParams, WepayField.NONCE_STR, RandomStrs.generate(16));
        put(payBankParams, WepayField.ENC_BANK_NO, encBankNo);// RSA加密
        put(payBankParams, WepayField.ENC_TRUE_NAME, encTrueName); // RSA加密
        put(payBankParams, WepayField.BANK_CODE, request.getBankCode());
        put(payBankParams, WepayField.AMOUNT, request.getAmount() + "");

        // 业务可选参数
        putIfNotEmpty(payBankParams, WepayField.DESC, request.getDesc());

        return payBankParams;
    }

    private Map<String, String> buildQueryBankParams(QueryBankRequest request) {

        Map<String, String> queryBankParams = new TreeMap<>();

        // 配置参数
        buildPartnerConfigParams(queryBankParams);

        // 业务必需参数
        put(queryBankParams, WepayField.PARTNER_TRADE_NO, request.getPartnerTradeNo());
        put(queryBankParams, WepayField.NONCE_STR, RandomStrs.generate(16));

        return queryBankParams;
    }

    private Map<String, String> buildGetPublicKeyParams() {

        Map<String, String> getPublicKeyParams = new TreeMap<>();

        // 配置参数
        buildPartnerConfigParams(getPublicKeyParams);

        // 业务必需参数
        put(getPublicKeyParams, WepayField.NONCE_STR, RandomStrs.generate(16));

        return getPublicKeyParams;
    }

    private void checkPayBankParams(PayBankRequest request) {
        checkNotNull(request, "pay bank request can't be null");
        checkNotNullAndEmpty(request.getEncBankNo(), "encBankNo");
        checkNotNullAndEmpty(request.getEncTrueName(), "encTrueName");
        Integer totalFee = request.getAmount();
        checkArgument(totalFee != null && totalFee > 0, "totalFee must > 0");
        checkNotNullAndEmpty(request.getBankCode(), "BankCode");

    }


}
