/*package voHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.URL;


public class AdditionalInfoGetter {


    public String[] getInfo(String id) {
        String splitted[] = null;
        StringBuilder result = new StringBuilder();

        try {
            URL link = new URL("http://hq.sinajs.cn/list=" + id);
            HttpURLConnection conn = (HttpURLConnection) link.openConnection();

            try {
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "gbk"));
                String temp = "";

                while ((temp = br.readLine()) != null) {
                    result.append(temp);
                }
            } catch (ConnectException e) {
                System.err.println(e);
            } catch (NoRouteToHostException e1) {
                System.err.println(e1);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        String modi1[] = result.toString().split("\"");
        splitted = modi1[1].split(",");

        return splitted;
    }

    public String getStockName(String id) {

        return this.getInfo(id)[0];
    }

    public double getDealSum(String id) {

        return Double.valueOf(this.getInfo(id)[9]);
    }


//	public static void main(String[] args){
//		AdditionalInfoGetter getter = new AdditionalInfoGetter();
//		System.out.println(getter.getDealSum("sh600979"));
//	}

}*/
