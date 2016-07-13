package data.api.refreshImpl;

import java.util.ArrayList;
import java.util.Calendar;

import data.api.dataGetter.DataGetter;
import data.api.dataManagement.SingleStockData_management;
import ui.main.TestConnect;
import vo.StockVO;

public class SingleStock_refreshImpl {
    private String date;
    private DataGetter getter = new DataGetter();
    private SingleStockData_management singleDataImpl = new SingleStockData_management();

    public ArrayList<StockVO> Show(String id) {
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        System.out.println(this.date);
        String url = "http://121.41.106.89:8010/api/stock/" + id + "/?start=2016-03-01&end=" + this.date;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = singleDataImpl.SingleStock(temp);
            return result;
        }
        return null;
    }
}
