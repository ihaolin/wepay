package me.hao0.wepay.model.bill;

import me.hao0.wepay.model.enums.WepayField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账单字段
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 4/12/15
 * @since 1.1.0
 */
public final class BillFields {

    /**
     * 账单公用起始字段
     */
    private static final List<String> START_FIELDS = Arrays.asList(
        WepayField.TRADE_TIME, WepayField.APP_ID, WepayField.MCH_ID, WepayField.SUB_MCH_ID,
        WepayField.DEVICE_INFO, WepayField.TRANSACTION_ID, WepayField.OUT_TRADE_NO, WepayField.OPEN_ID,
        WepayField.TRADE_TYPE, WepayField.TRADE_STATE, WepayField.BANK_TYPE, WepayField.FEE_TYPE,
        WepayField.TOTAL_FEE, WepayField.ENTER_RED_PKG_FEE
    );

    private static final List<String> END_FIELDS = Arrays.asList(
        WepayField.BODY, WepayField.DATA_PKG, WepayField.COMMISSION_FEE, WepayField.FEE_RATE
    );

    /**
     * 所有订单账单的字段集，顺序与微信返回数据保持一致
     */
    public static final List<String> ALL = initAllFields();

    /**
     * 退款账单的字段集合，顺序与微信返回数据保持一致
     */
    public static final List<String> REFUND = initRefundFields();

    /**
     * 成功账单的字段集合，顺序与微信返回数据保持一致
     */
    public static final List<String> SUCCESS = initSuccessFields();

    /**
     * 账单统计的字段集合，顺序与微信返回数据保持一致
     */
    public static final List<String> COUNT = Arrays.asList(
        WepayField.TRADE_TOTAL_COUNT, WepayField.TRADE_TOTAL_FEE,
        WepayField.REFUND_TOTAL_FEE, WepayField.COUPON_REFUND_TOTAL_FEE,
        WepayField.COMMISSION_TOTAL_FEE
    );


    private BillFields(){}

    private static List<String> initAllFields() {
        List<String> all = new ArrayList<>();
        startFields(all);
        initCommonRefundFields(all);
        endFields(all);
        return all;
    }

    private static List<String> initRefundFields() {
        List<String> refund = new ArrayList<>();
        startFields(refund);
        refund.add(WepayField.REFUND_APPLY_TIME);
        refund.add(WepayField.REFUND_SUCCESS_TIME);
        initCommonRefundFields(refund);
        endFields(refund);
        return refund;
    }

    private static void initCommonRefundFields(List<String> fields){
        fields.add(WepayField.REFUND_ID);
        fields.add(WepayField.OUT_REFUND_NO);
        fields.add(WepayField.REFUND_FEE);
        fields.add(WepayField.ENTER_RED_PKG_REFUND_FEE);
        fields.add(WepayField.REFUND_CHANNEL);
        fields.add(WepayField.REFUND_STATUS);
    }

    private static List<String> initSuccessFields() {
        List<String> success = new ArrayList<>();
        startFields(success);
        endFields(success);
        return success;
    }

    private static void startFields(List<String> fields) {
        for (String f : START_FIELDS){
            fields.add(f);
        }
    }

    private static void endFields(List<String> fields) {
        for (String f : END_FIELDS){
            fields.add(f);
        }
    }
}
