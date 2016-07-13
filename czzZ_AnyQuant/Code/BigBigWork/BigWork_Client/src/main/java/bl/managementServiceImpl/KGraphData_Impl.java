package bl.managementServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vo.StockVO;
import bl.listServiceImpl.SingleStock_Impl;
import bl_service.KGraphData_service;
import bl_service.SingleStock_service;

public class KGraphData_Impl implements KGraphData_service {

    private String ID;
    private String date1;
    private String date2;
    private int day;

    private SingleStock_service single = new SingleStock_Impl();

    public String[] s;

    public KGraphData_Impl(String ID, String date1, String date2) {
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
    }

    public void setDate(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public KGraphData_Impl(String ID) {
        this.ID = ID;
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
        //System.out.println(this.date1);
    }

    /**
     * 获取K线图所需要的数据
     *
     * @throws ParseException
     */
    public double[][] GetKGraphData() throws ParseException {
        /*ArrayList<StockVO> list = single.Show(ID);
		Date startDate = sdf.parse(list.get(0).getDate());
		Date endDate = sdf.parse(list.get(list.size()-1).getDate());*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date startDate = sdf.parse(date1);
        Date endDate = sdf.parse(date2);
        ArrayList<StockVO> list = single.setTime(ID, date1, date2);
        startDate = sdf.parse(list.get(0).getDate());
        endDate = sdf.parse(list.get(list.size() - 1).getDate());
        System.out.println(list.get(0).getDate());
        System.out.println(list.get(list.size() - 1).getDate());
        long margin = 0;
        margin = endDate.getTime() - startDate.getTime();
        margin = margin / (1000 * 60 * 60 * 24);
        //System.out.println(margin);

        s = new String[list.size() - 1];
        for (int i = 0; i < list.size() - 1; i++) {
            s[i] = list.get(i).getDate();
        }

        // DAY, OPEN, CLOSE, HIGH, LOW, AVERAGE
        double data[][] = new double[list.size() - 1][6];
        day = list.size();
        //double data[][] = new double[(int) margin][6];
        for (int j = 0; j < 6; j++) {
            if (j == 0) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = i + 1;
                }
            } else if (j == 1) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = list.get(i).getOpen();
                }
            } else if (j == 2) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = list.get(i).getClose();
                }
            } else if (j == 3) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = list.get(i).getHigh();
                }
            } else if (j == 4) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = list.get(i).getLow();
                }
            } else if (j == 5) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = (list.get(i).getOpen() + list.get(i).getClose()) / 2;
                }
            }
        }


		/*int j=-1;
		int m = (int) margin;
		for(int i=0;i<m;i++){
			j++;
			endDate = sdf.parse(list.get(j).getDate());
			//System.out.println(endDate);
			margin = endDate.getTime() - startDate.getTime();
		    margin = margin/(1000*60*60*24);
		    //System.out.println(margin);
		    if(i!=(int)margin){
		    	data[i][0] = i;
		    	for(int k=1;k<6;k++){
		    		data[i][k] = 0;
		    	}
		    	j--;
		    }else{
		    	data[i][0] = i;
		    	data[i][1] = list.get(j).getOpen();
		    	data[i][2] = list.get(j).getClose();
		    	data[i][3] = list.get(j).getHigh();
		    	data[i][4] = list.get(j).getLow();
		    	data[i][5] = list.get(j).getOpen();
		    }
		}*/

        return data;
    }

    @Override
    public int getDay() {
        // TODO Auto-generated method stub
        return day;
    }

    public String[] getDate() {
        return s;
    }
}
