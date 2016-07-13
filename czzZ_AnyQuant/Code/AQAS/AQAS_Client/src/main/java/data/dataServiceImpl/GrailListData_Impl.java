package data.dataServiceImpl;

import java.util.ArrayList;

import data.dataHelper.FileHelper;
import data_service.GrailListData_service;
import vo.GrailVO;

public class GrailListData_Impl implements GrailListData_service {

    @Override
    public ArrayList<GrailVO> GrailList() {
        // TODO Auto-generated method stub
        ArrayList<GrailVO> result = new ArrayList<GrailVO>();
        ArrayList<String> re = new ArrayList<String>();

        FileHelper helper = new FileHelper("file" + "/" + "GrailList.txt");
        re = helper.read();

        for (String string : re) {
            result.add(new GrailVO(string));
        }
        return result;
    }

}
