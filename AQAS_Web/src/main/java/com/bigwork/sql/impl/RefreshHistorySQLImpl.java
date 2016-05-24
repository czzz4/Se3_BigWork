package com.bigwork.sql.impl;


import com.bigwork.sql.implservice.RefreshHistorySQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class RefreshHistorySQLImpl extends SQLImpl implements RefreshHistorySQLImplService {
    public RefreshHistorySQLImpl(Connection con) {
        super(con);
    }

    @Override
    public void insertRefreshHistory() throws SQLException {
        psql = con.prepareStatement("INSERT INTO refreshhistory VALUES (?,?)");

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);

        psql.setInt(1, 0);
        psql.setString(2, time);

        psql.executeUpdate();
    }

    @Override
    public String selectRefreshHistory() throws SQLException {
        psql = con.prepareStatement("SELECT * FROM refreshhistory WHERE times = (SELECT max(times) FROM refreshhistory)");

        rs = psql.executeQuery();
        String refreshTime = "";
        while (rs.next())
            refreshTime = rs.getString("refreshtime");
        rs.close();
        return refreshTime;
    }
}
