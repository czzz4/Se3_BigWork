package com.bigwork.bl_service;

import com.bigwork.model.Industry;
import com.bigwork.model.Stock;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.Series;

import java.text.ParseException;
import java.util.ArrayList;

public interface Hot_IndustryData_service {


    /**
     * 返回一段时间内的热门行业列表
     *
     * @return IndustryVO的ArrayList；StockVO中只有TypeName和HotValue屬性
     * @throws ParseException
     */
    public ArrayList<Industry> GetOrderList() throws ParseException;

    public ArrayList<Industry> GetOrderList(String start, String end) throws ParseException;


    /**
     * 获取热门行业饼图所需要数据
     *
     * @throws ParseException
     */
    public ObservableList<PieChart.Data> GetPieGraphData() throws ParseException;

    public ObservableList<PieChart.Data> GetPieGraphData(String start, String end) throws ParseException;

    /**
     * 获取热门行业条形图所需要的数据
     *
     * @throws ParseException
     */
    public ObservableList<Series<String, Number>> GetBarChartData() throws ParseException;

    public ObservableList<Series<String, Number>> GetBarChartData(String start, String end) throws ParseException;


    /*
     * 计算方法：
     * 	1.当日涨幅在2%以上
     *  2.当日成交量明显放大(?)
     *  3.换手率5%以上
     *
     *  然后以上几个值再加起来......?
     * */
    public ArrayList<Stock> calculate() throws ParseException;
}
