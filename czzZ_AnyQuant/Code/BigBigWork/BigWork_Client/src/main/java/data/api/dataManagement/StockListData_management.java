package data.api.dataManagement;

import java.util.ArrayList;

import bl.listServiceImpl.SingleStock_Impl;
import data.api.dataGetter.DataGetter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ui.main.TestConnect;
import vo.StockVO;

public class StockListData_management {

    public ArrayList<StockVO> StockList(String data) {
        // TODO Auto-generated method stub
        DataGetter getData = new DataGetter();
        String Data;

        ArrayList<StockVO> temp = new ArrayList<StockVO>();
        ArrayList<StockVO> result = new ArrayList<StockVO>();
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
