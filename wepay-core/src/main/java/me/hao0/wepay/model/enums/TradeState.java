package me.hao0.wepay.model.enums;

/**
 * 交易状态
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 * @since 1.0.0
 */
public enum TradeState {

    /**
     * 支付成功
     */
    SUCCESS("SUCCESS"),

    /**
     * 转入退款
     */
    REFUND("REFUND"),

    /**
     * 未支付
     */
    NOTPAY("NOTPAY"),

    /**
     * 已关闭
     */
    CLOSED("CLOSED"),

    /**
     * 已撤销（刷卡支付）
     */
    REVOKED("REVOKED"),

    /**
     * 已撤销（刷卡支付）
     */
    USERPAYING("USERPAYING"),

    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    PAYERROR("PAYERROR");

    private String type;

    private TradeState(String type){
        this.type = type;
    }

    public String type(){
        return type;
    }

    public static TradeState from(String s){
        for (TradeState tt : TradeState.values()){
            if (tt.type().equals(s)){
                return tt;
            }
        }
        throw new IllegalArgumentException("unknown trade type: " + s);
    }
}
