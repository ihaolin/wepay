package me.hao0.wepay.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.hao0.wepay.model.common.Coupon;
import me.hao0.wepay.model.enums.FeeType;
import me.hao0.wepay.model.enums.TradeState;
import me.hao0.wepay.model.enums.TradeType;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.serializer.BooleanDeserializer;
import me.hao0.wepay.serializer.DateDeserializer;
import me.hao0.wepay.serializer.FeeTypeDeserializer;
import me.hao0.wepay.serializer.TradeStateDeserializer;
import me.hao0.wepay.serializer.TradeTypeDeserializer;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 微信查询订单对象
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 27/11/15
 * @since 1.0.0
 */
public class WePayOrder implements Serializable {

    private static final long serialVersionUID = -3808893700552515824L;

    /**
     * 用户openId
     */
    @JsonProperty(WepayField.OPEN_ID)
    private String openId;

    /**
     * 用户是否关注公众号
     */
    @JsonProperty(WepayField.IS_SUBSCRIBE)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private Boolean subscribe;

    /**
     * 交易类型
     */
    @JsonProperty(WepayField.TRADE_TYPE)
    @JsonDeserialize(using = TradeTypeDeserializer.class)
    private TradeType tradeType;

    /**
     * 银行类型
     */
    @JsonProperty(WepayField.BANK_TYPE)
    private String bankType;

    /**
     * 总金额
     */
    @JsonProperty(WepayField.TOTAL_FEE)
    private Integer totalFee;

    @JsonProperty(WepayField.FEE_TYPE)
    @JsonDeserialize(using = FeeTypeDeserializer.class)
    private FeeType feeType;

    /**
     * 微信订单好
     */
    @JsonProperty(WepayField.TRANSACTION_ID)
    private String transactionId;

    /**
     * 商户订单号
     */
    @JsonProperty(WepayField.OUT_TRADE_NO)
    private String outTradeNo;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 支付完成时间
     */
    @JsonProperty(WepayField.TIME_END)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date timeEnd;

    /**
     * 交易状态
     */
    @JsonProperty(WepayField.TRADE_STATE)
    @JsonDeserialize(using = TradeStateDeserializer.class)
    private TradeState tradeState;

    /**
     * 交易状态描述
     */
    @JsonProperty(WepayField.TRADE_STATE_DESC)
    private String tradeStateDesc;

    /**
     * 现金支付金额
     */
    @JsonProperty(WepayField.CASH_FEE)
    private Integer cachFee;

    @JsonProperty(WepayField.CASH_FEE_TYPE)
    @JsonDeserialize(using = FeeTypeDeserializer.class)
    private FeeType cashFeeType;

    /**
     * 设备号
     */
    @JsonProperty(WepayField.DEVICE_INFO)
    private String deviceInfo;

    /**
     * 代金券或立减优惠金额(分)
     */
    @JsonProperty(WepayField.COUPON_FEE)
    private Integer couponFee;

    /**
     * 代金券或立减优惠使用数量
     */
    @JsonProperty(WepayField.COUPON_COUNT)
    private Integer couponCount;

    /**
     * 代金券或立减优惠明细
     */
    @JsonIgnore
    private List<Coupon> coupons;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public TradeState getTradeState() {
        return tradeState;
    }

    public void setTradeState(TradeState tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }

    public Integer getCachFee() {
        return cachFee;
    }

    public void setCachFee(Integer cachFee) {
        this.cachFee = cachFee;
    }

    public FeeType getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(FeeType cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "WePayOrder{" +
                "openId='" + openId + '\'' +
                ", subscribe=" + subscribe +
                ", tradeType=" + tradeType +
                ", bankType='" + bankType + '\'' +
                ", totalFee=" + totalFee +
                ", feeType=" + feeType +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", attach='" + attach + '\'' +
                ", timeEnd=" + timeEnd +
                ", tradeState=" + tradeState +
                ", tradeStateDesc='" + tradeStateDesc + '\'' +
                ", cachFee=" + cachFee +
                ", cashFeeType=" + cashFeeType +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", couponFee=" + couponFee +
                ", couponCount=" + couponCount +
                ", coupons=" + coupons +
                '}';
    }
}
