package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl.listServiceImpl.StockList_Impl;
import com.bigwork.bl_service.Hot_IndustryData_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.model.Industry;
import com.bigwork.model.Stock;
import com.bigwork.model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hot_IndustryData_Impl implements Hot_IndustryData_service {

//	private String date1;
//	private String date2;
	private SingleStock_service singleStock = new SingleStock_Impl();
	private static StockList_service list = new StockList_Impl();
	private static ArrayList<Stock> stocks = list.ShowAll();

	ArrayList<Stock> all = list.ShowAll();

	/**
	 * 默认为一个月时间
	 */
	public Hot_IndustryData_Impl() {

	}

	/**
	 * 返回一段时间内的热门行业列表
	 *
	 * @param start
	 * @param end
	 * @return Industry的ArrayList；
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Industry> GetOrderList(String start, String end) throws ParseException {
		ArrayList<Industry> finalRes = new ArrayList<Industry>();
		ArrayList<Stock> result = this.calculate(start, end);
		Collections.sort(result, new Comparator() {
			public int compare(Object o1, Object o2) {
				Stock stock1 = (Stock) o1;
				Stock stock2 = (Stock) o2;
				return ((Double)stock2.getHotValue()).compareTo((Double)stock1.getHotValue());
			}});

		for(int i = 0; i < result.size(); i++){
			Industry newIns = new Industry(result.get(i).getTypeName(), i+1);
			finalRes.add(newIns);
		}

		for(Industry insvo : finalRes){
			System.out.println(insvo.getName()+" "+insvo.getRank());
		}

		return finalRes;
	}


    /*
    *
    * 默认时间
    * */

    public ArrayList<Industry> GetOrderList() throws ParseException {
        ArrayList<Industry> finalRes = new ArrayList<Industry>();
        ArrayList<Stock> result = this.calculate();
        Collections.sort(result, new Comparator() {
            public int compare(Object o1, Object o2) {
                Stock stock1 = (Stock) o1;
                Stock stock2 = (Stock) o2;
                return ((Double)stock2.getHotValue()).compareTo((Double)stock1.getHotValue());
            }});

        for(int i = 0; i < result.size(); i++){
            Industry newIns = new Industry(result.get(i).getTypeName(), i+1);
            finalRes.add(newIns);
        }

        for(Industry insvo : finalRes){
            System.out.println(insvo.getName()+" "+insvo.getRank());
        }

        return finalRes;
    }


	/**
	 * 获取热门行业饼图所需要数据
	 * @throws ParseException
	 */
	public ObservableList<PieChart.Data> GetPieGraphData() throws ParseException {
		ArrayList<Stock> result = this.calculate();
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		for(Stock temp : result){
			data.add(new PieChart.Data(temp.getTypeName(), temp.getHotValue()));
		}
		return data;
	}

    public ObservableList<PieChart.Data> GetPieGraphData(String start, String end) throws ParseException {
        ArrayList<Stock> result = this.calculate(start, end);
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for(Stock temp : result){
            data.add(new PieChart.Data(temp.getTypeName(), temp.getHotValue()));
        }
        return data;
    }

	/**
	 * 获取热门行业条形图所需要的数据
	 * @throws ParseException
	 */
	public ObservableList<Series<String, Number>> GetBarChartData() throws ParseException {
		ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
		ObservableList<Series<String, Number>> series = FXCollections.observableArrayList();
		ArrayList<Stock> result = this.calculate();

		for(Stock temp : result){
			data.add(new XYChart.Data<String, Number>(temp.getTypeName(), temp.getHotValue()));
		}
		series.add(new Series<>("热门行业", data));
		return series;
	}

    public ObservableList<Series<String, Number>> GetBarChartData(String start, String end) throws ParseException {
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        ObservableList<Series<String, Number>> series = FXCollections.observableArrayList();
        ArrayList<Stock> result = this.calculate(start, end);

        for(Stock temp : result){
            data.add(new XYChart.Data<String, Number>(temp.getTypeName(), temp.getHotValue()));
        }
        series.add(new Series<>("热门行业", data));
        return series;
    }



	/*
	 * 计算方法： （当日涨幅在2%以上||换手率5%以上）的天数
	 *
	 * 然后以上几个值再加起来......?
	 */
	public ArrayList<Stock> calculate(String start, String end) throws ParseException {
		ArrayList<Stock> tmpResult = new ArrayList<Stock>();
		ArrayList<Stock> result = new ArrayList<Stock>();
		for(Stock single : all){
			Stock newStock;
			int goodDays = 0;
			ArrayList<Stock> tpSingle = singleStock.setTime(single.getId(), start, end);
			for(Stock tpStock : tpSingle){
				if(tpStock.getTurnover()>0.05||
						(tpStock.getClose()-tpStock.getOpen())/tpStock.getOpen()>0.02){
					goodDays++;
				}
			}
			newStock = new Stock(single.getId(), 0, 0, 0,0, 0, 0, "",0, 0, 0);
			newStock.setHotValue(goodDays);
			tmpResult.add(newStock);
//			System.out.println("Days = "+goodDays);
		}


		double medSum = 0;
		double elecSum = 0;
		double estSum = 0;
		double salesSum = 0;
		for(Stock temp : tmpResult){
			switch(temp.getType()){
			case "MED":
				medSum+=temp.getHotValue();
				break;
			case "ELEC":
				elecSum+=temp.getHotValue();
				break;
			case "ESTATE":
				estSum+=temp.getHotValue();
				break;
			case "SALES":
				salesSum+=temp.getHotValue();
				break;
			}
		}
		Stock newMed = new Stock(Type.MED, medSum);
		Stock newElec = new Stock(Type.ELEC, elecSum);
		Stock newEst = new Stock(Type.ESTATE, estSum);
		Stock newSales = new Stock(Type.SALES, salesSum);
		result.add(newMed);
		result.add(newElec);
		result.add(newEst);
		result.add(newSales);

//		for(Stock tmp : result){
//			System.out.println(tmp.getTypeName()+"  "+tmp.getHotValue());
//		}

		return result;
	}


    /*
    * 默认时间calculate
    * */
    public ArrayList<Stock> calculate() throws ParseException {
        ArrayList<Stock> tmpResult = new ArrayList<Stock>();
        ArrayList<Stock> result = new ArrayList<Stock>();
        for(Stock single : all){
            Stock newStock;
            int goodDays = 0;
            ArrayList<Stock> tpSingle = singleStock.Show(single.getId());
            for(Stock tpStock : tpSingle){
                if(tpStock.getTurnover()>0.05||
                        (tpStock.getClose()-tpStock.getOpen())/tpStock.getOpen()>0.02){
                    goodDays++;
                }
            }
            newStock = new Stock(single.getId(), 0, 0, 0,0, 0, 0, "",0, 0, 0);
            newStock.setHotValue(goodDays);
            tmpResult.add(newStock);

            System.out.println(single.getId());
			System.out.println("Days = "+goodDays);
        }


        double medSum = 0;
        double elecSum = 0;
        double estSum = 0;
        double salesSum = 0;
        for(Stock temp : tmpResult){
            switch(temp.getType()){
                case "MED":
                    medSum+=temp.getHotValue();
                    break;
                case "ELEC":
                    elecSum+=temp.getHotValue();
                    break;
                case "ESTATE":
                    estSum+=temp.getHotValue();
                    break;
                case "SALES":
                    salesSum+=temp.getHotValue();
                    break;
            }
        }
        Stock newMed = new Stock(Type.MED, medSum);
        Stock newElec = new Stock(Type.ELEC, elecSum);
        Stock newEst = new Stock(Type.ESTATE, estSum);
        Stock newSales = new Stock(Type.SALES, salesSum);
        result.add(newMed);
        result.add(newElec);
        result.add(newEst);
        result.add(newSales);

        return result;
    }

	public static void main(String[] args) throws ParseException{
		Hot_IndustryData_Impl test = new Hot_IndustryData_Impl();
//		test.calculate();
		test.GetOrderList();
	}


}


