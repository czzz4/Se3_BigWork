package data.api.refreshImpl;

import java.util.ArrayList;
import java.util.Calendar;

import data.api.dataGetter.DataGetter;
import data.api.dataManagement.GrailListData_management;
import ui.main.TestConnect;
import vo.GrailVO;

public class Grail_refreshImpl {
    private String date;
    private DataGetter getter = new DataGetter();
    private GrailListData_management grailListDataImpl = new GrailListData_management();
//	private GrailData_Impl grailData_Impl = new GrailData_Impl();

    public ArrayList<GrailVO> ShowGrailList() {
        ArrayList<GrailVO> result = new ArrayList<GrailVO>();
        //System.out.println("1");
        //String url = "http://121.41.106.89:8010/api/benchmark/hs300";
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        System.out.println(this.date);
        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start=2014-01-01&end=" + this.date;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = grailListDataImpl.GrailList(temp);

            return result;
        }
        return null;
    }
}

