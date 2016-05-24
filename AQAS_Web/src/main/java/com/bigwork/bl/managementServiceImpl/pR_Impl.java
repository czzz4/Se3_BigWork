package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.pR_service;
import com.bigwork.model.Stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class pR_Impl implements pR_service {

    private String ID;
    private int n;
    private String end;
    private String start;

    private SingleStock_service single;

    public pR_Impl(String id, int n){
        ID = id;
        this.n = n;

        this.setDate(n);
    }

    public pR_Impl(String id, String start, String end){
        this.start = start;
        this.end = end;
    }

    private void setDate(int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date temp = cal.getTime();
        end = sdf.format(temp);
//        today = "2014-11-12";
        cal.add(Calendar.DATE, -n);
        temp = cal.getTime();
        start = sdf.format(temp);
//        start = "2014-10-24";
    }

    public double getpR(){
        double result = 0.0;
        double ln = 0.0;
        double hn = 0.0;
        double c = 0.0;

        try {
            single = new SingleStock_Impl();

            ArrayList<Stock> list = single.setTime(ID, start, end);

            ln = this.getLow(list);
            hn = this.getHigh(list);
            int num = list.size();
            c = list.get(num - 1).getClose();
            result = 100 - (c - ln) * 100 / (hn - ln);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private double getLow(ArrayList<Stock> list){
        double ln = list.get(0).getClose();
        for(Stock temp : list){
            if(temp.getClose() <= ln){    // 是close的是吧....
                ln = temp.getClose();
            }
        }
        return ln;
    }

    private double getHigh(ArrayList<Stock> list){
        double hn = list.get(0).getClose();
        for(Stock temp : list){
            if(temp.getClose() >= hn){
                hn = temp.getClose();
            }
        }
        return hn;
    }


    public static void main(String[] args){
        pR_service test = new pR_Impl("sz002644", 14);
        System.out.println(test.getpR());
    }
}
