package ui.chartPane;

import bl.managementServiceImpl.ATR_Impl;
import bl.managementServiceImpl.BIAS_Impl;
import bl.managementServiceImpl.EWMA_Impl;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ui.common.MessageDialog;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

import java.text.ParseException;

/**
 * Created by asus on 2016/3/28.
 */
public class ParentChartPane {
    protected Pane chartPane;
    protected Label background;
    Main main;
    protected VBox layout;

    protected ATR_Impl atr;
    protected BIAS_Impl bias;
    protected EWMA_Impl ewma;

    public ParentChartPane(Main main) {
        this.main = main;
        background = new Label();
        background.setPrefSize(1200, 500);
        chartPane = new Pane(background);
        background.setGraphic(new ImageView(new Image("mainBG.jpg")));
    }

    public Pane getPane() {
        return chartPane;
    }

    public void setLayout(VBox l) {
        this.layout = l;
    }

    public void setLabels() {
        try {
            Label a = new Label("均幅指标             " + atr.GetATR());
            Label b = new Label("乘离率                " + bias.getBIAS());
            Label e = new Label("加权移动平均值   " + ewma.getEWMA());

            a.setFont(new Font("weiruanyahei", 20));
            a.setTextFill(Color.WHITE);
            b.setFont(new Font("weiruanyahei", 20));
            b.setTextFill(Color.WHITE);
            e.setFont(new Font("weiruanyahei", 20));
            e.setTextFill(Color.WHITE);

            a.setLayoutX(950);
            b.setLayoutX(950);
            e.setLayoutX(950);
            a.setLayoutY(50);
            b.setLayoutY(100);
            e.setLayoutY(150);

            chartPane.getChildren().addAll(a, b, e);
        } catch (ParseException e) {
            MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
        }
    }

    public void chartBG() {
        Label chartBG = new Label();
        chartBG.setGraphic(new ImageView(new Image("lineGraphBG.png")));
        chartBG.setLayoutX(5);
        chartBG.setLayoutY(5);
        chartPane.getChildren().add(chartBG);
    }
}
