package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.KDJ_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/13.
 */
public class KDJ_Impl implements KDJ_service {

    private String ID;
    private String start;
    private String end;
    private int n;

    private SingleStock_service single;
    private ArrayList<Stock> list;

    public KDJ_Impl(String id){
        ID = id;
        n = 9;
        this.setDate();
        this.setDays();
    }

    public KDJ_Impl(String id, int days){
        ID = id;
        n = days;
        this.setDate();
        this.setDays();
    }

    public KDJ_Impl(String id, String d1, String d2){
        ID = id;
        start = d1;
        end = d2;

        this.setDays();

    }

    private void setDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date temp = cal.getTime();
        end = sdf.format(temp);     // default: current day as ending date
        cal.add(Calendar.DATE, -n); // default: 9 days
        temp = cal.getTime();
        start = sdf.format(temp);
    }


    @Override
    public double getK() {
        double k = 50.0;
        single = new SingleStock_Impl();
        for(int a = 1; a<n; a++){
//            System.out.println("K = "+k);
//            System.out.println("RSV = "+this.getRSV());

            k = 2*k/3 + this.getRSV()/3;
        }
        return k;
    }

    @Override
    public double getD() {

//        System.out.println("days = "+n);

        double d = 50.0;
        single = new SingleStock_Impl();
        for(int a = 1; a<n; a++){
            d = 2*d/3 + getK()/3;
        }
        return d;
    }

    @Override
    public double getJ() {
        double j = 0.0;
        single = new SingleStock_Impl();
        j = this.getK() * 3 - this.getD() * 2;
        return j;
    }

    private double getRSV(){
        double rsv = 0.0;
        double ln = 0.0;
        double hn = 0.0;
        double c = 0.0;

        try {
            single = new SingleStock_Impl();

            list = single.setTime(ID, start, end);

            ln = this.getLow(list);
            hn = this.getHigh(list);
//            System.out.println("list size "+list.size());
            c = list.get(n - 1).getClose();
            rsv = (c - ln) * 100 / (hn - ln);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rsv;
    }


    private void setDays(){
        single = new SingleStock_Impl();

        list = single.setTime(ID, start, end);
        n = list.size();
    }


    private double getLow(ArrayList<Stock> list){
        double ln = list.get(0).getClose();
        for(Stock temp : list){
            if(temp.getClose() <= ln){    // 是close吧....??
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

//    private int getN(){
//        int res = 0;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date d1 = null;
//        Date d2 = null;
//        try {
//            d1 = sdf.parse(start);
//            d2 = sdf.parse(end);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        long t1 = d1.getTime();
//        long t2 = d2.getTime();
//        long t3 = Math.abs(t1-t2);
//
//        res = (int)(t3/1000/60/60/24);
//
//        return res;
//    }

    public static void main(String[] args){
        KDJ_service test = new KDJ_Impl("sz002644", "2015-11-15", "2015-12-29");
        System.out.println(test.getJ());
    }

}
