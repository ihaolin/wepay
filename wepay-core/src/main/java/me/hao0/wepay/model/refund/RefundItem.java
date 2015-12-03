package me.hao0.wepay.model.refund;

import me.hao0.wepay.model.common.Coupon;
import me.hao0.wepay.model.enums.RefundChannel;

import java.io.Serializable;
import java.util.List;

/**
 * 单笔退款
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 1/12/15
 * @since 1.0.0
 */
public class RefundItem implements Serializable {

    private static final long serialVersionUID = -8803509387441693049L;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    private String refundId;

    /**
     * 退款渠道
     */
    private RefundChannel channel;

    /**
     * 退款金额
     */
    private Integer refundFee;

    /**
     * 代金券或立减优惠退款金额
     */
    private Integer couponRefundFee;

    /**
     * 代金券或立减优惠退款项
     */
    private List<Coupon> coupons;

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

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "RefundItem{" +
                "outRefundNo='" + outRefundNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", channel=" + channel +
                ", refundFee=" + refundFee +
                ", couponRefundFee=" + couponRefundFee +
                ", coupons=" + coupons +
                '}';
    }
}
