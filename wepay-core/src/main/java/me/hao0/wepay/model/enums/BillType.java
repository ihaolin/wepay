package me.hao0.wepay.model.enums;

/**
 * 账单类型
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 4/12/15
 * @since 1.1.0
 */
public enum BillType {

    /**
     * 所有订单信息
     */
    ALL("ALL"),

    /**
     * 成功支付的订单
     */
    SUCCESS("SUCCESS"),

    /**
     * 退款订单
     */
    REFUND("REFUND");

    private String type;

    private BillType(String type){
        this.type = type;
    }

    public String type(){
        return type;
    }

    public static BillType from(String t){
        for (BillType bt : BillType.values()){
            if (bt.type().equals(t)){
                return bt;
            }
        }
        throw new IllegalArgumentException("unknown bill type: " + t);
    }

}
