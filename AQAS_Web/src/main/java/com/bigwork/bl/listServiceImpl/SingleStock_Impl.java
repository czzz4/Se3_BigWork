package com.bigwork.bl.listServiceImpl;

import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.SingleStockData_management;
import com.bigwork.data.dataServiceImpl.SingleStockData_Impl;
import com.bigwork.data_service.SingleStockData_service;
import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SingleStock_Impl implements SingleStock_service {

    private DataGetter getter = new DataGetter();
    private SingleStockData_management singleDataImpl = new SingleStockData_management();
    private String date1;
    private String date2;

    public ArrayList<Stock> Show(String id)  {
    	Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date2 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date2 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        //System.out.println(this.date2);
        cal.add(Calendar.MONTH, -1);
        month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date1 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date1 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        ArrayList<Stock> s = new ArrayList<>();
        s = this.setTime(id, date1, date2);
        /* SingleStockData_service singleStockData = new SingleStockData_Impl();
        return singleStockData.SingleStock(id);*/
        return  s;
    }

    public void close() {
        // TODO Auto-generated method stub

    }


    //界面没有实现这个功能？
    private ArrayList<Stock> SetTime(String id, String startTime, String endTime) {
        ArrayList<Stock> result = new ArrayList<Stock>();
        String realEnd = "";
        System.out.println("Out");
        //返回结果包括最后一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date end = sdf.parse(endTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(end);
            cal.add(Calendar.DATE, 1);
            realEnd = sdf.format(cal.getTime());
            System.out.println(realEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String url = "http://121.41.106.89:8010/api/stock/"
                + id + "/?start=" + startTime + "&end="
                + realEnd;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);

            result = singleDataImpl.SingleStock(temp);
            return result;
        }
        return null;
    }

    public ArrayList<Stock> setTime(String id, String startTime, String endTime){
        SingleStockData_service singleStockData = new SingleStockData_Impl();


        ArrayList<Stock> R = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date line = null;
        try {
            line = sdf.parse("2010-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if ((startDate.getTime() - line.getTime()) > 0) {
            System.out.println("In");
            ArrayList<Stock> result = null;
            try {
                result = singleStockData.SingleStock(id,startTime,endTime);
            } catch (SqlImplNullPointerException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            R = result;
        } else {
            R = this.SetTime(id, startTime, endTime);
        }

        return R;
    }

//	public static void main(String[] args){
//		SingleStock_Impl test = new SingleStock_Impl();
//		ArrayList<StockVO> res = test.setTime("sh600000", "2016-02-23", "2016-02-23");
//		for(StockVO vo : res){
//			System.out.println(vo.getClose());
//		}
//	}

}