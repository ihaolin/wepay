# Wepay[![Build Status](https://travis-ci.org/ihaolin/wepay.svg?branch=master)](https://travis-ci.org/ihaolin/wepay)

轻量的微信支付组件(A Lightweight Wechat Pay Component)
---

+ 包引入:
	
	```xml
	<dependency>
        <groupId>me.hao0</groupId>
        <artifactId>wepay-core</artifactId>
        <version>1.3.0</version>
    </dependency>
	```
	
+ 依赖包，注意引入项目时是否需要**exclude**:

	```xml
	<dependency>
        <groupId>me.hao0</groupId>
        <artifactId>common</artifactId>
        <version>1.1.2</version>
    </dependency>
	
	```
	
+ 基本用法:
	
	```java
	Wepay wepay = WepayBuilder
                    .newBuilder(appId, appKey, mchId)
                    .config1(...)	// 其他可选配置
                    ...
                    .build();
    
    wepay.module().api();
	```

+ 已实现的组件:

	+ 支付``pay()``;
	+ 退款``refund()``;
	+ 订单``order()``;
	+ 通知``notify()``;
	+ 账单``bill()``。
		
+ API文档[这里](API.md)。

+ **关于测试**: 
	
	+ [测试用例中](wepay-core/src/test/java/me/hao0/wepay/WepayTest.java)是一些基本测试，需作一些配置：

		```java
		// 在test/reources目录中配置dev.properties
		// 包括appId(APP ID), appKey(支付密钥), mchId(商户号)
		Properties props = new Properties();
        InputStream in = Object.class.getResourceAsStream("/dev.properties");
        props.load(in);
        in.close();
		 
		 // 配置证书，退款需要证书，不配置可测试除退款的接口 
        Path path = Paths.get("/path/to/your_cert.p12");
        byte[] data = Files.readAllBytes(path);

        wepay = WepayBuilder.newBuilder(
                props.getProperty("appId"),
                props.getProperty("appKey"),
                props.getProperty("mchId"))
                .certPasswd(props.getProperty("mchId"))
                .certs(data)
                .build();
		```
	
	+ [wepay-demo](wepay-demo)项目是一个可运行web项目，方便测试，可按如下步骤进行测试，复制[wepay-demo](wepay-demo)中的``app-example.properties``为``app.properties``，并作相应配置:

		```ruby
		# 微信app id
		appId=
		# 微信支付key
		appKey=
		# 商户号
		mchId=
		# 支付通知url
		payNotifyUrl=${your_domain}/notifies/paid
		```
	
	+ **注意**：**``payNotifyUrl ``**应该配置为微信服务器可以外网调用的地址，本地测试建议使用[ngrok](https://ngrok.com/)工具来作本地外网映射。

	+ 到[wepay-demo](wepay-demo)根目录运行以下命令即可:

		```bash
		mvn clean jetty:run -Dmaven.test.skip -Djetty.port={自定义端口号}
		```
	
	+ 动态二维码支付可访问(**请求正常后，会出现由联图生成的二维码图片，用微信扫描支付成功后，后台会得到对应通知``Notifies``**):
		
		```bash
		http://localhost:{port}/pays/qrpay?orderNumber={自定义订单号}
		```	
	
	+ 退款可访问(**提交成功后，微信会有消息通知**):

		```bash
		http://localhost:{port}/refunds/apply?orderNumber={商户订单号}		```
		
+ 相关文档:
	
	+ [微信支付文档](https://pay.weixin.qq.com/wiki/doc/api/index.html)。

+ 历史版本:

	+ 1.0.0:
		
		+ 基本功能实现。
	
	+ 1.1.0:

		+ 增加账单查询。

	+ 1.1.1:

		+ 修复prepayId。

	+ 1.1.2:

		+ 修复JS/APP支付签名问题。

	+ 1.2.2:
		
		+ 增加退款查询字段: 退款状态, 退款金额, 退款入账方。
	
	+ 1.2.3:
			
        + fix isNullOrEmpty。
    
    + 1.2.4:
    			
        + 升级common至1.1.2, 可配置解析微信XML的编码类型, 默认为UTF-8, 防止与本地默认编码不一致。
    
    + 1.2.5:
    
        + 二维码支付结果更新为[QrPayResponse](wepay-core/src/main/java/me/hao0/wepay/model/pay/QrPayResponse.java)，并增加**prepay_id**字段。
    
    + 1.3.0:
    
        + 获取微信响应内容，签名校验失败时，抛出异常[SignException](wepay-core/src/main/java/me/hao0/wepay/exception/SignException.java)。

+ 相关组件:

	+ <a href="https://github.com/ihaolin/alipay" target="_blank">支付宝支付组件</a>；
	+ <a href="https://github.com/ihaolin/wechat" target="_blank">微信公众号组件</a>。

## 有事请烧钱

+ 支付宝:
		
	<img src="alipay.png" width="200">
	
+ 微信:

	<img src="wechat.png" width="200">
        