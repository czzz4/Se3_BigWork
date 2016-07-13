package bl_service;


public interface SingleLineGraphData_service {
    /**
     * 获取单只股票折线图所需要的数据
     * <p>
     * <p>
     * （成交金额＝成交价格＊成交量？……于是还是用那个带Name的API了因为我觉得这个应该算不出来＝＝）
     */
    public void GetSingleLineGraphData();

    /**
     * 返回振幅，涨跌幅（等？）数据
     */
    public void GetStockOffset();
}
