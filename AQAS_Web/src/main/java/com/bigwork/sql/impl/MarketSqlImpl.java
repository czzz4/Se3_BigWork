package com.bigwork.sql.impl;



import com.bigwork.model.Grail;
import com.bigwork.sql.DateHelper;
import com.bigwork.sql.implservice.MarketSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class MarketSqlImpl extends SQLImpl implements MarketSQLImplService {
    public MarketSqlImpl(Connection con) {
        super(con);
    }

    @Override
    public void insertMarket(Grail market) throws SQLException {
        psql = con.prepareStatement("INSERT  INTO marketdata VALUES (?,?,?,?,?,?,?,?,?)");
        psql.setString(1, market.getID());
        psql.setString(2, null);
        psql.setDouble(3, market.getOpen());
        psql.setDouble(4, market.getHigh());
        psql.setDouble(5, market.getLow());
        psql.setDouble(6, market.getClose());
        psql.setDouble(7, market.getAdj_price());
        psql.setString(8, market.getVolume());
        psql.setString(9, market.getDate());

        psql.executeUpdate();
    }

    @Override
    public Grail selectMarket(String market_id, String date) throws SQLException {
        psql = con.prepareStatement("SELECT * FROM marketdata WHERE id = ? AND dateTime = ?");
        psql.setString(1, market_id);
        psql.setString(2, date);

        rs = psql.executeQuery();

        Grail market = new Grail();
        while (rs.next()) {
            market = new Grail();

            market.setID(rs.getString("id"));
            market.setOpen(rs.getDouble("open"));
            market.setHigh(rs.getDouble("high"));
            market.setLow(rs.getDouble("low"));
            market.setClose(rs.getDouble("close"));
            market.setAdj_price(rs.getDouble("adj_price"));
            market.setVolume(rs.getString("volume"));
            market.setDate(rs.getString("dateTime"));
        }

        rs.close();
        return market;
    }

    @Override
    public void insertArray(ArrayList<Grail> array) throws SQLException {
        for (Grail market : array)
            insertMarket(market);
    }

    @Override
    public ArrayList<Grail> selectArray(String market_id, String datefrom, String dateTo) throws SQLException {
        psql = con.prepareStatement("SELECT * FROM marketdata WHERE id = ? ");
        psql.setString(1, market_id);

        rs = psql.executeQuery();

        ArrayList<Grail> array = new ArrayList<>();
        while (rs.next()) {
            if (DateHelper.isIn(rs.getString("dateTime"), datefrom, dateTo)) {
                Grail market = new Grail();
                market.setID(rs.getString("id"));
                market.setOpen(rs.getDouble("open"));
                market.setHigh(rs.getDouble("high"));
                market.setLow(rs.getDouble("low"));
                market.setClose(rs.getDouble("close"));
                market.setAdj_price(rs.getDouble("adj_price"));
                market.setVolume(rs.getString("volume"));
                market.setDate(rs.getString("dateTime"));
                array.add(market);
            }
        }

        rs.close();
        return array;
    }
}
