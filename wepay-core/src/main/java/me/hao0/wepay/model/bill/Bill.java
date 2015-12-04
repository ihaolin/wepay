package me.hao0.wepay.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.hao0.wepay.model.enums.FeeType;
import me.hao0.wepay.model.enums.TradeType;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.serializer.FeeTypeDeserializer;
import me.hao0.wepay.serializer.TradeTypeDeserializer;
import java.io.Serializable;

/**
 * 交易成功账单
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 4/12/15
 * @since 1.1.0
 */
public class Bill implements Serializable {

    private static final long serialVersionUID = 2385619717854141289L;

    /**
     * 交易时间
     */
    @JsonProperty(WepayField.TRADE_TIME)
    private String tradeTime;

    /**
     * 公众帐号ID
     */
    @JsonProperty(WepayField.APP_ID)
    private String appId;

    /**
     * 商户ID
     */
    @JsonProperty(WepayField.MCH_ID)
    private String mchId;

    /**
     * 子商户ID
     */
    @JsonProperty(WepayField.SUB_MCH_ID)
    private String subMchId;

    /**
     * 设备号
     */
    @JsonProperty(WepayField.DEVICE_INFO)
    private String deviceInfo;

    /**
     * 微信订单号
     */
    @JsonProperty(WepayField.TRANSACTION_ID)
    private String transactionId;

    /**
     * 商户订单号
     */
    @JsonProperty(WepayField.OUT_TRADE_NO)
    private String outTradeNo;

    /**
     * 用户标识
     */
    @JsonProperty(WepayField.OPEN_ID)
    private String openId;

    /**
     * 交易类型
     */
    @JsonProperty(WepayField.TRADE_TYPE)
    @JsonDeserialize(using = TradeTypeDeserializer.class)
    private TradeType tradeType;

    /**
     * 交易状态
     */
    @JsonProperty(WepayField.TRADE_STATE)
    private String tradeState;

    /**
     * 付款银行
     */
    @JsonProperty(WepayField.BANK_TYPE)
    private String bankType;

    /**
     * 货币类型
     */
    @JsonProperty(WepayField.FEE_TYPE)
    @JsonDeserialize(using = FeeTypeDeserializer.class)
    private FeeType feeType;

    /**
     * 总金额
     */
    @JsonProperty(WepayField.TOTAL_FEE)
    private Float totalFee;

    /**
     * 企业红包金额
     */
    @JsonProperty(WepayField.ENTER_RED_PKG_FEE)
    private Float enterRedPkgFee;

    /**
     * 商品名称
     */
    private String body;

    /**
     * 商户数据包
     */
    @JsonProperty(WepayField.DATA_PKG)
    private String dataPkg;

    /**
     * 手续费
     */
    @JsonProperty(WepayField.COMMISSION_FEE)
    private String commissionFee;

    /**
     * 费率
     */
    @JsonProperty(WepayField.FEE_RATE)
    private String feeRate;

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDataPkg() {
        return dataPkg;
    }

    public void setDataPkg(String dataPkg) {
        this.dataPkg = dataPkg;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public Float getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Float totalFee) {
        this.totalFee = totalFee;
    }

    public Float getEnterRedPkgFee() {
        return enterRedPkgFee;
    }

    public void setEnterRedPkgFee(Float enterRedPkgFee) {
        this.enterRedPkgFee = enterRedPkgFee;
    }

    public String getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(String commissionFee) {
        this.commissionFee = commissionFee;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "tradeTime='" + tradeTime + '\'' +
                ", appId='" + appId + '\'' +
                ", mchId='" + mchId + '\'' +
                ", subMchId='" + subMchId + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", openId='" + openId + '\'' +
                ", tradeType=" + tradeType +
                ", tradeState='" + tradeState + '\'' +
                ", bankType='" + bankType + '\'' +
                ", feeType=" + feeType +
                ", totalFee=" + totalFee +
                ", enterRedPkgFee=" + enterRedPkgFee +
                ", body='" + body + '\'' +
                ", dataPkg='" + dataPkg + '\'' +
                ", commissionFee=" + commissionFee +
                ", feeRate='" + feeRate + '\'' +
                '}';
    }
}


