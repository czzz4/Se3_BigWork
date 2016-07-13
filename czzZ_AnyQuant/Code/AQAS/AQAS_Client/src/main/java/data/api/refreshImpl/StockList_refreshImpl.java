package data.api.refreshImpl;

import java.text.ParseException;
import java.util.ArrayList;

import data.api.dataGetter.DataGetter;
import data.api.dataManagement.StockListData_management;
import ui.main.TestConnect;
import vo.StockVO;

public class StockList_refreshImpl {

    private DataGetter getter = new DataGetter();
    private StockListData_management stockListDataImpl = new StockListData_management();
//	private SingleStockData_management singleDataImpl = new SingleStockData_management();

    public ArrayList<StockVO> ShowAll() throws ParseException {
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        String url = "http://121.41.106.89:8010/api/stocks/";
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = stockListDataImpl.StockList(temp);
        }
        return result;
    }

}
