package me.hao0.wepay.model.Partner;

import java.io.Serializable;

/**
 * 企业付款到银行卡请求对象
 *
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/8/7
 */
public class PayBankRequest implements Serializable {

    /**
     * 商户企业付款单号
     */
    private String partnerTradeNo;

    /**
     * 收款方银行卡号
     */
    private String encBankNo;

    /**
     * 收款方用户名
     */
    private String encTrueName;

    /**
     * 收款方开户行
     */
    private String bankCode;

    /**
     * 付款金额
     */
    private Integer amount;

    /**
     * 付款说明
     */
    private String desc;


    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getEncBankNo() {
        return encBankNo;
    }

    public void setEncBankNo(String encBankNo) {
        this.encBankNo = encBankNo;
    }

    public String getEncTrueName() {
        return encTrueName;
    }

    public void setEncTrueName(String encTrueName) {
        this.encTrueName = encTrueName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "PayBankRequest{" +
                "partnerTradeNo='" + partnerTradeNo + '\'' +
                ", encBankNo='" + encBankNo + '\'' +
                ", encTrueName='" + encTrueName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", amount=" + amount +
                ", desc='" + desc + '\'' +
                '}';
    }
}
