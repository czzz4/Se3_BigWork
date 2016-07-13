package data.dataHelper;


import data.api.refreshImpl.Grail_refreshImpl;
import data.api.refreshImpl.SingleStock_refreshImpl;
import data.api.refreshImpl.StockList_refreshImpl;

public class Refresh {

    private StockList_refreshImpl stocklist_refresh = new StockList_refreshImpl();
    private SingleStock_refreshImpl singlestock_refresh = new SingleStock_refreshImpl();
    private Grail_refreshImpl grail_refresh = new Grail_refreshImpl();
    private String path;
    private FileHelper helper;

    /**
     * 更新所有本地文件
     *
     * @return
     */
    public boolean refresh() {
        //this.GrailList();
        return this.GrailList() && this.StockList() && this.SingleStockEight()
                && this.SingleStockFive() && this.SingleStockFour() && this.SingleStockNine()
                && this.SingleStockOne() && this.SingleStockSeven() && this.SingleStockSix()
                && this.SingleStockThree() && this.SingleStockTwo();

    }

    private boolean GrailList() {
        path = "file" + "/" + "GrailList.txt";
        //path = "GrailList.txt";
        helper = new FileHelper(path);
        System.out.println("0");
        //System.out.println(grail_refresh.ShowGrailList().get(0));
        //helper.rewrite(grail_refresh.ShowGrailList());
        return helper.rewrite(grail_refresh.ShowGrailList());

    }

    private Boolean StockList() {
        path = "file" + "/" + "StockList.txt";
        helper = new FileHelper(path);
        System.out.println("1");
        return helper.rewrite(stocklist_refresh.ShowAll());
    }

    private Boolean SingleStockOne() {
        path = "file" + "/" + "SingleStockOne.txt";
        //path = "SingleStockOne.txt";
        helper = new FileHelper(path);
        System.out.println("1");
        return helper.rewrite(singlestock_refresh.Show("sz002644"));

    }

    private Boolean SingleStockTwo() {
        path = "file" + "/" + "SingleStockTwo.txt";
        helper = new FileHelper(path);
        System.out.println("2");
        return helper.rewrite(singlestock_refresh.Show("sz002664"));
    }

    private Boolean SingleStockThree() {
        path = "file" + "/" + "SingleStockThree.txt";
        helper = new FileHelper(path);
        System.out.println("3");
        return helper.rewrite(singlestock_refresh.Show("sz000908"));

    }

    private Boolean SingleStockFour() {
        path = "file" + "/" + "SingleStockFour.txt";
        helper = new FileHelper(path);
        System.out.println("4");
        return helper.rewrite(singlestock_refresh.Show("sh600216"));

    }

    private Boolean SingleStockFive() {
        path = "file" + "/" + "SingleStockFive.txt";
        helper = new FileHelper(path);
        System.out.println("5");
        return helper.rewrite(singlestock_refresh.Show("sh600979"));

    }

    private Boolean SingleStockSix() {
        path = "file" + "/" + "SingleStockSix.txt";
        helper = new FileHelper(path);
        System.out.println("6");
        return helper.rewrite(singlestock_refresh.Show("sh600724"));

    }

    private Boolean SingleStockSeven() {
        path = "file" + "/" + "SingleStockSeven.txt";
        helper = new FileHelper(path);
        System.out.println("7");
        return helper.rewrite(singlestock_refresh.Show("sz300137"));

    }

    private Boolean SingleStockEight() {
        path = "file" + "/" + "SingleStockEight.txt";
        helper = new FileHelper(path);
        System.out.println("8");
        return helper.rewrite(singlestock_refresh.Show("sh600129"));

    }

    private Boolean SingleStockNine() {
        path = "file" + "/" + "SingleStockNine.txt";
        helper = new FileHelper(path);
        System.out.println("9");
        return helper.rewrite(singlestock_refresh.Show("sz002569"));

    }
}
