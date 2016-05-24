package com.bigwork.bl.managementServiceImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.ATR_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public class ATR_Impl implements ATR_service {
    //均幅指标
    private String ID;
    private String date1;
    private String date2;

    private SingleStock_service single;

    public ATR_Impl(String ID, String date1, String date2) {
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * 默认一个月时间
     *
     * @param ID
     */
    public ATR_Impl(String ID) {
        this.ID = ID;
    }

    public boolean setTime(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
        return true;
    }

    public double GetATR() throws ParseException {
        single = new SingleStock_Impl();
        ArrayList<Stock> list = single.setTime(ID, date1, date2);
        int n = list.size();
        double sum = 0;
        //当前交易日的最高价与最低价间的波幅；前一交易日收盘价与当个交易日最高价间的波幅；前一交易日收盘价与当个交易日最低价间的波幅
        double d1 = 0, d2 = 0, d3 = 0;
        double max = 0;
        for (int i = 1; i < n; i++) {
            d1 = list.get(i).getHigh() - list.get(i).getLow();
            d2 = list.get(i - 1).getClose() - list.get(i).getHigh();
            d3 = list.get(i - 1).getClose() - list.get(i).getLow();
            if (d2 < 0)
                d2 = -d2;
            if (d3 < 0) {
                d3 = -d3;
            }
            max = d1;
            if (max < d2) {
                max = d2;
            }
            if (max < d3) {
                max = d3;
            }
            sum = sum + max;
        }
        BigDecimal b = new BigDecimal(sum / n);
        double f1 = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static void main(String[] args) throws ParseException {
        ATR_service a = new ATR_Impl("sh600979", "2015-10-20", "2015-11-20");
        double num = a.GetATR();
        System.out.println(num);
    }


}
