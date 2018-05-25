package me.hao0.wepay.model.pay;

/**
 * H5支付请求对象
 * Author: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/25
 *
 * @since 1.0.0
 */
public class H5PayRequest extends PayRequest {

    /**
     * WAP支付链接
     */
    private String wapUrl;
    /**
     * /WAP支付网页名称
     */
    private String wapName;

    public String getWapUrl() {
        return wapUrl;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public String getWapName() {
        return wapName;
    }

    public void setWapName(String wapName) {
        this.wapName = wapName;
    }

    @Override
    public String toString() {
        return "H5PayRequest{" +
                "wapUrl='" + wapUrl + '\'' +
                ", wapName='" + wapName + '\'' +
                '}';
    }
}
