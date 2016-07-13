package bl_service;

public interface Fluctuation_service {

    /**
     * 获取一段时间内的某只股票的历史波动率
     *
     * @param ID
     * @param start
     * @param end
     * @return
     */
    public double getRate(String ID, String start, String end);

    int getPeriod(String start, String end);
}
