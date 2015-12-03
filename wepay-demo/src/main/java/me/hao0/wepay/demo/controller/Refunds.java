package me.hao0.wepay.demo.controller;

import me.hao0.wepay.demo.support.WepaySupport;
import me.hao0.wepay.model.refund.RefundApplyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 3/12/15
 */
@Controller
@RequestMapping("/refunds")
public class Refunds {

    @Autowired
    private WepaySupport wepaySupport;

    /**
     * 退款申请
     * @param orderNumber 商户订单号
     */
    @RequestMapping("/apply")
    @ResponseBody
    public RefundApplyResponse apply(@RequestParam("orderNumber") String orderNumber){
        return wepaySupport.refundApply(orderNumber);
    }
}
