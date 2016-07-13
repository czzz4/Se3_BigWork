package bl.managementServiceImpl;

import java.lang.reflect.Array;
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


/**
 * 下面以计算股票的历史波动率为例加以说明。   
 *  1、从市场上获得标的股票在固定时间间隔(如每天、每周或每月等)上的价格。    
 *  2、对于每个时间段，求出该时间段末的股价与该时段初的股价之比的自然对数。    
 *  3、求出这些对数值的标准差，再乘以一年中包含的时段数量的平方根(如，选取时间间隔为每天，
 *  	则若扣除闭市，每年中有250个交易日，应乘以根号250)，得到的即为历史波动率。
 */

public class Fluctuation_Impl implements Fluctuation_service {

    private SingleStock_service singleStock = new SingleStock_Impl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private Date startDate = null;
    private Date endDate = null;

    private int length;

    private String ID;

    /**
     * 设置股票ID
     * */
    public void setID(String id){
        ID = id;
    }

    /**
     * 根据自定义时间计算波动率
     * */
    public double getFluctuation(String start, String end, int year){
        double result = 0.0;
        int num = 0;
        this.setDate(start, end);
        length = this.getPeriod();
        num = this.getCount();
        String newStart = String.valueOf(year) + "-01-01";
        String newEnd = String.valueOf(year) + "-12-31";
        this.setDate(newStart, newEnd);

//        System.out.println("start " + startDate+"  end "+endDate);
        ArrayList<Double> list = this.getRateList();

        double var = this.getVar(list);
        result = var * Math.pow(num, 0.5);
        return result;
    }


    /**
     * 根据 输入天数 和 年份计算
     * */
    public double getFluctuation(int days, int year){
        String start = String.valueOf(year) + "-01-01";
        String end = String.valueOf(year) + "-12-31";
        int num = 0;
        this.setDate(start, end);
        double result = 0.0;
        length = days;
//        System.out.println("LEN = "+length);
        num = this.getCount();
        ArrayList<Double> list = this.getRateList();

        double var = this.getVar(list);
        result = var * Math.pow(num, 0.5);
        return result;
    }


    /**
     * 根据 type 计算波动率
     * */
    public double getFluctuation(String type, int year){
        String start = String.valueOf(year) + "-01-01";
        String end = String.valueOf(year) + "-12-31";
        int num = 0;
        this.setDate(start, end);
        double result = 0.0;
        this.setLength(type);
//        System.out.println("LEN = "+length);
        num = this.getCount();
        ArrayList<Double> list = this.getRateList();

        double var = this.getVar(list);
        result = var * Math.pow(num, 0.5);
        return result;
    }



    /**
     * 设置日期
     */
    public void setDate(String start, String end){
        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 通过 月/周/日 setLength
     * */
    public void setLength(String type){
        switch (type) {
            case "month":
                length = 30;
                break;
            case "week":
                length = 5;
                break;
            case "season":
                length = 62;
                break;
        }
    }


    /**
    * 计算所选时段内交易日的天数
     * */
    public int getPeriod() {
        int dayNum = 0;        // get the number of days
//		PythonInterpreter interpreter = new PythonInterpreter(); 
//		ArrayList<StockVO> list = singleStock.setTime(ID, start, end);
        Calendar cal = Calendar.getInstance();

        cal.setTime(startDate);
        while (cal.getTime().before(endDate)) {
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                    cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                dayNum++;    //节假日我就先不管了orz（春节什么的要怎么弄.......）
            }
            cal.add(Calendar.DATE, 1);    // move to the next day
        }

        return dayNum;
    }


    /**
    * 计算一年内所选时段的数量
    * */
    public int getCount(){
        return 250 / length;    // 250工作日天数
    }



    public ArrayList<Double> getRateList(){
        ArrayList<Double> result = new ArrayList<Double>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int count = 0;
        double startVal = 1;
        double endVal = 1;
        double finVal = 1;
        while(cal.getTime().before(endDate)){
            if(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                    cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){  // only counts weekdays
                System.out.println("today is " + cal.get(Calendar.DAY_OF_WEEK));

                if(count == 1){
                    System.out.println("equals 0");
                    try {
//                    System.out.println("size = " + singleStock.setTime(ID, sdf.format(cal.getTime()),
//                            sdf.format(cal.getTime())).size());
                        if(singleStock.setTime(ID, sdf.format(cal.getTime()),
                                sdf.format(cal.getTime())).size() != 0) {
                            startVal = singleStock.setTime(ID, sdf.format(cal.getTime()),
                                    sdf.format(cal.getTime())).get(0).getAdj();
                            System.out.println("startVal: " + startVal);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(count == length){
                    System.out.println("count = "+ count);
                    count = 0;
                    try {
                        if(singleStock.setTime(ID, sdf.format(cal.getTime()),
                            sdf.format(cal.getTime())).size() != 0) {
                            endVal = singleStock.setTime(ID, sdf.format(cal.getTime()),
                                sdf.format(cal.getTime())).get(0).getAdj();
                            finVal = Math.log(Math.abs(endVal / startVal));
                            System.out.println("endVal: " + endVal);

                            result.add(finVal);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                count++;
//                cal.add(Calendar.DATE, 1);
            }
            cal.add(Calendar.DATE, 1);
        }

        return result;
    }


//    public double getRate(String ID, String start, String end) {
//
//        SingleStock_service getter = new SingleStock_Impl();
//        Calendar cal = Calendar.getInstance();
//        Date res = cal.getTime();
//        int dayNum = this.getPeriod(start, end);
//        int perNum = 0;    // counting the number of periods
//
//        ArrayList<Double> tmpResult = new ArrayList<Double>();
//        Date endDate;
//        Date ptr;    // points to the start of each period
//        try {
//            endDate = sdf.parse(end);
////			System.out.println(sdf.format(res));
//
//            ptr = sdf.parse(start);    //	Date pointer to each start of a period
//            cal.setTime(ptr);
//            while (cal.getTime().before(endDate)) {
//                for (int cnt = 0; cnt < dayNum; cnt++) {
//                    double startVal = 0;
//                    double endVal = 0;
//                    double tmpcal = 0;
////					StockVO tmpVO;
//                    if (cnt == 0) {
//                        startVal = getter.setTime(ID, sdf.format(cal.getTime()),
//                                sdf.format(cal.getTime())).get(0).getAdj();
//                    }
//                    if (cnt == dayNum - 1 && cal.getTime().before(endDate)) {
//                        endVal = getter.setTime(ID, sdf.format(cal.getTime()),
//                                sdf.format(cal.getTime())).get(0).getAdj();
//
//                        tmpcal = Math.log(endVal / startVal);
//                        tmpResult.add((Double) tmpcal);
//                        ptr = cal.getTime();    // updates pointer
//                    } else if (!cal.getTime().before(endDate)) {
//                        break;
//                    }
//                    cal.add(Calendar.DATE, 1);
//                }
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
//    }


    public double getAvg(ArrayList<Double> list){
        double total = 0.0;
        for(int i = 0; i<list.size(); i++){
            total += list.get(i);
        }
        return total/list.size();
    }

    public double getVar(ArrayList<Double> list){
        double var = 0.0;
        double avg = this.getAvg(list);
        for(int i = 0; i<list.size(); i++){
            var += Math.pow((list.get(i) - avg), 2);
        }
        var /= list.size();
        return var;
    }

    public static void main(String[] args) {
        Fluctuation_Impl fluc = new Fluctuation_Impl();
//        fluc.getRate("sh600979", "2016-03-12", "2016-04-09");
        fluc.setID("sh600979");
//        System.out.println(fluc.getFluctuation("month", 2014));
        System.out.println(fluc.getFluctuation("2015-01-19", "2015-02-01", 2015));
//        System.out.println(fluc.getFluctuation(7, 2014));
    }

}
