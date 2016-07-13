package ui.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 15HR-1528SS on 2016/4/13.
 */
public class GetDate {
    public static ArrayList<String> getDate() {
        ArrayList<String> dates = new ArrayList<>();

        long s = System.currentTimeMillis();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Calendar C = Calendar.getInstance();
        C.add(Calendar.DATE, 0);

        String date_now = format.format(C.getTime());

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -31);

        String date_past = format.format(c.getTime());

        dates.add(date_now);
        dates.add(date_past);

        return dates;
    }

    public static void main(String[] args) {
        System.out.println(getDate().get(0) + " " + getDate().get(1));
    }
}
