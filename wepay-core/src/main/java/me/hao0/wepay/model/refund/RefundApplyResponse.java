package me.hao0.wepay.model.refund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.hao0.wepay.annotation.Optional;
import me.hao0.wepay.model.enums.FeeType;
import me.hao0.wepay.model.enums.RefundChannel;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.serializer.FeeTypeDeserializer;
import me.hao0.wepay.serializer.RefundChannelDeserializer;

import java.io.Serializable;

/**
 * 退款结果
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 28/11/15
 * @since 1.0.0
 */
public class RefundApplyResponse implements Serializable {

    private static final long serialVersionUID = -8303581191923588820L;

    /**
     * 设备号
     */
    @Optional
    @JsonProperty(WepayField.DEVICE_INFO)
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @JsonProperty(WepayField.NONCE_STR)
    private String nonceStr;

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
     * 商户退款单号
     */
    @JsonProperty(WepayField.OUT_REFUND_NO)
    private String outRefundNo;

    /**
     * 微信退款但号
     */
    @JsonProperty(WepayField.REFUND_ID)
    private String refundId;

    /**
     * 退款渠道
     */
    @JsonProperty(WepayField.REFUND_CHANNEL)
    @JsonDeserialize(using = RefundChannelDeserializer.class)
    private RefundChannel channel;

    /**
     * 退款金额
     */
    @JsonProperty(WepayField.REFUND_FEE)
    private Integer refundFee;

    /**
     * 订单总金额
     */
    @JsonProperty(WepayField.TOTAL_FEE)
    private Integer totalFee;

    /**
     * 货币类型
     */
    @JsonProperty(WepayField.FEE_TYPE)
    @JsonDeserialize(using = FeeTypeDeserializer.class)
    private FeeType feeType;

    /**
     * 现金支付金额
     */
    @JsonProperty(WepayField.CASH_FEE)
    private Integer cashFee;

    /**
     * 现金退款金额
     */
    @JsonProperty(WepayField.CASH_REFUND_FEE)
    private Integer cashRefundFee;

    /**
     * 代金券或立减优惠退款金额
     */
    @JsonProperty(WepayField.COUPON_REFUND_FEE)
    private Integer couponRefundFee;

    /**
     * 代金券或立减优惠使用数量
     */
    @JsonProperty(WepayField.COUPON_REFUND_COUNT)
    private Integer couponRefundCount;

    /**
     * 代金券或立减优惠ID
     */
    @JsonProperty(WepayField.COUPON_REFUND_ID)
    private Integer couponRefundId;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
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

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public RefundChannel getChannel() {
        return channel;
    }

    public void setChannel(RefundChannel channel) {
        this.channel = channel;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
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

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public Integer getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(Integer cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    public Integer getCouponRefundId() {
        return couponRefundId;
    }

    public void setCouponRefundId(Integer couponRefundId) {
        this.couponRefundId = couponRefundId;
    }

    @Override
    public String toString() {
        return "RefundResponse{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", channel=" + channel +
                ", refundFee=" + refundFee +
                ", totalFee=" + totalFee +
                ", feeType=" + feeType +
                ", cashFee=" + cashFee +
                ", cashRefundFee=" + cashRefundFee +
                ", couponRefundFee=" + couponRefundFee +
                ", couponRefundCount=" + couponRefundCount +
                ", couponRefundId=" + couponRefundId +
                '}';
    }
}
