package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.RSI_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.VR_service;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class RSI_Impl implements RSI_service {

    private String ID;
    private int n;
    private String start;
    private String end;

    private SingleStock_service single;
    private ArrayList<Stock> list;

    public RSI_Impl(String id){
        ID = id;

        this.setDate();
    }

    public RSI_Impl(String id, int days){
        ID = id;
        n = days;

        this.setDate(days); // default date, with current date as end
    }

    public RSI_Impl(String id, String start, String end){
        ID = id;
        this.start = start;
        this.end = end;
    }

    private void setDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date temp = cal.getTime();
        end = sdf.format(temp);
//        end = "2014-11-12";
        cal.add(Calendar.MONTH, -1);
        temp = cal.getTime();
        start = sdf.format(temp);
//        start = "2014-10-24";
    }

    private void setDate(int days){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date temp = cal.getTime();
        end = sdf.format(temp);
        end = "2014-11-12";
        cal.add(Calendar.DATE, - days);
        temp = cal.getTime();
        start = sdf.format(temp);
        start = "2014-10-24";
    }

    public double getRSI(){
        double result = 0.0;
        single = new SingleStock_Impl();
        list = single.setTime(ID, start, end);
        double rise = this.getRise();
        double dec = this.getDec();
        result = rise * 100 / (rise + dec);

        return result;
    }


    private double getRise(){
        double r = 0;
        for(int a = 1; a<list.size(); a++){
            if(list.get(a).getClose() > list.get(a-1).getClose()){
                r += (list.get(a).getClose() - list.get(a-1).getClose());
            }
        }
        r /= (list.size()-1) ;
        return r;
    }

    private double getDec(){
        double d = 0;
        for(int a = 1; a<list.size(); a++){
            if(list.get(a).getClose() < list.get(a-1).getClose()){
//                System.out.println("dec "+(list.get(a-1).getClose() - list.get(a).getClose()));

                d += (list.get(a-1).getClose() - list.get(a).getClose());
            }
        }
        d /= (list.size()-1);
        return d;
    }


    public static void main(String[] args){
        RSI_service test = new RSI_Impl("sz002644", "2014-12-12", "2014-12-23");
        System.out.println(test.getRSI());
    }

}
