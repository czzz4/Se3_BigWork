package ui.filtPane;

import javafx.scene.control.DatePicker;

/**
 * Created by asus on 2016/4/10.
 */
public class MyDatePicker extends DatePicker {
    private final String pattern = "yyyy-MM-dd";

    public MyDatePicker() {
        super();
        this.setPrefSize(160, 20);
        this.setShowWeekNumbers(false);
    }


}
