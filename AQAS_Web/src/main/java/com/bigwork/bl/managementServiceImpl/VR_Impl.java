package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.VR_service;
import com.bigwork.model.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class VR_Impl implements VR_service {

    private String ID;
    private int n;
    private String start;
    private String end;

    private SingleStock_service single;
    private ArrayList<Stock> list;

    public VR_Impl(String id){
        ID = id;

        this.setDate();
    }

    public VR_Impl(String id, int days){
        ID = id;
        n = days;

        this.setDate(days); // default date, with current date as end
    }

    public VR_Impl(String id, String start, String end){
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
//        end = "2014-11-12";
        cal.add(Calendar.DATE, - days);
        temp = cal.getTime();
        start = sdf.format(temp);
//        start = "2014-10-24";
    }

    public double getVR(){
        double result = 0.0;
        single = new SingleStock_Impl();
        list = single.setTime(ID, start, end);
        int up = this.getUp();
        int down = this.getDown();
        result = up * 100 / down;
        return result;
    }


    private int getUp(){
        int up = 0;
        for(Stock temp : list){
            if(temp.getClose() > temp.getOpen()){
                up++;
            }
        }
        return up;
    }

    private int getDown(){
        int down = 0;
        for(Stock temp : list){
            if(temp.getClose() > temp.getOpen()){
                down++;
            }
        }
        return down;
    }

    public static void main(String[] args){
        VR_service test = new VR_Impl("sz002644");
        System.out.println(test.getVR());
    }

}
