package com.bigwork.data.dataServiceImpl;


import com.bigwork.data.dataHelper.FileHelper;
import com.bigwork.data_service.StockListData_service;
import com.bigwork.model.Stock;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockListData_Impl implements StockListData_service {


    public ArrayList<Stock> StockList() throws SqlImplNullPointerException, SQLException {
        // TODO Auto-generated method stub
        ArrayList<Stock> result = new ArrayList<Stock>();

        MysqlLink link = new MysqlLink();
        link.link("114.212.43.14", "root", "141250185");
        result = link.getStockList_Impl().selectArray();
        return result;
    }

}
