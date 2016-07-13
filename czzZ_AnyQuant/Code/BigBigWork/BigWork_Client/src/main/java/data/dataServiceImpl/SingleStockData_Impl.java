package data.dataServiceImpl;

import java.util.ArrayList;

import data.dataHelper.FileHelper;
import data.dataHelper.SingleStockFileFinder;
import data_service.SingleStockData_service;
import vo.StockVO;

public class SingleStockData_Impl implements SingleStockData_service {

    private SingleStockFileFinder finder = new SingleStockFileFinder();

    @Override
    public ArrayList<StockVO> SingleStock(String id) {
        // TODO Auto-generated method stub
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        ArrayList<String> re = new ArrayList<String>();

        System.out.println(finder.getFilePath(id));
        FileHelper helper = new FileHelper(finder.getFilePath(id));
        re = helper.read();

        for (String string : re) {
            result.add(new StockVO(string));
        }
        return result;
    }

}