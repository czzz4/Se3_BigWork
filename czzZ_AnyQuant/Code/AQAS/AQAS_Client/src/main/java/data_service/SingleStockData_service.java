package data_service;

import java.util.ArrayList;

import vo.StockVO;

public interface SingleStockData_service {

    /**
     * 处理单支股票原始数据，以Arraylist返回给bl
     * 每个VO里存储的是同一股票每一天的信息
     * time默认为一个月
     *
     * @return
     */
    public ArrayList<StockVO> SingleStock(String id);


}
