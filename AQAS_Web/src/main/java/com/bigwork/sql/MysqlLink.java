package com.bigwork.sql;



import com.bigwork.sql.impl.MarketSqlImpl;
import com.bigwork.sql.impl.RefreshHistorySQLImpl;
import com.bigwork.sql.impl.StockListSQLImpl;
import com.bigwork.sql.impl.StockSQLImpl;
import com.bigwork.sql.implservice.MarketSQLImplService;
import com.bigwork.sql.implservice.RefreshHistorySQLImplService;
import com.bigwork.sql.implservice.StockListSQLService;
import com.bigwork.sql.implservice.StockSQLImplService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 15HR-1528SS on 2016/5/5.
 */
public class MysqlLink {
    private Connection con;
    private StockSQLImplService stock_impl;
    private MarketSQLImplService market_impl;
    private RefreshHistorySQLImplService refreshHistory_impl;
    private StockListSQLService stockListSQL_Impl;

    public void link(String ip, String user, String password) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + ip + "/anyquant";

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
                stock_impl = new StockSQLImpl(con);
                market_impl = new MarketSqlImpl(con);
                refreshHistory_impl = new RefreshHistorySQLImpl(con);
                stockListSQL_Impl = new StockListSQLImpl(con);
            }

        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            System.out.println("Sorry,please check ip,user or password!");
            e.printStackTrace();
        } finally {
            System.out.println("Load data successfully!");
        }
    }

    public StockSQLImplService getStock_Impl() throws SqlImplNullPointerException {
        if (stock_impl != null)
            return stock_impl;
        else
            throw new SqlImplNullPointerException("Stock_impl is null");

    }

    public MarketSQLImplService getMarket_Impl() throws SqlImplNullPointerException {
        if (market_impl != null)
            return market_impl;
        else
            throw new SqlImplNullPointerException("Market_impl is null");

    }

    public RefreshHistorySQLImplService getRefreshHistory_Impl() throws SqlImplNullPointerException {
        if (refreshHistory_impl != null)
            return refreshHistory_impl;
        else
            throw new SqlImplNullPointerException("RefreshHistory_impl is null");

    }

    public StockListSQLService getStockList_Impl()throws SqlImplNullPointerException{
        if(stockListSQL_Impl != null){
            return  stockListSQL_Impl;
        }else
            throw new SqlImplNullPointerException("stockListSQL_Impl is null");
    }
    public void close() throws SQLException {
        con.close();
    }

}
