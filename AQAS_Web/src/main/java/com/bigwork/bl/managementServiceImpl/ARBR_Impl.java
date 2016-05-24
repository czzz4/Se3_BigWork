package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.ARBR_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;
import sun.java2d.pipe.SpanShapeRenderer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class ARBR_Impl implements ARBR_service {

    private String ID;
    private int n;
    private String today;

    private SingleStock_service single;
    private ArrayList<Stock> list;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public ARBR_Impl(String id){
        ID = id;
        n = 26;
        today = sdf.format(Calendar.getInstance().getTime());

//        today = "2015-11-14";
    }

    public ARBR_Impl(String id, int n){
        ID = id;
        this.n = n;
        today = sdf.format(Calendar.getInstance().getTime());
    }

    public double getAR(){
        double result = 0.0;
        list = this.getList();
        double hSum = 0.0;
        double lSum = 0.0;
        for(Stock temp : list){
            hSum += (temp.getHigh() - temp.getOpen());
            lSum += (temp.getOpen() - temp.getLow());
        }
        result = hSum * 100 / lSum;
        return result;
    }


    public double getBR(){
        double result = 0.0;
        list = this.getList();
        double hSum = 0.0;
        double lSum = 0.0;
        for(int i = 1; i < list.size(); i++){
            hSum += list.get(i).getHigh() - list.get(i - 1).getClose();
            lSum += list.get(i - 1).getClose() - list.get(i).getLow();
        }
        result = hSum / lSum;
        return result;
    }

    private ArrayList<Stock> getList(){
        String start;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date d1 = cal.getTime();
        start = sdf.format(d1);
//        start = "2015-10-19";
        single = new SingleStock_Impl();

        ArrayList<Stock> list = new ArrayList<Stock>();

        try {
            list = single.setTime(ID, start, today);
//            System.out.println("size"+list.size());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args){
        ARBR_service test = new ARBR_Impl("sh600979");
        System.out.println(test.getAR()+"  "+test.getBR());
    }
}
