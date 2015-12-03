package me.hao0.wepay.model.refund;

import me.hao0.wepay.model.enums.FeeType;

import java.io.Serializable;
import java.util.List;

/**
 * 退款查询对象
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 1/12/15
 * @since 1.0.0
 */
public class RefundQueryResponse implements Serializable {

    private static final long serialVersionUID = -3559898607397949643L;

    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 总金额
     */
    private Integer totalFee;

    /**
     * 货币类型
     */
    private FeeType feeType;

    /**
     * 现金支付金额
     */
    private Integer cashFee;

    /**
     * 退款项
     */
    private List<RefundItem> items;

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

    public List<RefundItem> getItems() {
        return items;
    }

    public void setItems(List<RefundItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "RefundQueryResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalFee=" + totalFee +
                ", feeType=" + feeType +
                ", cashFee=" + cashFee +
                ", items=" + items +
                '}';
    }
}
