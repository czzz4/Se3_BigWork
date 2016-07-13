package bl.listServiceImpl;


import java.util.ArrayList;

import data.dataServiceImpl.StockListData_Impl;
import data_service.StockListData_service;
import vo.StockVO;
import bl_service.StockList_service;

public class StockList_Impl implements StockList_service {


    public ArrayList<StockVO> ShowAll() {
        StockListData_service stockListData = new StockListData_Impl();
        return stockListData.StockList();
    }


    public ArrayList<StockVO> Search(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void Refresh() {
        // TODO Auto-generated method stub
        this.ShowAll();
    }

    public ArrayList<StockVO> filter(StockVO low, StockVO high) {
        // TODO Auto-generated method stub
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        ArrayList<StockVO> all = this.ShowAll();

//		System.out.println("in filter");

        all = this.ShowAll();
        for (StockVO current : all) {
            StockFilter filter = new StockFilter(low, high, current);
//			System.out.println(low.getHigh()+"   "+high.getHigh());
//			System.out.println(current.getHigh());

            if (filter.filt())
                result.add(current);
        }
        return result;
    }

    public ArrayList<StockVO> setTime(String startTime, String endTime) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        StockList_Impl test = new StockList_Impl();
        StockVO high = new StockVO("", -1, -1, 19, -1, -1, -1, "", -1, -1, -1);
        StockVO low = new StockVO("", -1, -1, 1, -1, -1, -1, "", -1, -1, -1);
        test.filter(low, high);
    }

}
