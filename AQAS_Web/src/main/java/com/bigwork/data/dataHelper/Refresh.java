package com.bigwork.data.dataHelper;


import com.bigwork.data.api.refreshImpl.Grail_refreshImpl;
import com.bigwork.data.api.refreshImpl.SingleStock_refreshImpl;
import com.bigwork.data.api.refreshImpl.StockList_refreshImpl;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.text.ParseException;

public class Refresh {

    private StockList_refreshImpl stocklist_refresh = new StockList_refreshImpl();
    private SingleStock_refreshImpl singlestock_refresh = new SingleStock_refreshImpl();
    private Grail_refreshImpl grail_refresh = new Grail_refreshImpl();
    private MysqlLink link;
    private String LastTime;
    /**
     * 更新所有本地文件
     *
     * @return
     * @throws ParseException
     */
    public boolean refresh() throws ParseException, SqlImplNullPointerException, SQLException {
        //this.GrailList();
        link = new MysqlLink();
        link.link("114.212.43.14", "root", "141250185");
        LastTime = link.getRefreshHistory_Impl().selectRefreshHistory();
        link.getRefreshHistory_Impl().insertRefreshHistory();
        return this.GrailList() && this.StockList() && this.SingleStockEight()
                && this.SingleStockFive() && this.SingleStockFour() && this.SingleStockNine()
                && this.SingleStockOne() && this.SingleStockSeven() && this.SingleStockSix()
                && this.SingleStockThree() && this.SingleStockTwo();

    }

    private boolean GrailList() throws SqlImplNullPointerException, SQLException {
        System.out.println("-1");
        link.getMarket_Impl().insertArray(grail_refresh.ShowGrailList(LastTime));
        return true;
    }

    private Boolean StockList() throws ParseException, SqlImplNullPointerException, SQLException {
        System.out.println("0");
        link.getStockList_Impl().insertArray(stocklist_refresh.ShowAll());
        return true;
    }

    private Boolean SingleStockOne() throws SqlImplNullPointerException, SQLException {
        System.out.println("1");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sz002644",LastTime));
        return true;

    }

    private Boolean SingleStockTwo() throws SqlImplNullPointerException, SQLException{
        System.out.println("2");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sz002664",LastTime));
        return true;
    }

    private Boolean SingleStockThree() throws SqlImplNullPointerException, SQLException{
        System.out.println("3");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sz000908",LastTime));
        return true;

    }

    private Boolean SingleStockFour() throws SqlImplNullPointerException, SQLException{
        System.out.println("4");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sh600216",LastTime));
        return true;

    }

    private Boolean SingleStockFive() throws SqlImplNullPointerException, SQLException{
        System.out.println("5");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sh600979",LastTime));
        return true;

    }

    private Boolean SingleStockSix() throws SqlImplNullPointerException, SQLException{
        System.out.println("6");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sh600724",LastTime));
        return true;

    }

    private Boolean SingleStockSeven() throws SqlImplNullPointerException, SQLException{
        System.out.println("7");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sz300137",LastTime));
        return true;

    }

    private Boolean SingleStockEight() throws SqlImplNullPointerException, SQLException{
        System.out.println("8");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sh600129",LastTime));
        return true;

    }

    private Boolean SingleStockNine() throws SqlImplNullPointerException, SQLException{
        System.out.println("9");
        link.getStock_Impl().insertArray(singlestock_refresh.Show("sz002569",LastTime));
        return true;

    }
}
