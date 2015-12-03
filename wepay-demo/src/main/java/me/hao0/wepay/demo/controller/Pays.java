package me.hao0.wepay.demo.controller;

import me.hao0.wepay.demo.support.WepaySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 2/12/15
 */
@Controller
@RequestMapping("/pays/")
public class Pays {

    private static final Logger log = LoggerFactory.getLogger(Pays.class);

    @Autowired
    private WepaySupport wepaySupport;

    /**
     * 二维码支付
     * @param orderNumber 商户订单号
     */
    @RequestMapping(value = "/qrpay")
    public void qrPay(
            @RequestParam("orderNumber") String orderNumber,
            HttpServletResponse response){
        try {
            String qrUrl = wepaySupport.qrPay(orderNumber);
            response.sendRedirect(qrUrl);
        } catch (IOException e) {
            log.error("failed to qr pay(orderNumber={}), cause: {}",
                    orderNumber, e.getMessage());
        }
    }
}
