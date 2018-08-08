package me.hao0.wepay.model.Partner;

import java.io.Serializable;

/**
 * 企业付款到银行卡查询请求对象
 *
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/8/7
 */
public class QueryBankRequest implements Serializable {

    /**
     * 商户企业付款单号
     */
    private String partnerTradeNo;

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    @Override
    public String toString() {
        return "QueryBankRequest{" +
                "partnerTradeNo='" + partnerTradeNo + '\'' +
                '}';
    }
}
