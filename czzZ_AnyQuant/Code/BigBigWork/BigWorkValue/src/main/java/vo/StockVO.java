package vo;

import voHelper.StockWrapper;

public class StockVO {

    final private String split = ":%:%:";

    private String id;
    private int volume;
    private double pd;
    private double high;
    private double pe_ttm;
    private double adj_price;
    private double low;
    private String date;
    private double close;
    private double open;
    private double turnover;

    private String name;
    private Type type;
    private double dealsum;

    /*newly added 16/04/08*/
    private double hotValue;

    private StockWrapper wrapper = new StockWrapper();

	 	/*open: 开盘价
        high: 最高价
		low: 最低价
		close: 收盘价
		adj_price: 后复权价
		volume: 成交量
		turnover: 换手率
		pe: 市盈率
		pb: 市净率
		
		name：名称
		type：行业
		dealsum：成交金额
		
		hotValue：热门指数*/

    public StockVO() {

    }

    public StockVO(double adj_price, int volume, double turnover, double pe, double pb) {
        this.adj_price = adj_price;
        this.volume = volume;
        this.turnover = turnover;
        this.pe_ttm = pe;
        this.pd = pb;
        this.id = "";
        this.volume = -1;
        this.high = -1;
        this.low = -1;
        this.date = "";
        this.close = -1;
        this.open = -1;
    }

    public StockVO(String str) {
        super();
        String[] strs = str.split(this.split);
        this.id = strs[0];
        this.volume = Integer.parseInt(strs[1]);
        this.pd = Double.parseDouble(strs[2]);
        this.high = Double.parseDouble(strs[3]);
        this.pe_ttm = Double.parseDouble(strs[4]);
        this.adj_price = Double.parseDouble(strs[5]);
        this.low = Double.parseDouble(strs[6]);
        this.date = strs[7];
        this.close = Double.parseDouble(strs[8]);
        this.open = Double.parseDouble(strs[9]);
        this.turnover = Double.parseDouble(strs[10]);

        this.name = wrapper.getName(id);
        this.type = wrapper.getType(id);
        this.dealsum = wrapper.getDealSum(id);

        this.hotValue = 0.0;    //initialized to 0, gets value through HotIndustryData
    }

    public String toString() {
        return this.id + this.split + this.volume + this.split + this.pd
                + this.split + this.high + this.split + this.pe_ttm + this.split
                + this.adj_price + this.split + this.low + this.split + this.date
                + this.split + this.close + this.split + this.open + this.split
                + this.turnover + this.split + this.name + this.split + this.type.toString()
                + this.split + this.dealsum + this.split + this.hotValue;
        //added the last 4 members   by zjx14
    }

    public String getId() {
        return id;
    }

    public StockVO(String id, int volume, double pd, double high,
                   double pe_ttm, double adj_price, double low, String date,
                   double close, double open, double turnover) {
        super();
        this.id = id;
        this.volume = volume;
        this.pd = pd;
        this.high = high;
        this.pe_ttm = pe_ttm;
        this.adj_price = adj_price;
        this.low = low;
        this.date = date;
        this.close = close;
        this.open = open;
        this.turnover = turnover;

        this.name = wrapper.getName(id);
        this.type = wrapper.getType(id);
        this.dealsum = wrapper.getDealSum(id);

        this.hotValue = 0.0;
    }

    /*used in getPieChartData*/
    public StockVO(Type type, double hotValue) {
        this.type = type;
        this.hotValue = hotValue;
    }


    public void setId(String id) {
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPd() {
        return pd;
    }

    public void setPd(int pd) {
        this.pd = pd;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public double getPe() {
        return pe_ttm;
    }

    public void setPe(int pe_ttm) {
        this.pe_ttm = pe_ttm;
    }

    public double getAdj() {
        return adj_price;
    }

    public void setAdj(int adj_price) {
        this.adj_price = adj_price;
    }

    public double getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getDealSum() {
        return this.getAdj() * this.getVolume();
    }

    public void setHotValue(double value) {
        hotValue = value;
    }

    public double getHotValue() {
        return hotValue;
    }

    public String getTypeName() {
        String name = "";
        switch (this.type) {
            case MED:
                name = "生物制药";
                break;
            case ELEC:
                name = "电器行业";
                break;
            case ESTATE:
                name = "房地产";
                break;
            case SALES:
                name = "服装鞋类";
        }
        return name;
    }

}
