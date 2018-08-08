package me.hao0.wepay.model.Partner;

/**
 * 企业付款到银行卡响应对象
 *
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/8/7
 */
public class PayBankResponse {

    /**
     * 微信企业付款单号
     */
    private String payment_no;

    /**
     * 手续费金额
     */
    private Integer cmms_amt;

    /**
     * 商户Id
     */
    private String partnerId;

    /**
     * 商户企业付款单号
     */
    private String partnerTradeNo;

    /**
     * 代付金额
     */
    private String amount;

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


    public PayBankResponse(String payment_no, Integer cmms_amt, String partnerId, String partnerTradeNo, String amount, String nonceStr, String paySign) {
        this.payment_no = payment_no;
        this.cmms_amt = cmms_amt;
        this.partnerId = partnerId;
        this.partnerTradeNo = partnerTradeNo;
        this.amount = amount;
        this.nonceStr = nonceStr;
        this.paySign = paySign;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public Integer getCmms_amt() {
        return cmms_amt;
    }

    public void setCmms_amt(Integer cmms_amt) {
        this.cmms_amt = cmms_amt;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    @Override
    public String toString() {
        return "PayBankResponse{" +
                "payment_no='" + payment_no + '\'' +
                ", cmms_amt=" + cmms_amt +
                ", partnerId='" + partnerId + '\'' +
                ", partnerTradeNo='" + partnerTradeNo + '\'' +
                ", amount='" + amount + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", pkg='" + pkg + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}
