package com.bigwork.data.dataServiceImpl;

import com.bigwork.data_service.GrailListData_service;
import com.bigwork.model.Grail;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.util.ArrayList;

public class GrailListData_Impl implements GrailListData_service {

    @Override
    public ArrayList<Grail> GrailList(String market_id,String datefrom, String dateTo) throws SqlImplNullPointerException, SQLException {
        // TODO Auto-generated method stub
        ArrayList<Grail> result = new ArrayList<Grail>();
        MysqlLink link = new MysqlLink();
        link.link("114.212.43.14", "root", "141250185");
        result = link.getMarket_Impl().selectArray(market_id,datefrom,dateTo);
        return result;
    }

}
