package data_service;

import java.util.ArrayList;

import vo.GrailVO;

public interface GrailListData_service {

    /**
     * 处理大盘一段时间内原始数据，以ArrayList返回给bl
     *
     * @return
     */
    public ArrayList<GrailVO> GrailList();


}
