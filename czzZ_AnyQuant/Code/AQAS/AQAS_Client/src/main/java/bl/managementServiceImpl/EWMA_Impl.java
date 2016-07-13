package bl.managementServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import bl.listServiceImpl.SingleStock_Impl;
import bl_service.EWMA_service;
import bl_service.SingleStock_service;
import vo.StockVO;

public class EWMA_Impl implements EWMA_service {
    //加权移动平均值
    private String ID;
    private String date1;
    private String date2;

    private SingleStock_service single;

    public EWMA_Impl(String ID, String date1, String date2) {
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * 默认一个月时间
     *
     * @param ID
     */
    public EWMA_Impl(String ID) {
        this.ID = ID;
    }

    public boolean setTime(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
        return true;
    }

    public double getEWMA() throws ParseException {
        // 梯型加权移动平均线
        single = new SingleStock_Impl();
        ArrayList<StockVO> list = single.setTime(ID, date1, date2);
        int n = list.size();
        int l = 0;
        double sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum = sum + (list.get(i).getClose() + list.get(i + 1).getClose()) * (i + 1);
            l = l + 2 * (i + 1);
        }
        BigDecimal b = new BigDecimal(sum / l);
        double f1 = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static void main(String[] args) throws ParseException {
        EWMA_service a = new EWMA_Impl("sh600979", "2015-11-10", "2015-11-20");
        double num = a.getEWMA();
        System.out.println(num);
    }


}
