package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.OBV_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ryysuke on 16/5/12.
 */
public class OBV_Impl implements OBV_service {

    private String ID;
    private String start;
    private String end;

//    final String base = "2012-01-01";
    private ArrayList<Stock> list;
    private SingleStock_service single;

    public OBV_Impl(String id){
        ID = id;
        this.setDate(); // default, with current date as end
    }

    public OBV_Impl(String id, String date1, String date2){
        ID = id;
        start = date1;
        end = date2;
    }

    private void setDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        end = sdf.format(cal.getTime());

        cal.add(Calendar.MONTH, -1);
        start = sdf.format(cal.getTime());
    }

    public double getOBV(){
        double obv = 0.0;
        single = new SingleStock_Impl();
        list = single.setTime(ID, start, end);
        obv = list.get(0).getVolume();
        for(int a = 1; a < list.size(); a++){
            if(list.get(a).getVolume() < list.get(a - 1).getVolume()){
                obv -= list.get(a).getVolume();
            }
            else{
                obv += list.get(a).getVolume();
            }
        }

        return obv;
    }


    public static void main(String[] args){
        OBV_service test = new OBV_Impl("sz002644", "2015-10-21", "2015-12-21");
        System.out.println(test.getOBV());
    }
}
