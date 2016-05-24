package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl.listServiceImpl.StockList_Impl;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.model.Grail;
import com.bigwork.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/24.
 */
@Controller
public class StockListController {

    //股票列表
    @RequestMapping(value = "/StockList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> StockList(){
        ArrayList<Stock> list = new ArrayList<>();
        StockList_service stockList = new StockList_Impl();
        list = stockList.ShowAll();
        return list;
    }

    //根据筛选条件筛选
    @RequestMapping(value = "/StockFilt", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> StockFilt(@RequestParam("Hadj_price")double Hadj_price,@RequestParam("Ladj_price")double Ladj_price,
                                      @RequestParam("Hvolume")int Hvolume,@RequestParam("Lvolume")int Lvolume ,
                                      @RequestParam("Hturnover")double Hturnover,@RequestParam("Lturnover")double Lturnover,
                                      @RequestParam("Hpe")double Hpe,@RequestParam("Lpe")double Lpe,
                                      @RequestParam("Hpd")double Hpd,@RequestParam("Lpd")double Lpd){
        ArrayList<Stock> list = new ArrayList<>();
        Stock LOW = new Stock(Ladj_price,Lvolume,Lturnover,Lpe,Lpd);
        Stock HIGH = new Stock(Hadj_price,Hvolume,Hturnover,Hpe,Hpd);
        StockList_service stockList = new StockList_Impl();
        list = stockList.filter(LOW,HIGH);
        return list;
    }

}
