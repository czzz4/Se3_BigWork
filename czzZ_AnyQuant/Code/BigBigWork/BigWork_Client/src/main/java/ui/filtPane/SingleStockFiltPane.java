package ui.filtPane;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.chartPane.StockKGraphPane;
import ui.chartPane.StockLinePane;
import ui.chartPane.StockPane;
import ui.common.GetDate;
import ui.common.MessageDialog;
import ui.main.Main;

import java.text.ParseException;

/**
 * Created by asus on 2016/4/9.
 */
public class SingleStockFiltPane extends ParentFiltPane {
    private String id;
    private static int state = 1;
    Label time = new Label();
    Label kGraph = new Label();
    Label lineGraph = new Label();
    Label list = new Label();

    ImageView select = new ImageView(new Image("select.png"));
    ImageView kIcon = new ImageView(new Image("kDiagram.png"));
    ImageView lineIcon = new ImageView("lineGraph.png");
    ImageView listIcon = new ImageView(new Image("list.png"));

    public static String from = GetDate.getDate().get(1);
    public static String to = GetDate.getDate().get(0);

    private void setButtons() {
        time.setGraphic(select);
        kGraph.setGraphic(kIcon);
        lineGraph.setGraphic(lineIcon);
        list.setGraphic(listIcon);

        time.setOnMouseClicked(timeEvent);
        kGraph.setOnMouseClicked(kEvent);
        list.setOnMouseClicked(listEvent);
        lineGraph.setOnMouseClicked(lineEvent);

        this.getChildren().addAll(time, list, kGraph, lineGraph);
    }

    public SingleStockFiltPane(Main main, String id) {
        super(main);
        this.id = id;
        setButtons();
        setPrefSize(80, 100);
        setLayoutY(200);
    }

    EventHandler kEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //左边显示单只股票K型图
            System.out.println("the date is from" + from + " to " + to);
            state = 2;
            System.out.println("the state is " + state);
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new StockKGraphPane(main, id, from, to).getPane());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler lineEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //左边显示单支股票折线图
            System.out.println("the date is from" + from + " to " + to);

            state = 3;
            System.out.println("the state is " + state);
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new StockLinePane(main, id, from, to).getPane());
            } catch (ParseException e) {
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            }
        }
    };

    EventHandler listEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //左边显示单支股票列表信息
            System.out.println("the date is from" + from + " to " + to);
            state = 1;
            System.out.println("the state is " + state);
            System.out.println("haha ????!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new StockPane(main, id, from, to).getPane());
            } catch (ParseException e) {
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            }
        }
    };

    EventHandler timeEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            System.out.println("the date is from" + from + " to " + to);
            System.out.println("the state is " + state);
            new FiltSingleStage(SingleStockFiltPane.this.main, SingleStockFiltPane.this.id, state);
        }


    };


}
