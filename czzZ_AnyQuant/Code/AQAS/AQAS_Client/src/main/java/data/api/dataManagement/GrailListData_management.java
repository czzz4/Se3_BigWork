package data.api.dataManagement;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.GrailVO;
import data_service.GrailListData_service;

public class GrailListData_management {

    public ArrayList<GrailVO> GrailList(String data) {
        // TODO Auto-generated method stub
        String Name;

        ArrayList<GrailVO> result = new ArrayList<GrailVO>();

        JSONObject jsonObj;
        JSONArray jsonarray;
        jsonObj = JSONObject.fromObject(data);
        jsonarray = JSONArray.fromObject(jsonObj.get("data"));

        jsonObj = JSONObject.fromObject(jsonarray.getString(0));
        Name = (String) jsonObj.get("name");

        jsonarray = JSONArray.fromObject(jsonObj.get("trading_info"));
        Object[] obj = jsonarray.toArray();
        for (int i = 0; i < obj.length; i++) {
            jsonObj = JSONObject.fromObject(jsonarray.getString(i));
            GrailVO tempVO = new GrailVO(Name, jsonObj.getString("date"), Double.parseDouble(jsonObj.getString("high")),
                    Double.parseDouble(jsonObj.getString("open")), Double.parseDouble(jsonObj.getString("close")),
                    jsonObj.getString("volume"), Double.parseDouble(jsonObj.getString("adj_price")),
                    Double.parseDouble(jsonObj.getString("low")));
            //System.out.println(jsonObj.getString("volume"));
            result.add(tempVO);
        }
        return result;
    }


}
