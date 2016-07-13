package bl_service;

import java.text.ParseException;

public interface KGraphData_service {
    /**
     * 获取K线图所需要的数据
     *
     * @return
     * @throws ParseException
     */
    public double[][] GetKGraphData() throws ParseException;

    public int getDay();

    public void setDate(String date1, String date2);

    public String[] getDate();
}