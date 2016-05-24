package com.bigwork.data.api.dataManagement;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.model.Stock;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class StockListData_management {

    public ArrayList<Stock> StockList(String data) throws ParseException {
        // TODO Auto-generated method stub
        DataGetter getData = new DataGetter();
        String Data;

        ArrayList<Stock> temp = new ArrayList<Stock>();
        ArrayList<Stock> result = new ArrayList<Stock>();
        SingleStock_Impl singlestockImpl = new SingleStock_Impl();

        JSONObject jsonObj;
        JSONArray jsonarray;
        if (TestConnect.getConnectionState()) {
            jsonObj = JSONObject.fromObject(data);
            jsonarray = JSONArray.fromObject(jsonObj.get("data"));

            Object[] obj = jsonarray.toArray();
            for (int i = 0; i < 10; i++) {
                if (i == 7) {
                    i++;
                }
                jsonObj = JSONObject.fromObject(jsonarray.getString(i));
                //System.out.println(i);
                //Data = getData.getData(jsonObj.getString("name"));
                temp = singlestockImpl.Show(jsonObj.getString("name"));

                result.add(temp.get(temp.size() - 1));
            }
        }
        return result;
    }

}
