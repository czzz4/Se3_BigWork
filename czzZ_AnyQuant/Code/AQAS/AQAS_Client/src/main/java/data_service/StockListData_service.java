package data_service;

import java.util.ArrayList;

import vo.StockVO;

public interface StockListData_service {

    /**
     * 处理股票列表原始数据，以Arraylist返回给bl
     * 每个VO里存储的是不同支股票的信息
     *
     * @return
     */
    public ArrayList<StockVO> StockList();

}
