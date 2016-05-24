package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.StockList_Impl;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.model.Industry;
import com.bigwork.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/24.
 */
@Controller
public class Hot_IndustryController {

    //热门股票列表
    @RequestMapping(value = "/HotStockList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> HotStockList(){
        ArrayList<Stock> list = new ArrayList<>();
        StockList_service stockList = new StockList_Impl();
        list = stockList.ShowAll();
        return list;
    }

    //热门行业列表
    @RequestMapping(value = "/StockList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Industry> StockList(){
        ArrayList<Industry> list = new ArrayList<>();
        StockList_service stockList = new StockList_Impl();
        //list = stockList.ShowAll();
        return list;
    }
}
