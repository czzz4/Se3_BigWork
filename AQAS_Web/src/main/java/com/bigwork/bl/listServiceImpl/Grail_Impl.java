package com.bigwork.bl.listServiceImpl;

import com.bigwork.controller.TestConnect;
import com.bigwork.model.Grail;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.GrailListData_management;
import com.bigwork.data.dataServiceImpl.GrailListData_Impl;
import com.bigwork.data_service.GrailListData_service;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import data_service.GrailData_service;

public class Grail_Impl implements Grail_service {

    private DataGetter getter = new DataGetter();
    private GrailListData_management grailListDataImpl = new GrailListData_management();
//	private GrailData_service grailData_Impl = new GrailData_Impl();
    private String date1;
    private String date2;

    public ArrayList<Grail> ShowGrailList(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date2 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date2 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        //System.out.println(this.date2);
        cal.add(Calendar.MONTH, -1);
        month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date1 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date1 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        GrailListData_service grailListData = new GrailListData_Impl();
        ArrayList<Grail> g = new ArrayList<>();
        try {
            g = grailListData.GrailList("sh600",date1,date2);
        } catch (SqlImplNullPointerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }

    private ArrayList<Grail> SetTime(String start, String end) {
        ArrayList<Grail> result = new ArrayList<Grail>();
        System.out.println("Out");
        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start="
                + start + "&end=" + end;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = grailListDataImpl.GrailList(temp);
            return result;
        }
        return null;
    }

    public ArrayList<Grail> setTime(String start, String end){
        GrailListData_service grailListData = new GrailListData_Impl();

        ArrayList<Grail> R = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date line = null;
        try {
            line = sdf.parse("2014-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if ((startDate.getTime() - line.getTime()) > 0) {
            System.out.println("In");
            ArrayList<Grail> result = null;
            try {
                result = grailListData.GrailList("sh600",start,end);
            } catch (SqlImplNullPointerException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            R = result;
        } else {
            R = this.SetTime(start, end);
        }

        return R;
    }

    public void close() {
        // TODO Auto-generated method stub

    }

    public ArrayList<Grail> filter(String F) {
        // TODO Auto-generated method stub
        return null;
    }

//	public static void main(String[] args){
//		Grail_Impl test = new Grail_Impl();
//		ArrayList<GrailVO> t = test.setTime("2015-01-11", "2015-02-02");
//		System.out.println(t.size());
//
//	}

}
