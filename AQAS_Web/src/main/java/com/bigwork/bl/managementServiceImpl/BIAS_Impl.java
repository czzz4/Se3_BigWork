package com.bigwork.bl.managementServiceImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.BIAS_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public class BIAS_Impl implements BIAS_service {
    // 乖离率
    private String ID;
    private String date1;
    private String date2;

    private SingleStock_service single;

    public BIAS_Impl(String ID, String date1, String date2) {
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * 默认一个月时间
     *
     * @param ID
     */
    public BIAS_Impl(String ID) {
        this.ID = ID;
    }

    public boolean setTime(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
        return true;
    }

    public double getBIAS() throws ParseException {
        // 乖离率
        single = new SingleStock_Impl();
        ArrayList<Stock> list = single.setTime(ID, date1, date2);
        double bias;

        int n = list.size();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + list.get(i).getClose();
        }
        //sum为一般移动平均线
        sum = sum / n;

        bias = (list.get(n - 1).getClose() - sum) / sum * 100;
        BigDecimal b = new BigDecimal(bias);
        double f1 = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static void main(String[] args) throws ParseException {
        BIAS_service a = new BIAS_Impl("sh600979", "2015-11-01", "2015-11-10");
        double num = a.getBIAS();
        System.out.println(num + "%");
    }
}
