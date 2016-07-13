package bl_service;

import java.text.ParseException;

public interface ATR_service {

    /**
     * 获取一段时间内的单支股票均幅指标
     *
     * @param ID
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public double GetATR() throws ParseException;
}
