package data.dataServiceImpl;

import java.util.ArrayList;

import data.dataHelper.FileHelper;
import data_service.StockListData_service;
import vo.StockVO;

public class StockListData_Impl implements StockListData_service {

    @Override
    public ArrayList<StockVO> StockList() {
        // TODO Auto-generated method stub
        ArrayList<String> re = new ArrayList<String>();
        ArrayList<StockVO> result = new ArrayList<StockVO>();

        FileHelper helper = new FileHelper("file" + "/" + "StockList.txt");
        re = helper.read();

        for (String string : re) {
            result.add(new StockVO(string));
        }
        return result;
    }

}
