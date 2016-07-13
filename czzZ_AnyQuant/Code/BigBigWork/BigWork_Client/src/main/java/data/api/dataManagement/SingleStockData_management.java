package data.api.dataManagement;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.StockVO;
import data_service.SingleStockData_service;

public class SingleStockData_management {

    public ArrayList<StockVO> SingleStock(String data) {
        // TODO Auto-generated method stub
        String Name;

        ArrayList<StockVO> result = new ArrayList<StockVO>();


        JSONObject jsonObj;
        JSONArray jsonarray;
        jsonObj = JSONObject.fromObject(data);
        jsonarray = JSONArray.fromObject(jsonObj.get("data"));
        //System.out.println(jsonarray.getString(0));

        jsonObj = JSONObject.fromObject(jsonarray.getString(0));
        Name = (String) jsonObj.get("name");
        //System.out.println(Name);
        //System.out.println(jsonObj.get("trading_info"));

        jsonarray = JSONArray.fromObject(jsonObj.get("trading_info"));
        Object[] obj = jsonarray.toArray();
        //System.out.println(obj.length);
        for (int i = 0; i < obj.length; i++) {
            jsonObj = JSONObject.fromObject(jsonarray.getString(i));
            StockVO tempVO = new StockVO(Name, Integer.parseInt(jsonObj.getString("volume")), Double.parseDouble(jsonObj.getString("pb")),
                    Double.parseDouble(jsonObj.getString("high")), Double.parseDouble(jsonObj.getString("pe_ttm")),
                    Double.parseDouble(jsonObj.getString("adj_price")), Double.parseDouble(jsonObj.getString("low")),
                    jsonObj.getString("date"), Double.parseDouble(jsonObj.getString("close")), Double.parseDouble(jsonObj.getString("open")),
                    Double.parseDouble(jsonObj.getString("turnover")));
            //System.out.println(jsonObj.getString("volume"));
            result.add(tempVO);
        }
        return result;
    }

}
