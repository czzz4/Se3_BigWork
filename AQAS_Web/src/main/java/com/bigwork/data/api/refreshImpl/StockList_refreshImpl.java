package com.bigwork.data.api.refreshImpl;


import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.StockListData_management;
import com.bigwork.model.Stock;

import java.text.ParseException;
import java.util.ArrayList;

public class StockList_refreshImpl {

    private DataGetter getter = new DataGetter();
    private StockListData_management stockListDataImpl = new StockListData_management();
//	private SingleStockData_management singleDataImpl = new SingleStockData_management();

    public ArrayList<Stock> ShowAll() throws ParseException {
        ArrayList<Stock> result = new ArrayList<Stock>();
        String url = "http://121.41.106.89:8010/api/stocks/";
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = stockListDataImpl.StockList(temp);
        }
        return result;
    }

}
