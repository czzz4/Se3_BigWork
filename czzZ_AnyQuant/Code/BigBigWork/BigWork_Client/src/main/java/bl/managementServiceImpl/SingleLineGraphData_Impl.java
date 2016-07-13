package bl.managementServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bl.listServiceImpl.SingleStock_Impl;
import data.dataHelper.FileHelper;
import data.dataHelper.SingleStockFileFinder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import bl_service.SingleLineGraphData_service;
import bl_service.SingleStock_service;
import vo.StockVO;

public class SingleLineGraphData_Impl implements SingleLineGraphData_service{

	private String ID;
	// private String start;
	// private String end;

	private FileHelper helper;
	private SingleStockFileFinder finder;
	private SingleStock_service singleStock = new SingleStock_Impl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public SingleLineGraphData_Impl(){

	}

	public SingleLineGraphData_Impl(String ID) {
		finder = new SingleStockFileFinder();

		helper = new FileHelper(finder.getFilePath(ID));
		this.ID = ID;
	}

	public void setID(String id){
		ID = id;
	}

	/**
	 * 1. 获取单只股票折线图所需要的数据（默认时间） 2. 同上＋自定义时间
	 *
	 * （成交金额＝成交价格＊成交量？……于是还是用那个带Name的API了因为我觉得这个应该算不出来＝＝）
	 */
	public void GetSingleLineGraphData() {


	}


	public ObservableList<Series<Date, Number>> getVolData(String start, String end) throws ParseException{

		ObservableList<Series<Date, Number>> series = FXCollections.observableArrayList();
		ObservableList<Data<Date, Number>> data = FXCollections.observableArrayList();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<StockVO> list = singleStock.setTime(ID, start, end);

		for(StockVO item : list){
			try {
				System.out.println(sdf.parse(item.getDate())+"  "+(Number)item.getVolume());
				data.add(new Data<Date, Number> (sdf.parse(item.getDate()), (Number)item.getVolume()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		series.add(new Series<>(ID+"成交量", data));
		return series;
	}


	public ObservableList<Series<Date, Number>> getDealData(String start, String end) throws ParseException{
		ObservableList<Series<Date, Number>> series = FXCollections.observableArrayList();
		ObservableList<Data<Date, Number>> data = FXCollections.observableArrayList();
		Date curr = null;
		ArrayList<StockVO> list = singleStock.setTime(ID, start, end);
		for(StockVO item : list){
			try {
				System.out.println(sdf.parse(item.getDate())+"  "+(Number)item.getDealSum());
				data.add(new Data<Date, Number> (sdf.parse(item.getDate()), (Number)item.getDealSum()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		series.add(new Series<>(ID+"成交金额", data));
		return series;
	}

	/**
	 * 返回振幅，涨跌幅（等？）数据
	 */
	public void GetStockOffset() {

	}


	public ObservableList<Series<Date, Number>> getVolData(){
		ObservableList<Series<Date, Number>> series = FXCollections.observableArrayList();
		ObservableList<Data<Date, Number>> data = FXCollections.observableArrayList();

		ArrayList<StockVO> list = singleStock.Show(ID);

        for(StockVO item : list){
            try {
                System.out.println(sdf.parse(item.getDate())+"  "+(Number)item.getDealSum());
                data.add(new Data<Date, Number> (sdf.parse(item.getDate()), (Number)item.getVolume()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

		series.add(new Series<>(ID+"成交量", data));
		return series;
	}

	public ObservableList<Series<Date, Number>> getDealData(){
		ObservableList<Series<Date, Number>> series = FXCollections.observableArrayList();
		ObservableList<Data<Date, Number>> data = FXCollections.observableArrayList();

		SingleStock_service single = new SingleStock_Impl();
		ArrayList<StockVO> list = singleStock.Show(ID);

        for(StockVO item : list){
            try {
                System.out.println(sdf.parse(item.getDate())+"  "+(Number)item.getDealSum());
                data.add(new Data<Date, Number> (sdf.parse(item.getDate()), (Number)item.getDealSum()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

		series.add(new Series<>(ID+"成交金额", data));
		return series;
	}

    public String[] getDates(){
        String[] pair = new String[2];
        ArrayList<StockVO> list = singleStock.Show(ID);
        for(int i = 0; i<list.size(); i++){
            if(i == 0){
                pair[0] = list.get(i).getDate();
            }
            if(i == list.size()-1){
                pair[1] = list.get(i).getDate();
            }
        }
        return pair;
    }

}
