package me.hao0.wepay.model.pay;


import me.hao0.wepay.annotation.Optional;

/**
 * 二维码支付请求对象
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 * @since 1.0.0
 */
public class QrPayRequest extends PayRequest {

    /**
     * 商品ID
     * {@link me.hao0.wepay.model.enums.WepayField#PRODUCT_ID}
     */
    @Optional(any = false)
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "QrPayRequest{" +
                "productId='" + productId + '\'' +
                "} " + super.toString();
    }
}
