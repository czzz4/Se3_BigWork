package bl_service;

import java.text.ParseException;
import java.util.ArrayList;

import vo.GrailVO;

public interface Grail_service {


    //public void ShowGrail();

    /**
     * 显示一个月内打大盘信息列表
     *
     * @return
     */
    public ArrayList<GrailVO> ShowGrailList();

    /**
     * 显示一段时间内的大盘信息
     *
     * @param String: start. end "yyyy-MM-dd"
     * @return
     * @throws ParseException
     */
    public ArrayList<GrailVO> setTime(String start, String end) throws ParseException;

    /**
     * 关闭大盘列表，返回股票列表
     */
    public void close();

    /**
     * 根据某项数据条件对列表进行筛选
     *
     * @param F
     * @return
     */
    public ArrayList<GrailVO> filter(String F);

}
