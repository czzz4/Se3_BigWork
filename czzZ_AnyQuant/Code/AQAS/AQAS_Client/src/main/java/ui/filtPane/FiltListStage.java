package ui.filtPane;

import bl.listServiceImpl.StockList_Impl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.chartPane.StockListPane;
import ui.main.Main;
import ui.messagePane.MessageDialog;
import vo.StockVO;

/**
 * Created by asus on 2016/4/10.
 */
public class FiltListStage extends Stage {
    private Main main;
    private StockList_Impl impl;
    Button close = new Button("close");
    Scene scene;
    double[] info = Main.getLocs();
    double finalX = info[0];
    double finalY = info[1];
    double finalWidth = 300;
    double finalHeight = 400;

    Label adjLabel = new Label("后复权价");
    Label dealLabel = new Label("成交量");
    Label turnoverLabel = new Label("换手率");
    Label gainLabel = new Label("市盈率");
    Label pureLabel = new Label("市净率");

    double adjF;
    double adjT;
    int dealF;
    int dealT;
    double turnF;
    double turnT;
    double gainF;
    double gainT;
    double pureF;
    double pureT;


    TextField adjFrom = new TextField();
    TextField adjTo = new TextField();
    TextField dealFrom = new TextField();
    TextField dealTo = new TextField();
    TextField turnoverFrom = new TextField();
    TextField turnoverTO = new TextField();
    TextField gainFrom = new TextField();
    TextField gainTo = new TextField();
    TextField pureFrom = new TextField();
    TextField pureTo = new TextField();

    Label[] tos = new Label[5];

    Button finished = new Button("完成");
    Button cancel = new Button("取消");

    VBox layout;
    HBox[] hBoxes = new HBox[11];

    StockVO stock1;
    StockVO stock2;

    public FiltListStage(Main m) {
        this.main = m;
        impl = new StockList_Impl();

        setButtons();
        setLabels();
        setLayout();
        clear();
        scene = new Scene(layout);
        this.setOpacity(0.9);
        setLocation();
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.TRANSPARENT);
        this.showAndWait();
    }

    private void setButtons() {
        cancel.setOnAction(e -> this.close());
        finished.setOnAction(event -> handleData());
    }

    private void setLocation() {
        FiltListStage.this.setX(finalX + 1200);
        FiltListStage.this.setWidth(0);
        FiltListStage.this.setHeight(finalHeight);
        FiltListStage.this.setY(finalY + 200);
        new Thread() {
            public void run() {
                for (int i = 0; i < finalWidth; i += 20) {
                    FiltListStage.this.setX(finalX + 1200 - i);
                    FiltListStage.this.setWidth(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void setLabels() {
        for (int i = 0; i < 5; i++) {
            tos[i] = new Label("至");
        }
    }

    private void setLayout() {
        layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getStylesheets().add("setMessageDialog.css");

        for (int i = 0; i < 11; i++) {
            hBoxes[i] = new HBox();
            hBoxes[i].getStylesheets().add("SetFiltListStage.css");
        }

        hBoxes[0].getChildren().add(adjLabel);
        hBoxes[1].getChildren().addAll(adjFrom, tos[0], adjTo);
        hBoxes[2].getChildren().add(dealLabel);
        hBoxes[3].getChildren().addAll(dealFrom, tos[1], dealTo);
        hBoxes[4].getChildren().add(turnoverLabel);
        hBoxes[5].getChildren().addAll(turnoverFrom, tos[2], turnoverTO);
        hBoxes[6].getChildren().add(gainLabel);
        hBoxes[7].getChildren().addAll(gainFrom, tos[3], gainTo);
        hBoxes[8].getChildren().add(pureLabel);
        hBoxes[9].getChildren().addAll(pureFrom, tos[4], pureTo);
        hBoxes[10].getChildren().addAll(finished, cancel);

        for (int i = 0; i < 10; i++) {
            hBoxes[i].setAlignment(Pos.CENTER_LEFT);
            hBoxes[i].setSpacing(20);
        }

        hBoxes[10].setAlignment(Pos.CENTER);
        hBoxes[10].setSpacing(30);

        layout.getChildren().addAll(hBoxes);
    }

    private void handleData() {
        //TODO
        try {
            if (!adjFrom.getText().equals("")) {
                adjF = Double.valueOf(adjFrom.getText());
            }
            if (!adjTo.getText().equals("")) {
                adjT = Double.valueOf(adjTo.getText());
            }
            if (!dealFrom.getText().equals("")) {
                dealF = Integer.valueOf(dealFrom.getText());
            }
            if (!dealTo.getText().equals("")) {
                dealT = Integer.valueOf(dealTo.getText());
            }
            if (!turnoverFrom.getText().equals("")) {
                turnF = Double.valueOf(turnoverFrom.getText());
            }
            if (!turnoverTO.getText().equals("")) {
                turnT = Double.valueOf(turnoverTO.getText());
            }
            if (!gainFrom.getText().equals("")) {
                gainF = Double.valueOf(gainFrom.getText());
            }
            if (!gainTo.getText().equals("")) {
                gainT = Double.valueOf(gainTo.getText());
            }
            if (!pureFrom.getText().equals("")) {
                pureT = Double.valueOf(pureFrom.getText());
            }
            if (!pureTo.getText().equals("")) {
                pureT = Double.valueOf(pureTo.getText());
            }
        } catch (NumberFormatException e) {
            MessageDialog.display("错误", "请输入正确数据类型");
            return;
        }
        stock1 = new StockVO(adjF, dealF, turnF, gainF, pureF);
        stock2 = new StockVO(adjT, dealT, turnT, gainT, pureT);

        main.getMenuPane().getLayout().getChildren().remove(2);
        try {
            main.getMenuPane().getLayout().getChildren().add(new StockListPane(main, impl.filter(stock1, stock2)).getPane());
        } catch (Exception e) {
            MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
        }
        clear();
        clearTXF();
        this.close();
    }

    private void clear() {
        adjF = 0;
        adjT = 2000000000;
        dealF = 0;
        dealT = 2000000000;
        turnF = 0;
        turnT = 2000000000;
        gainF = 0;
        gainT = 2000000000;
        pureF = 0;
        pureT = 2000000000;
    }

    private void clearTXF() {
        adjFrom.setText("");
        adjTo.setText("");
        dealFrom.setText("");
        dealTo.setText("");
        turnoverFrom.setText("");
        turnoverTO.setText("");
        gainFrom.setText("");
        gainTo.setText("");
        pureFrom.setText("");
        pureTo.setText("");
    }

}
