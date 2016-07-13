package vo;

/* @author zjx14
 * 
 * 很不幸的是还没有找到提供行业分类的API
 * 然后本来十分天真地想过从名字中查找某些字来大致判断分类
 * 然后发现名字并不都是那么正常＝＝
 * 于是现在
 * 人工分类……但是我真的好懒（sad
 * 
 * 
 * 生物制药 MED：sz002644，sz000908，sh600216，sh600129
 * 电器行业 ELEC：sz002664，sh600979，sz300137（仪器仪表what ghost＝＝）
 * 房地产 ESTATE：sh600724
 * 服装鞋类 SALES：sz002569
 * 
 * */

public enum Type {
    MED, ELEC, ESTATE, SALES;
}
