package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.model.Grail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/21.
 */
@Controller
public class marketController {

    @RequestMapping(value = "/marketList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Grail> marketList(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();
        return list;
    }

    @RequestMapping(value = "/marketListByTime", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Grail> marketListByTime(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.setTime(startTime,endTime);
        return list;
    }

    //大盘KDJ值
    @RequestMapping(value = "/KDJ", method = RequestMethod.GET)
    @ResponseBody
    public Double[] KDJ(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();

        Double[] kdj = {1.0,2.0,3.0};
        return kdj;
    }

    //最新一天大盘数据
    @RequestMapping(value = "/market", method = RequestMethod.GET)
    @ResponseBody
    public Grail market(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();
        int l = list.size();
        return list.get(l-1);
    }

    //涨跌幅
    @RequestMapping(value = "/applies", method = RequestMethod.GET)
    @ResponseBody
    public Double applies(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();

        return 3.14;
    }
}
