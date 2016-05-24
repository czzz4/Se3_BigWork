package com.bigwork.data.dataServiceImpl;


import com.bigwork.data.dataHelper.FileHelper;
import com.bigwork.data.dataHelper.SingleStockFileFinder;
import com.bigwork.data_service.SingleStockData_service;
import com.bigwork.model.Stock;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.util.ArrayList;

public class SingleStockData_Impl implements SingleStockData_service {

    private SingleStockFileFinder finder = new SingleStockFileFinder();

    public ArrayList<Stock> SingleStock(String id,String datefrom, String dateTo) throws SqlImplNullPointerException, SQLException {
        // TODO Auto-generated method stub
        ArrayList<Stock> result = new ArrayList<Stock>();

        MysqlLink link = new MysqlLink();
        link.link("114.212.43.14", "root", "141250185");
        result = link.getStock_Impl().selectArray(id,datefrom,dateTo);
        return result;
    }

}