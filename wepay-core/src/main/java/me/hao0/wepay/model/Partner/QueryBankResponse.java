package me.hao0.wepay.model.Partner;

/**
 * 企业付款到银行卡查询响应对象
 *
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/8/7
 */
public class QueryBankResponse {

    private String mchId;

    private String partnerTradeNo;

    private String paymentNo;

    private String bankNoMd5;

    private String trueNameMd5;

    private String amount;

    private String status;

    private String cmmsAmt;

    private String createTime;

    private String paySuccTime;

    private String reason;


    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getBankNoMd5() {
        return bankNoMd5;
    }

    public void setBankNoMd5(String bankNoMd5) {
        this.bankNoMd5 = bankNoMd5;
    }

    public String getTrueNameMd5() {
        return trueNameMd5;
    }

    public void setTrueNameMd5(String trueNameMd5) {
        this.trueNameMd5 = trueNameMd5;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCmmsAmt() {
        return cmmsAmt;
    }

    public void setCmmsAmt(String cmmsAmt) {
        this.cmmsAmt = cmmsAmt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPaySuccTime() {
        return paySuccTime;
    }

    public void setPaySuccTime(String paySuccTime) {
        this.paySuccTime = paySuccTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "QueryBankResponse{" +
                "mchId='" + mchId + '\'' +
                ", partnerTradeNo='" + partnerTradeNo + '\'' +
                ", paymentNo='" + paymentNo + '\'' +
                ", bankNoMd5='" + bankNoMd5 + '\'' +
                ", trueNameMd5='" + trueNameMd5 + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                ", cmmsAmt='" + cmmsAmt + '\'' +
                ", createTime='" + createTime + '\'' +
                ", paySuccTime='" + paySuccTime + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
