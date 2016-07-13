package bl_service;

import java.util.ArrayList;

public interface Fluctuation_service {

    /**
     * 获取一段时间内的某只股票的历史波动率
     *
     * @param ID
     * @param start
     * @param end
     * @return
     */


    /**
     * 根据自定义时间计算波动率
     * */
    public double getFluctuation(String start, String end, int year);

    /**
     * 根据 输入天数 和 年份计算
     * */
    public double getFluctuation(int days, int year);

    /**
     * 根据 type 计算波动率
     * */
    public double getFluctuation(String type, int year);

    public void setDate(String start, String end);

    public void setLength(String type);

    public int getPeriod();

//    public double getRate(String ID, String start, String end);

    public ArrayList<Double> getRateList();

    public int getCount();

}
