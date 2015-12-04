# Wepay API文档

+ 已实现组件:
	
	+ 支付: <a href="#pay-api">pay()</a>
	+ 退款: <a href="#refund-api">refund()</a>
	+ 订单: <a href="#order-api">order()</a>
	+ 通知: <a href="#notify-api">notify()</a>
	+ 账单: <a href="#bill-api">bill()</a>
	
+ **<a id="pay-api">支付pay()</a>**:

	```java
	/**
     * JS支付(公众号支付)
     * @param request 支付请求对象
     * @return JsPayResponse对象，或抛WepayException
     */
    JsPayResponse jsPay(JsPayRequest request);	
    
	 /**
     * 动态二维码支付(NATIVE)[模式二]
     * @param request 支付请求对象
     * @param convert 是否转换为二维码图片链接(使用联图)
     * @return 可访问的二维码链接，或抛WepayException
     */
    String qrPay(QrPayRequest request, Boolean convert);
    
    /**
     * app支付
     * @param request 支付请求对象
     * @return AppPayResponse对象，或抛WepayException
     */
    AppPayResponse appPay(PayRequest request);
	```

+ **<a id="pay-api">退款refund()</a>**:
	
	```java
	 /**
     * 申请退款
     * @param request 退款请求对象
     * @return RefundResponse对象，或抛WepayException
     */
    RefundApplyResponse apply(RefundApplyRequest request);
    
     /**
     * 通过商户订单号查询退款
     * @param outTradeNo 商户订单号
     * @return 退款查询对象，或抛WepayException
     */
    RefundQueryResponse queryByOutTradeNo(String outTradeNo);
    
    /**
     * 通过商户退款单号查询退款
     * @param outRefundNo 商户退款单号
     * @return 退款查询对象，或抛WepayException
     */
    RefundQueryResponse queryByOutRefundNo(String outRefundNo);
    
    /**
     * 通过微信订单号查询退款
     * @param transactionId 微信订单号
     * @return 退款查询对象，或抛WepayException
     */
    RefundQueryResponse queryByTransactionId(String transactionId);
    
    /**
     * 通过微信退款单号查询退款
     * @param refundId 微信退款单号
     * @return 退款查询对象，或抛WepayException
     */
    RefundQueryResponse queryByRefundId(String refundId);
	```

+ **<a id="order-api">订单order()</a>**:
	
	```java
	/**
     * 根据微信订单号查询订单
     * @param transactionId 微信订单号
     * @return PayOrder对象，或抛WepayException
     */
    WePayOrder queryByTransactionId(String transactionId);
    
    /**
     * 根据商户订单号查询订单
     * @param outTradeNo 商户订单号
     * @return PayOrder对象，或抛WepayException
     */
    WePayOrder queryByOutTradeNo(String outTradeNo);
    
    /**
     * 关闭订单
     * @param outTradeNo 商户订单号
     * @return 关闭成功返回true，或抛WepayException
     */
    Boolean closeOrder(String outTradeNo);
    
	```
	
+ **<a id="notify-api">通知notify()</a>**:

	```java
	/**
     * 签名校验
     * @param params 待验证参数(包含sign)
     * @return 验证通过返回true，反之false
     */
    Boolean verifySign(Map<String, ?> params);
    
    /**
     * 通知成功
     * @return 通知成功的XML消息
     */
    String ok();
    
    /**
     * 通知不成功
     * @param errMsg 失败消息
     * @return 通知失败的XML消息
     */
    String notOk(String errMsg);
	```
        
+ **<a id="bill-api">通知bill()</a>**:

	```java
	/**
     * 查询所有账单
     * @param deviceInfo 微信支付分配的终端设备号，填写此字段，只下载该设备号的对账单
     * @param date 账单的日期
     * @return 账单明细
     */
    BillDetail<CommonBill> queryAll(String deviceInfo, String date);
	
	/**
     * 查询交易成功的账单
     * @param deviceInfo 微信支付分配的终端设备号，填写此字段，只下载该设备号的对账单
     * @param date 账单的日期
     * @return 账单明细
     */
    BillDetail<Bill> querySuccess(String deviceInfo, String date);
    
    /**
     * 查询退款账单
     * @param deviceInfo 微信支付分配的终端设备号，填写此字段，只下载该设备号的对账单
     * @param date 账单的日期
     * @return 账单明细
     */
    BillDetail<RefundBill> queryRefund(String deviceInfo, String date);
	```