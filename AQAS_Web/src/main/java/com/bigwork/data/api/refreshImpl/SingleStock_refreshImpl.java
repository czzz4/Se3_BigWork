package com.bigwork.data.api.refreshImpl;

import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.SingleStockData_management;
import com.bigwork.model.Stock;

import java.util.ArrayList;
import java.util.Calendar;

public class SingleStock_refreshImpl {
    private String date;
    private DataGetter getter = new DataGetter();
    private SingleStockData_management singleDataImpl = new SingleStockData_management();

    public ArrayList<Stock> Show(String id,String  lasttime) {
        ArrayList<Stock> result = new ArrayList<Stock>();
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        System.out.println(this.date);
        String url = "http://121.41.106.89:8010/api/stock/" + id + "/?start="+lasttime+"&end=" + this.date;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = singleDataImpl.SingleStock(temp);
            return result;
        }
        return null;
    }
}
