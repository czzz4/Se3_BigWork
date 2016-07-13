package bl.managementServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bl.listServiceImpl.SingleStock_Impl;
//import org.python.util.PythonInterpreter;

import vo.StockVO;
//import bl.ListserviceImpl.SingleStock_Impl;
import bl_service.Fluctuation_service;
import bl_service.SingleStock_service;


/*
 * 下面以计算股票的历史波动率为例加以说明。   
 *  1、从市场上获得标的股票在固定时间间隔(如每天、每周或每月等)上的价格。    
 *  2、对于每个时间段，求出该时间段末的股价与该时段初的股价之比的自然对数。    
 *  3、求出这些对数值的标准差，再乘以一年中包含的时段数量的平方根(如，选取时间间隔为每天，
 *  	则若扣除闭市，每年中有250个交易日，应乘以根号250)，得到的即为历史波动率。
 */

public class Fluctuation_Impl implements Fluctuation_service {

    private SingleStock_service singleStock = new SingleStock_Impl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public int getPeriod(String start, String end) {
//		double result = 0;
        int dayNum = 0;        // get the number of days
//		PythonInterpreter interpreter = new PythonInterpreter(); 
//		ArrayList<StockVO> list = singleStock.setTime(ID, start, end);
        Calendar cal = Calendar.getInstance();
        Date startDate;
        Date endDate;
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
            cal.setTime(startDate);
            while (cal.getTime().before(endDate)) {
                if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                        cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    dayNum++;    //节假日我就先不管了orz（春节什么的要怎么弄？再找个日期的API？）
                    cal.add(Calendar.DATE, 1);    // move to the next day
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dayNum;
    }


    public double getRate(String ID, String start, String end) {

        SingleStock_service getter = new SingleStock_Impl();
        Calendar cal = Calendar.getInstance();
        Date res = cal.getTime();
        int dayNum = this.getPeriod(start, end);
        int perNum = 0;    // counting the number of periods

        ArrayList<Double> tmpResult = new ArrayList<Double>();
        Date endDate;
        Date ptr;    // points to the start of each period
        try {
            endDate = sdf.parse(end);
//			System.out.println(sdf.format(res));

            ptr = sdf.parse(start);    //	Date pointer to each start of a period
            cal.setTime(ptr);
            while (cal.getTime().before(endDate)) {
                for (int cnt = 0; cnt < dayNum; cnt++) {
                    double startVal = 0;
                    double endVal = 0;
                    double tmpcal = 0;
//					StockVO tmpVO;
                    if (cnt == 0) {
                        startVal = getter.setTime(ID, sdf.format(cal.getTime()),
                                sdf.format(cal.getTime())).get(0).getAdj();
                    }
                    if (cnt == dayNum - 1 && cal.getTime().before(endDate)) {
                        endVal = getter.setTime(ID, sdf.format(cal.getTime()),
                                sdf.format(cal.getTime())).get(0).getAdj();

                        tmpcal = Math.log(endVal / startVal);
                        tmpResult.add((Double) tmpcal);
                        ptr = cal.getTime();    // updates pointer
                    } else if (!cal.getTime().before(endDate)) {
                        break;
                    }
                    cal.add(Calendar.DATE, 1);
                }
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    public double getRate(String ID, String type) {

        return 0;
    }


    public static void main(String[] args) {
        Fluctuation_Impl fluc = new Fluctuation_Impl();
        fluc.getRate("sh600979", "2016-03-12", "2016-04-09");
    }

}
