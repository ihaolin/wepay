package me.hao0.wepay.demo.controller;

import com.google.common.base.Throwables;
import me.hao0.wepay.demo.support.WepaySupport;
import me.hao0.wepay.util.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * 通知
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 2/12/15
 */
@RestController
@RequestMapping("/notifies")
public class Notifies {

    private static final Logger logger = LoggerFactory.getLogger(Notifies.class);

    @Autowired
    private WepaySupport wepaySupport;

    /**
     * 支付成功通知
     * @param request 请求对象
     * @return 处理结果
     */
    @RequestMapping("/paid")
    public String paid(HttpServletRequest request){

        String notifyXml = getPostRequestBody(request);
        if (notifyXml.isEmpty()){
            return wepaySupport.notifyNotOk("body为空");
        }

        Map<String, Object> notifyParams = Maps.toMap(notifyXml);

        if (wepaySupport.verifySign(notifyParams)){

            // TODO business logic

            logger.info("verify sign success: {}", notifyParams);

            return wepaySupport.notifyOk();
        } else {

            logger.error("verify sign failed: {}", notifyParams);
            return wepaySupport.notifyNotOk("签名失败");
        }
    }

    public static String getPostRequestBody(HttpServletRequest req) {
        if (req.getMethod().equals("POST")) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = req.getReader()) {
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = br.read(charBuffer)) != -1) {
                    sb.append(charBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                logger.warn("failed to read request body, cause: {}", Throwables.getStackTraceAsString(e));
            }
            return sb.toString();
        }
        return "";
    }
}
