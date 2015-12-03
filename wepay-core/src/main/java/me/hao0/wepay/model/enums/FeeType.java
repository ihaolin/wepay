package me.hao0.wepay.model.enums;

/**
 * 货币类型
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 * @since 1.0.0
 */
public enum FeeType {

    /**
     * 人民币
     */
    CNY("CNY");

    private String type;

    private FeeType(String type){
        this.type = type;
    }

    public String type(){
        return type;
    }

    public static FeeType from(String t){
        for (FeeType ft : FeeType.values()){
            if (ft.type().equals(t)){
                return ft;
            }
        }
        throw new IllegalArgumentException("unknown fee type: " + t);
    }
}
