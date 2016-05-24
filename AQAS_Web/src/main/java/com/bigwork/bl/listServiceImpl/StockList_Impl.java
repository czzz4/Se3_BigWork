package com.bigwork.bl.listServiceImpl;


import com.bigwork.bl_service.StockList_service;
import com.bigwork.data.dataServiceImpl.StockListData_Impl;
import com.bigwork.data_service.StockListData_service;
import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockList_Impl implements StockList_service {


    public ArrayList<Stock> ShowAll(){
        StockListData_service stockListData = new StockListData_Impl();
        ArrayList<Stock> s = new ArrayList<>();
        try {
             s = stockListData.StockList();

        } catch (SqlImplNullPointerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }


    public ArrayList<Stock> Search(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void Refresh(){
        // TODO Auto-generated method stub
        this.ShowAll();
    }

    public ArrayList<Stock> filter(Stock low, Stock high){
        // TODO Auto-generated method stub
        ArrayList<Stock> result = new ArrayList<Stock>();
        ArrayList<Stock> all = this.ShowAll();

//		System.out.println("in filter");

        for (Stock current : all) {
            StockFilter filter = new StockFilter(low, high, current);
//			System.out.println(low.getHigh()+"   "+high.getHigh());
//			System.out.println(current.getHigh());

            if (filter.filt())
                result.add(current);
        }
        return result;
    }

    public ArrayList<Stock> setTime(String startTime, String endTime) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) throws SQLException, SqlImplNullPointerException {
        StockList_Impl test = new StockList_Impl();
        Stock high = new Stock("", -1, -1, 19, -1, -1, -1, "", -1, -1, -1);
        Stock low = new Stock("", -1, -1, 1, -1, -1, -1, "", -1, -1, -1);
        test.filter(low, high);
    }

}
