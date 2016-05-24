package com.bigwork.sql.impl;

import com.bigwork.model.Stock;
import com.bigwork.sql.DateHelper;
import com.bigwork.sql.implservice.StockSQLImplService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class StockSQLImpl extends SQLImpl implements StockSQLImplService {
    public StockSQLImpl(Connection con) {
        super(con);
    }

    @Override
    public void insertStock(Stock stock) throws SQLException {
        psql = con.prepareStatement("INSERT INTO stockdata VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
        psql.setString(1, stock.getId());
        psql.setString(2, null);
        psql.setDouble(3, stock.getOpen());
        psql.setDouble(4, stock.getHigh());
        psql.setDouble(5, stock.getLow());
        psql.setDouble(6, stock.getClose());
        psql.setDouble(7, stock.getAdj());
        psql.setInt(8, stock.getVolume());
        psql.setDouble(9, stock.getTurnover());
        psql.setDouble(10, stock.getPe());
        psql.setDouble(11, stock.getPd());
        psql.setString(12, stock.getDate());

        psql.executeUpdate();
    }

    @Override
    public Stock selectStock(String stock_id, String date) throws SQLException {
        psql = con.prepareStatement("SELECT * FROM stockdata WHERE id = ? AND dateTime = ?");
        psql.setString(1, stock_id);
        psql.setString(2, date);

        rs = psql.executeQuery();

        Stock stock = new Stock();
        while (rs.next()) {
            stock = new Stock();
            stock.setId(rs.getString("id"));
            stock.setOpen(rs.getDouble("open"));
            stock.setHigh(rs.getDouble("high"));
            stock.setLow(rs.getDouble("low"));
            stock.setClose(rs.getDouble("close"));
            stock.setAdj(rs.getDouble("adj_price"));
            stock.setVolume(rs.getInt("volume"));
            stock.setTurnover(rs.getDouble("turnover"));
            stock.setPe(rs.getDouble("pe"));
            stock.setPd(rs.getDouble("pb"));
            stock.setDate(rs.getString("dateTime"));
        }

        rs.close();
        return stock;
    }

    @Override
    public void insertArray(ArrayList<Stock> array) throws SQLException {
        for (Stock stock : array)
            insertStock(stock);
    }


    @Override
    public ArrayList<Stock> selectArray(String stock_id, String datefrom, String dateTo) throws SQLException {
        psql = con.prepareStatement("SELECT * FROM stockdata WHERE id = ? ");
        psql.setString(1, stock_id);

        rs = psql.executeQuery();

        ArrayList<Stock> array = new ArrayList<>();
        while (rs.next()) {
            if (DateHelper.isIn(rs.getString("dateTime"), datefrom, dateTo)) {
                Stock stock = new Stock();
                stock.setId(rs.getString("id"));
                stock.setOpen(rs.getDouble("open"));
                stock.setHigh(rs.getDouble("high"));
                stock.setLow(rs.getDouble("low"));
                stock.setClose(rs.getDouble("close"));
                stock.setAdj(rs.getDouble("adj_price"));
                stock.setVolume(rs.getInt("volume"));
                stock.setTurnover(rs.getDouble("turnover"));
                stock.setPe(rs.getDouble("pe"));
                stock.setPd(rs.getDouble("pb"));
                stock.setDate(rs.getString("dateTime"));
                array.add(stock);
            }
        }

        rs.close();
        return array;
    }
}
