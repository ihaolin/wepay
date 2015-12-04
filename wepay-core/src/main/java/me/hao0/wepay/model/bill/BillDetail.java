package me.hao0.wepay.model.bill;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 账单信息
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 4/12/15
 * @since 1.1.0
 */
public class BillDetail<T extends Bill> implements Serializable {

    private static final long serialVersionUID = 2763506940046963037L;

    /**
     * 账单记录
     */
    private List<T> bills;

    /**
     * 账单统计
     */
    private BillCount count;

    @SuppressWarnings("unchecked")
    private static final BillDetail EMPTY = (BillDetail)new BillDetail(Collections.emptyList(), null);

    public BillDetail(List<T> bills, BillCount count) {
        this.bills = bills;
        this.count = count;
    }

    public List<T> getBills() {
        return bills;
    }

    public void setBills(List<T> bills) {
        this.bills = bills;
    }

    public BillCount getCount() {
        return count;
    }

    public void setCount(BillCount count) {
        this.count = count;
    }

    @SuppressWarnings("unchecked")
    public static BillDetail empty(){
        return EMPTY;
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "bills=" + bills +
                ", count=" + count +
                '}';
    }
}
