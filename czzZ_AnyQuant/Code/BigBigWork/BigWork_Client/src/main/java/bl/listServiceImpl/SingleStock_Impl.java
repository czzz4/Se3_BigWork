package bl.listServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import data.api.dataGetter.DataGetter;
import data.api.dataManagement.SingleStockData_management;
import data.dataServiceImpl.SingleStockData_Impl;
import data_service.SingleStockData_service;
import ui.main.TestConnect;
import vo.StockVO;
import bl_service.SingleStock_service;

public class SingleStock_Impl implements SingleStock_service {

    private DataGetter getter = new DataGetter();
    private SingleStockData_management singleDataImpl = new SingleStockData_management();

    public ArrayList<StockVO> Show(String id) {
        SingleStockData_service singleStockData = new SingleStockData_Impl();
        return singleStockData.SingleStock(id);
    }

    public void close() {
        // TODO Auto-generated method stub

    }


    //界面没有实现这个功能？
    private ArrayList<StockVO> SetTime(String id, String startTime, String endTime) {
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        String realEnd = "";
        System.out.println("Out");
        //返回结果包括最后一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date end = sdf.parse(endTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(end);
            cal.add(Calendar.DATE, 1);
            realEnd = sdf.format(cal.getTime());
            System.out.println(realEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String url = "http://121.41.106.89:8010/api/stock/"
                + id + "/?start=" + startTime + "&end="
                + realEnd;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);

            result = singleDataImpl.SingleStock(temp);
            return result;
        }
        return null;
    }

    public ArrayList<StockVO> setTime(String id, String startTime, String endTime) throws ParseException {
        SingleStockData_service singleStockData = new SingleStockData_Impl();
        ArrayList<StockVO> result = singleStockData.SingleStock(id);

        ArrayList<StockVO> R = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startTime);
        Date endDate = sdf.parse(endTime);
        Date every = new Date();
        Date line = sdf.parse("2016-03-01");
        if ((startDate.getTime() - line.getTime()) > 0) {
            System.out.println("In");
            for (StockVO stockvo : result) {
                every = sdf.parse(stockvo.getDate());
                if ((every.getTime() - startDate.getTime() > 0) && (every.getTime() - endDate.getTime() < 0)) {
                    R.add(stockvo);
                }
            }
        } else {
            R = this.SetTime(id, startTime, endTime);
        }

        return R;
    }

//	public static void main(String[] args){
//		SingleStock_Impl test = new SingleStock_Impl();
//		ArrayList<StockVO> res = test.setTime("sh600000", "2016-02-23", "2016-02-23");
//		for(StockVO vo : res){
//			System.out.println(vo.getClose());
//		}
//	}

}