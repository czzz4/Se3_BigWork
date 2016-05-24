package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.MA_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class MA_Impl implements MA_service {

    private String ID;
    private String date1;
    private String date2;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SingleStock_service single;


    public MA_Impl(String id){
        ID = id;
        Calendar cal = Calendar.getInstance();
        Date end = cal.getTime();
        date2 = sdf.format(end);
        cal.add(Calendar.MONTH, -1);
        Date start = cal.getTime();
        date1 = sdf.format(start);
    }

    public MA_Impl(String id, String d1, String d2){
        ID = id;
        date1 = d1;
        date2 = d2;
    }


    public double getMA() {
        double result = 0.0;
        double sum = 0.0;
        try {
            single = new SingleStock_Impl();
            ArrayList<Stock> list = single.setTime(ID, date1, date2);
            for (Stock temp : list) {
                sum += temp.getClose();
            }
            result = sum / list.size();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args){
        MA_service test = new MA_Impl("sh600979", "2015-10-20", "2015-11-20");
        System.out.println(test.getMA());
    }


}
