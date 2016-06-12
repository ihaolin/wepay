package me.hao0.wepay.core;

import me.hao0.common.util.Strings;
import me.hao0.wepay.model.enums.FeeType;
import me.hao0.wepay.model.enums.RefundChannel;
import me.hao0.wepay.model.enums.RefundStatus;
import me.hao0.wepay.model.enums.WepayField;
import me.hao0.wepay.model.refund.RefundApplyRequest;
import me.hao0.wepay.model.refund.RefundApplyResponse;
import me.hao0.wepay.model.common.Coupon;
import me.hao0.wepay.model.refund.RefundItem;
import me.hao0.wepay.model.refund.RefundQueryResponse;
import me.hao0.wepay.util.RandomStrs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static me.hao0.common.util.Preconditions.*;

/**
 * 退款组件
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 27/11/15
 * @since 1.0.0
 */
public final class Refunds extends Component {

    /**
     * 申请退款
     */
    private static final String APPLY = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 查询退款
     */
    private static final String QUERY = "https://api.mch.weixin.qq.com/pay/refundquery";

    Refunds(Wepay wepay) {
        super(wepay);
    }

    /**
     * 申请退款
     * @param request 退款请求对象
     * @return RefundResponse对象，或抛WepayException
     */
    public RefundApplyResponse apply(RefundApplyRequest request){
        checkApplyParams(request);
        Map<String, String> applyParams = buildApplyParams(request);
        return doHttpsPost(APPLY, applyParams, RefundApplyResponse.class);
    }

    /**
     * 通过商户订单号查询退款
     * @param outTradeNo 商户订单号
     * @return 退款查询对象，或抛WepayException
     */
    public RefundQueryResponse queryByOutTradeNo(String outTradeNo){
        Map<String, String> queryParams = buildQueryParams(WepayField.OUT_TRADE_NO, outTradeNo);
        Map<String, Object> respData = doPost(QUERY, queryParams);
        return renderQueryResp(respData);
    }

    /**
     * 通过商户退款单号查询退款
     * @param outRefundNo 商户退款单号
     * @return 退款查询对象，或抛WepayException
     */
    public RefundQueryResponse queryByOutRefundNo(String outRefundNo){
        Map<String, String> queryParams = buildQueryParams(WepayField.OUT_REFUND_NO, outRefundNo);
        Map<String, Object> respData = doPost(QUERY, queryParams);
        return renderQueryResp(respData);
    }

    /**
     * 通过微信订单号查询退款
     * @param transactionId 微信订单号
     * @return 退款查询对象，或抛WepayException
     */
    public RefundQueryResponse queryByTransactionId(String transactionId){
        Map<String, String> queryParams = buildQueryParams(WepayField.TRANSACTION_ID, transactionId);
        Map<String, Object> respData = doPost(QUERY, queryParams);
        return renderQueryResp(respData);
    }

    /**
     * 通过微信退款单号查询退款
     * @param refundId 微信退款单号
     * @return 退款查询对象，或抛WepayException
     */
    public RefundQueryResponse queryByRefundId(String refundId){
        Map<String, String> queryParams = buildQueryParams(WepayField.REFUND_ID, refundId);
        Map<String, Object> respData = doPost(QUERY, queryParams);
        return renderQueryResp(respData);
    }

    private RefundQueryResponse renderQueryResp(Map<String, Object> refundData) {
        RefundQueryResponse queryResp = new RefundQueryResponse();

        queryResp.setOutTradeNo((String)refundData.get(WepayField.OUT_TRADE_NO));
        queryResp.setTransactionId((String)refundData.get(WepayField.TRANSACTION_ID));
        queryResp.setTotalFee(Integer.parseInt((String)refundData.get(WepayField.TOTAL_FEE)));
        queryResp.setCashFee(Integer.parseInt((String) refundData.get(WepayField.CASH_FEE)));
        String feeType = (String)refundData.get(WepayField.FEE_TYPE);
        if (!Strings.isNullOrEmpty(feeType)){
            queryResp.setFeeType(FeeType.from(feeType));
        }

        Integer refundCount = Integer.parseInt((String) refundData.get(WepayField.REFUND_COUNT));

        List<RefundItem> refundItems = new ArrayList<>();
        RefundItem refundItem;
        for (int refundIndex = 0; refundIndex < refundCount; refundIndex++){
            refundItem = renderRefundItem(refundData, refundIndex);
            refundItems.add(refundItem);
        }
        queryResp.setItems(refundItems);

        return queryResp;
    }

    private RefundItem renderRefundItem(Map<String, Object> refundData, int refundItemIndex) {
        RefundItem refundItem = new RefundItem();
        refundItem.setOutRefundNo((String)refundData.get(WepayField.OUT_REFUND_NO + "_" + refundItemIndex));
        refundItem.setRefundId((String)refundData.get(WepayField.REFUND_ID + "_" + refundItemIndex));
        refundItem.setChannel(RefundChannel.from((String) refundData.get(WepayField.REFUND_CHANNEL + "_" + refundItemIndex)));
        refundItem.setRefundFee(Integer.parseInt((String)refundData.get(WepayField.REFUND_FEE + "_" + refundItemIndex)));
        refundItem.setRefundStatus(RefundStatus.from((String)refundData.get(WepayField.REFUND_STATUS + "_" + refundItemIndex)));

        String settlementRefundFee = (String)refundData.get(WepayField.SETTLEMENT_REFUND_FEE + "_" + refundItemIndex);
        if (!Strings.isNullOrEmpty(settlementRefundFee)){
            refundItem.setSettlementRefundFee(Integer.parseInt(settlementRefundFee));
        }

        refundItem.setRefundRecvAccout((String)refundData.get(WepayField.REFUND_RECV_ACCOUNT + "_" + refundItemIndex));

        Object couponRefundFee = refundData.get(WepayField.COUPON_REFUND_FEE + "_" + refundItemIndex);
        if (couponRefundFee != null){
            refundItem.setCouponRefundFee(Integer.parseInt((String)couponRefundFee));
        }
        Object couponRefundCountObj = refundData.get(WepayField.COUPON_REFUND_COUNT + "_" + refundItemIndex);
        if (couponRefundCountObj != null){
            Integer couponRefundCount = Integer.parseInt((String)couponRefundCountObj);
            if (couponRefundCount > 0){
                List<Coupon> couponItems = new ArrayList<>();
                Coupon couponItem;
                for (int couponItemIndex = 0; couponItemIndex < couponRefundCount; couponItemIndex++){
                    couponItem = Coupon.newCoupon(
                            (String) refundData.get(WepayField.COUPON_REFUND_BATCH_ID + "_" + refundItemIndex + "_" + couponItemIndex),
                            (String) refundData.get(WepayField.COUPON_REFUND_ID + "_" + refundItemIndex + "_" + couponItemIndex),
                            Integer.parseInt((String) refundData.get(WepayField.COUPON_REFUND_FEE + "_" + refundItemIndex + "_" + couponItemIndex))
                    );
                    couponItems.add(couponItem);
                }
                refundItem.setCoupons(couponItems);
            }
        }

        return refundItem;
    }

    /**
     * 构建查询退款参数
     * @param queryFieldName 查询字段名
     * @param queryFieldValue 查询字段值
     * @return 查询参数
     */
    private Map<String, String> buildQueryParams(String queryFieldName, String queryFieldValue) {
        checkNotNullAndEmpty(queryFieldValue, queryFieldName);

        Map<String, String> queryParams = new TreeMap<>();
        buildConfigParams(queryParams);
        queryParams.put(WepayField.NONCE_STR, RandomStrs.generate(16));
        queryParams.put(queryFieldName, queryFieldValue);
        buildSignParams(queryParams);

        return queryParams;
    }

    /**
     * 校验退款参数
     * @param request 退款请求对象
     */
    private void checkApplyParams(RefundApplyRequest request) {
        checkNotNull(wepay.certs, "merchant certs can't be null before apply refund");
        checkNotNullAndEmpty(wepay.certPasswd, "certPasswd");
        checkNotNull(request, "apply request can't be null");
        if (Strings.isNullOrEmpty(request.getTransactionId())){
            checkNotNullAndEmpty(request.getOutTradeNo(), "transactionId && outTradeNo");
        }
        checkNotNullAndEmpty(request.getOutRefundNo(), "outRefundNo");
        checkNotNullAndEmpty(request.getOpUserId(), "opUserId");
        Integer totalFee = request.getTotalFee();
        Integer refundFee = request.getRefundFee();
        checkPositive(totalFee, "totalFee");
        checkPositive(refundFee, "refundFee");
        checkPositive(totalFee - refundFee, "totalFee - refundFee");
    }

    /**
     * 构建退款参数
     * @param request 退款请求
     * @return 退款参数
     */
    private Map<String, String> buildApplyParams(RefundApplyRequest request) {
        Map<String, String> refundParams = new TreeMap<>();

        // 配置参数
        buildConfigParams(refundParams);

        // 业务参数
        putIfNotEmpty(refundParams, WepayField.TRANSACTION_ID, request.getTransactionId());
        putIfNotEmpty(refundParams, WepayField.OUT_TRADE_NO, request.getOutTradeNo());
        put(refundParams, WepayField.OUT_REFUND_NO, request.getOutRefundNo());
        put(refundParams, WepayField.TOTAL_FEE, request.getTotalFee() + "");
        put(refundParams, WepayField.REFUND_FEE, request.getRefundFee() + "");
        put(refundParams, WepayField.NONCE_STR, RandomStrs.generate(16));
        put(refundParams, WepayField.OP_USER_ID, request.getOpUserId());
        putIfNotEmpty(refundParams, WepayField.DEVICE_INFO, request.getDeviceInfo());
        if (request.getRefundFeeType() != null){
            put(refundParams, WepayField.REFUND_FEE_TYPE, request.getRefundFeeType().type());
        }

        // 签名参数
        buildSignParams(refundParams);

        return refundParams;
    }
}
