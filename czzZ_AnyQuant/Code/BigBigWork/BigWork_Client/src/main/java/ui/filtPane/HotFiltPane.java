package ui.filtPane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.chartPane.HotIndustryBarPane;
import ui.chartPane.HotIndustryPane;
import ui.chartPane.HotIndustryPiePane;
import ui.chartPane.StockLinePane;
import ui.common.MessageDialog;
import ui.main.Main;

import java.text.ParseException;

/**
 * Created by asus on 2016/4/9.
 */
public class HotFiltPane extends ParentFiltPane {
    Label list = new Label();
    Label paiGraph = new Label();
    Label histogram = new Label();
    Image listImg = new Image("list.png");
    Image paiImg = new Image("paiGraph.png");
    Image histImg = new Image("histogram.png");
    ImageView listIcon = new ImageView(listImg);
    ImageView paiIcon = new ImageView(paiImg);
    ImageView hisIcon = new ImageView(histImg);


    private void setButtons() {
        list.setGraphic(listIcon);
        paiGraph.setGraphic(paiIcon);
        histogram.setGraphic(hisIcon);

        list.setOnMouseClicked(listEvent);
        paiGraph.setOnMouseClicked(paiEvent);
        histogram.setOnMouseClicked(hisEvent);

        this.getChildren().addAll(list, paiGraph, histogram);
    }

    public HotFiltPane(Main main) {
        super(main);
        setButtons();
        this.setPrefSize(80, 100);
        this.setLayoutY(180);
    }

    EventHandler listEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //选择列表，左边显示热门行业列表信息
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new HotIndustryPane(main).getPane());
            } catch (ParseException e) {
                e.printStackTrace();
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            }
        }
    };

    EventHandler paiEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //选择饼图，左边显示热门行业饼图
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new HotIndustryPiePane(main).getPane());
            } catch (ParseException e) {
                e.printStackTrace();
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            }
        }
    };

    EventHandler hisEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //选择直方图，左边显示热门行业直方图信息
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new HotIndustryBarPane(main).getPane());
            } catch (ParseException e) {
                e.printStackTrace();
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            }
        }
    };


}
