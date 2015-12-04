package me.hao0.wepay.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.hao0.wepay.model.enums.RefundChannel;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.serializer.RefundChannelDeserializer;

/**
 * 普通账单
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 4/12/15
 * @since 1.1.0
 */
public class CommonBill extends Bill {

    private static final long serialVersionUID = -4518299029269484159L;

    /**
     * 微信退款单号
     */
    @JsonProperty(WepayField.REFUND_ID)
    private String refundId;

    /**
     * 商户退款单号
     */
    @JsonProperty(WepayField.OUT_REFUND_NO)
    private String outRefundNo;

    /**
     * 退款金额(元)
     */
    @JsonProperty(WepayField.REFUND_FEE)
    private Float refundFee;

    /**
     * 企业红包退款金额(元)
     */
    @JsonProperty(WepayField.ENTER_RED_PKG_REFUND_FEE)
    private Float enterRedPkgRefundFee;

    /**
     * 退款类型
     */
    @JsonProperty(WepayField.REFUND_CHANNEL)
    @JsonDeserialize(using = RefundChannelDeserializer.class)
    private RefundChannel channel;

    /**
     * 退款状态
     */
    @JsonProperty(WepayField.REFUND_STATUS)
    private String refundStatus;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public Float getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Float refundFee) {
        this.refundFee = refundFee;
    }

    public Float getEnterRedPkgRefundFee() {
        return enterRedPkgRefundFee;
    }

    public void setEnterRedPkgRefundFee(Float enterRedPkgRefundFee) {
        this.enterRedPkgRefundFee = enterRedPkgRefundFee;
    }

    public RefundChannel getChannel() {
        return channel;
    }

    public void setChannel(RefundChannel channel) {
        this.channel = channel;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    @Override
    public String toString() {
        return "CommonBill{" +
                "refundId='" + refundId + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", refundFee=" + refundFee +
                ", enterRedPkgRefundFee=" + enterRedPkgRefundFee +
                ", channel=" + channel +
                ", refundStatus='" + refundStatus + '\'' +
                "} " + super.toString();
    }
}
