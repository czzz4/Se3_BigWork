package bl.listServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//import data_service.GrailData_service;
import data.api.dataGetter.DataGetter;
import data.api.dataManagement.GrailListData_management;
import data.dataServiceImpl.GrailListData_Impl;
import data_service.GrailListData_service;
import ui.main.TestConnect;
import vo.GrailVO;
import bl_service.Grail_service;

public class Grail_Impl implements Grail_service {

    private DataGetter getter = new DataGetter();
    private GrailListData_management grailListDataImpl = new GrailListData_management();
//	private GrailData_service grailData_Impl = new GrailData_Impl();

    public ArrayList<GrailVO> ShowGrailList() {
        GrailListData_service grailListData = new GrailListData_Impl();
        return grailListData.GrailList();
    }

    private ArrayList<GrailVO> SetTime(String start, String end) {
        ArrayList<GrailVO> result = new ArrayList<GrailVO>();
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

    public ArrayList<GrailVO> setTime(String start, String end) throws ParseException {
        GrailListData_service grailListData = new GrailListData_Impl();
        ArrayList<GrailVO> result = grailListData.GrailList();

        ArrayList<GrailVO> R = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        Date every = new Date();
        Date line = sdf.parse("2014-01-01");
        if ((startDate.getTime() - line.getTime()) > 0) {
            System.out.println("In");
            for (GrailVO grailvo : result) {
                every = sdf.parse(grailvo.getDate());
                if ((every.getTime() - startDate.getTime() > 0) && (every.getTime() - endDate.getTime() < 0)) {
                    R.add(grailvo);
                }
            }
        } else {
            R = this.SetTime(start, end);
        }


        return R;
    }

    public void close() {
        // TODO Auto-generated method stub

    }

    public ArrayList<GrailVO> filter(String F) {
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
