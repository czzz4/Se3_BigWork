package vo;

import voHelper.AdditionalInfoGetter;

public class StockWrappedVO {

    private StockVO stock;
    private Type type;
    private String name;

    public StockWrappedVO() {

    }

    public StockWrappedVO(StockVO stock) {
        this.stock = stock;
//		this.type = type;

        AdditionalInfoGetter getter = new AdditionalInfoGetter();
        name = getter.getStockName("http://hq.sinajs.cn/list=" + stock.getId());

        switch (stock.getId()) {
            case "sz002644":
            case "sz000908":
            case "sh600216":
            case "sh600129":
                type = Type.MED;
                break;
            case "sz002664":
            case "sh600979":
            case "sz300137":
                type = Type.ELEC;
                break;
            case "sh600724":
                type = Type.ESTATE;
                break;
            case "sz002569":
                type = Type.SALES;
                break;
        }
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return stock.getId();
    }

    public int getVolume() {
        return stock.getVolume();
    }

    public double getPd() {
        return stock.getPd();
    }

    public double getHigh() {
        return stock.getHigh();
    }

    public double getPe() {
        return stock.getPe();
    }

    public double getAdj() {
        return stock.getAdj();
    }

    public double getLow() {
        return stock.getLow();
    }

    public String getDate() {
        return stock.getDate();
    }

    public double getClose() {
        return stock.getClose();
    }

    public double getOpen() {
        return stock.getOpen();
    }

    public double getTurnover() {
        return stock.getTurnover();
    }


    public static void main(String[] args) {
        StockWrappedVO test = new StockWrappedVO(new StockVO("sz002644:%:%:79599:%:%:5.11:%:%:12.54:%:%:146.84:%:%:12.35:%:%:11.8:%:%:2016-03-18:%:%:12.35:%:%:11.88:%:%:1.79"));
        System.out.println(test.getName() + test.getType());
    }

}
