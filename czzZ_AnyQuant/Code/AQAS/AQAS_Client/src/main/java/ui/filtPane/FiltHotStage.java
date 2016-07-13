package ui.filtPane;

import bl.listServiceImpl.Grail_Impl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ui.chartPane.*;
import ui.main.Main;
import ui.messagePane.MessageDialog;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by asus on 2016/4/14.
 */
public class FiltHotStage extends Stage{
    Main main;
    Grail_Impl impl;

    Scene scene;
    VBox pane;
    HBox hBox1;
    HBox hBox2;
    HBox hBox3;
    Label time = new Label("时间");
    Label from = new Label("从");
    Label to = new Label("到");
    Button finish = new Button("完成");
    Button cancel = new Button("取消");
    MyDatePicker date1 = new MyDatePicker();
    MyDatePicker date2 = new MyDatePicker();

    private int state;

    public String dateFrom;
    public String dateTo;

    double[] info = Main.getLocs();
    double finalX = info[0];
    double finalY = info[1];
    double finalWidth = 300;
    double finalHeight = 200;

    public FiltHotStage(Main m, int state) {
        this.main = m;
        this.state = state;
        impl = new Grail_Impl();

        setButtons();
        setLayout();
        scene = new Scene(pane);
        setLocation();
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.TRANSPARENT);
        this.showAndWait();
    }

    private void setButtons() {

        cancel.setPrefSize(80, 20);
        finish.setPrefSize(80, 20);
        cancel.setOnAction(e ->
            this.close()
        );
        finish.setOnAction(event -> update());

        date1.setOnAction(event -> setMyPicker());
    }

    private void setLocation() {
        FiltHotStage.this.setX(finalX + 1200);
        FiltHotStage.this.setWidth(0);
        FiltHotStage.this.setHeight(finalHeight);
        FiltHotStage.this.setY(finalY + 300);
        new Thread() {
            public void run() {
                for (int i = 0; i < finalWidth; i += 20) {
                    FiltHotStage.this.setX(finalX + 1200 - i);
                    FiltHotStage.this.setWidth(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void setLayout() {
        this.setOpacity(0.9);
        pane = new VBox();
        pane.getStylesheets().add("setMessageDialog.css");
        pane.setSpacing(20);
        pane.setPadding(new Insets(20));
        hBox1 = new HBox(20);
        hBox2 = new HBox(20);
        hBox3 = new HBox(20);

        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox3.setAlignment(Pos.CENTER);

        hBox1.getChildren().addAll(from, date1);
        hBox2.getChildren().addAll(to, date2);
        hBox3.getChildren().addAll(finish, cancel);
        pane.getChildren().addAll(time, hBox1, hBox2, hBox3);
    }

    private void update() {
        //TODO
        try {
            handleData();
        } catch (Exception e) {
            MessageDialog.display("错误", "请选择日期");
            return;
        }

        HotFiltPane.from = dateFrom;
        HotFiltPane.to = dateTo;

        this.close();
    }

    private void handleData() throws ParseException {

        try {
            dateFrom = date1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateTo = date2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (state == 1) {
                main.getMenuPane().getLayout().getChildren().remove(2);
                main.getMenuPane().getLayout().getChildren().add(new HotIndustryPane(main, dateFrom, dateTo).getPane());
            } else if (state == 2) {
                main.getMenuPane().getLayout().getChildren().remove(2);
                main.getMenuPane().getLayout().getChildren().add(new HotIndustryPiePane(main, dateFrom, dateTo).getPane());
            } else if (state == 3) {
                main.getMenuPane().getLayout().getChildren().remove(2);
                main.getMenuPane().getLayout().getChildren().add(new HotIndustryBarPane(main, dateFrom, dateTo).getPane());
            }

        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e) {
            MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            main.getMenuPane().getLayout().getChildren().add(new HotIndustryPane(main, HotFiltPane.from, HotFiltPane.to).getPane());
        }

        System.out.println(dateFrom);
        System.out.println(dateTo);
    }

    private void setMyPicker() {
//        date1.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item.isBefore(
                                        FiltHotStage.this.date1.getValue().plusDays(1))
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                                long p = ChronoUnit.DAYS.between(
                                        FiltHotStage.this.date1.getValue(), item
                                );
                            }
                        };
                    }
                };

        date2.setDayCellFactory(dayCellFactory);
        date2.setValue(date1.getValue().plusDays(1));
    }
}