package data.api.dataGetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.net.HttpURLConnection;

import ui.main.TestConnect;

public class DataGetter {

    private String openCode = "8bb6b6a06652c15752ca983a4eb64e6b";

    /**
     * 由网址进行API访问并将JSON数据以String格式返回
     *
     * @param URL
     * @return
     */

    public String getData(String url) {
        StringBuilder result = new StringBuilder();

        try {
            URL link = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) link.openConnection();
            conn.setRequestProperty("X-Auth-Code", openCode);


            try {
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String temp = "";

                while ((temp = br.readLine()) != null) {
                    result.append(temp);
                }
            } catch (ConnectException e) {
                TestConnect.setConnectionState(false);  // modified by zyz
            } catch (NoRouteToHostException e1) {
                TestConnect.setConnectionState(false);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
