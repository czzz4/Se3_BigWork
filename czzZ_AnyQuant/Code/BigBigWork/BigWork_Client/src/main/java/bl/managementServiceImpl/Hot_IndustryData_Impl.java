package bl.managementServiceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bl.listServiceImpl.SingleStock_Impl;
import bl.listServiceImpl.StockList_Impl;
import bl_service.Hot_IndustryData_service;
import bl_service.SingleStock_service;
import bl_service.StockList_service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import vo.IndustryVO;
import vo.StockVO;
import vo.Type;

public class Hot_IndustryData_Impl implements Hot_IndustryData_service{

//	private String date1;
//	private String date2;
	private SingleStock_service singleStock = new SingleStock_Impl();
	private static StockList_service list = new StockList_Impl();
	private static ArrayList<StockVO> stocks = list.ShowAll();

	ArrayList<StockVO> all = list.ShowAll();

//	public Hot_IndustryData_Impl(String date1, String date2) {
//		this.date1 = date1;
//		this.date2 = date2;
//	}

	/**
	 * 默认为一个月时间
	 */
	public Hot_IndustryData_Impl() {

	}

//	public void setTime(String start, String end){
//		date1 = start;
//		date2 = end;
//	}

	/**
	 * 返回一段时间内的热门行业列表
	 *
	 * @param start
	 * @param end
	 * @return IndustryVO的ArrayList；
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<IndustryVO> GetOrderList(String start, String end) throws ParseException {
		ArrayList<IndustryVO> finalRes = new ArrayList<IndustryVO>();
		ArrayList<StockVO> result = this.calculate(start, end);
		Collections.sort(result, new Comparator() {
			public int compare(Object o1, Object o2) {
				StockVO stock1 = (StockVO) o1;
				StockVO stock2 = (StockVO) o2;
				return ((Double)stock2.getHotValue()).compareTo((Double)stock1.getHotValue());
			}});

		for(int i = 0; i < result.size(); i++){
			IndustryVO newIns = new IndustryVO(result.get(i).getTypeName(), i+1);
			finalRes.add(newIns);
		}

		for(IndustryVO insvo : finalRes){
			System.out.println(insvo.getName()+" "+insvo.getRank());
		}

		return finalRes;
	}


    /*
    *
    * 默认时间
    * */

    public ArrayList<IndustryVO> GetOrderList() throws ParseException {
        ArrayList<IndustryVO> finalRes = new ArrayList<IndustryVO>();
        ArrayList<StockVO> result = this.calculate();
        Collections.sort(result, new Comparator() {
            public int compare(Object o1, Object o2) {
                StockVO stock1 = (StockVO) o1;
                StockVO stock2 = (StockVO) o2;
                return ((Double)stock2.getHotValue()).compareTo((Double)stock1.getHotValue());
            }});

        for(int i = 0; i < result.size(); i++){
            IndustryVO newIns = new IndustryVO(result.get(i).getTypeName(), i+1);
            finalRes.add(newIns);
        }

        for(IndustryVO insvo : finalRes){
            System.out.println(insvo.getName()+" "+insvo.getRank());
        }

        return finalRes;
    }


	/**
	 * 获取热门行业饼图所需要数据
	 * @throws ParseException
	 */
	public ObservableList<PieChart.Data> GetPieGraphData() throws ParseException {
		ArrayList<StockVO> result = this.calculate();
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		for(StockVO temp : result){
			data.add(new PieChart.Data(temp.getTypeName(), temp.getHotValue()));
		}
		return data;
	}

    public ObservableList<PieChart.Data> GetPieGraphData(String start, String end) throws ParseException {
        ArrayList<StockVO> result = this.calculate(start, end);
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for(StockVO temp : result){
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
		ArrayList<StockVO> result = this.calculate();

		for(StockVO temp : result){
			data.add(new XYChart.Data<String, Number>(temp.getTypeName(), temp.getHotValue()));
		}
		series.add(new Series<>("热门行业", data));
		return series;
	}

    public ObservableList<Series<String, Number>> GetBarChartData(String start, String end) throws ParseException {
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        ObservableList<Series<String, Number>> series = FXCollections.observableArrayList();
        ArrayList<StockVO> result = this.calculate(start, end);

        for(StockVO temp : result){
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
	public ArrayList<StockVO> calculate(String start, String end) throws ParseException {
		ArrayList<StockVO> tmpResult = new ArrayList<StockVO>();
		ArrayList<StockVO> result = new ArrayList<StockVO>();
		for(StockVO single : all){
			StockVO newStock;
			int goodDays = 0;
			ArrayList<StockVO> tpSingle = singleStock.setTime(single.getId(), start, end);
			for(StockVO tpStock : tpSingle){
				if(tpStock.getTurnover()>0.05||
						(tpStock.getClose()-tpStock.getOpen())/tpStock.getOpen()>0.02){
					goodDays++;
				}
			}
			newStock = new StockVO(single.getId(), 0, 0, 0,0, 0, 0, "",0, 0, 0);
			newStock.setHotValue(goodDays);
			tmpResult.add(newStock);
//			System.out.println("Days = "+goodDays);
		}


		double medSum = 0;
		double elecSum = 0;
		double estSum = 0;
		double salesSum = 0;
		for(StockVO temp : tmpResult){
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
		StockVO newMed = new StockVO(Type.MED, medSum);
		StockVO newElec = new StockVO(Type.ELEC, elecSum);
		StockVO newEst = new StockVO(Type.ESTATE, estSum);
		StockVO newSales = new StockVO(Type.SALES, salesSum);
		result.add(newMed);
		result.add(newElec);
		result.add(newEst);
		result.add(newSales);

//		for(StockVO tmp : result){
//			System.out.println(tmp.getTypeName()+"  "+tmp.getHotValue());
//		}

		return result;
	}


    /*
    * 默认时间calculate
    * */
    public ArrayList<StockVO> calculate() throws ParseException {
        ArrayList<StockVO> tmpResult = new ArrayList<StockVO>();
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        for(StockVO single : all){
            StockVO newStock;
            int goodDays = 0;
            ArrayList<StockVO> tpSingle = singleStock.Show(single.getId());
            for(StockVO tpStock : tpSingle){
                if(tpStock.getTurnover()>0.05||
                        (tpStock.getClose()-tpStock.getOpen())/tpStock.getOpen()>0.02){
                    goodDays++;
                }
            }
            newStock = new StockVO(single.getId(), 0, 0, 0,0, 0, 0, "",0, 0, 0);
            newStock.setHotValue(goodDays);
            tmpResult.add(newStock);

            System.out.println(single.getId());
			System.out.println("Days = "+goodDays);
        }


        double medSum = 0;
        double elecSum = 0;
        double estSum = 0;
        double salesSum = 0;
        for(StockVO temp : tmpResult){
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
        StockVO newMed = new StockVO(Type.MED, medSum);
        StockVO newElec = new StockVO(Type.ELEC, elecSum);
        StockVO newEst = new StockVO(Type.ESTATE, estSum);
        StockVO newSales = new StockVO(Type.SALES, salesSum);
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


