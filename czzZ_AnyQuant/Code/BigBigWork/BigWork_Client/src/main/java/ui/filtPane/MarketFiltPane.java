package ui.filtPane;

import bl.listServiceImpl.Grail_Impl;
import bl_service.Grail_service;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import ui.chartPane.AdjLinePane;
import ui.chartPane.MarketLinePane;
import ui.chartPane.MarketPane;
import ui.chartPart.MarketLineGraph;
import ui.common.GetDate;
import ui.common.MessageDialog;
import ui.main.Main;
import vo.GrailVO;

import java.text.ParseException;

/**
 * Created by asus on 2016/4/9.
 */
public class MarketFiltPane extends ParentFiltPane {

    Label select = new Label();
    Label list = new Label();
    Label lineGraph = new Label();
    Label adj = new Label();
    ImageView selectIcon = new ImageView(new Image("select.png"));
    ImageView listIcon = new ImageView(new Image("list.png"));
    ImageView lineIcon = new ImageView(new Image("lineGraph.png"));
    ImageView adjIcon = new ImageView(new Image("adj.png"));

    Grail_service impl = new Grail_Impl();
    private static int state = 1;

    public static String from = GetDate.getDate().get(1);
    public static String to = GetDate.getDate().get(0);

    private void setButtons() {
        //TODO
        select.setGraphic(selectIcon);
        list.setGraphic(listIcon);
        lineGraph.setGraphic(lineIcon);
        adj.setGraphic(adjIcon);
        select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FiltMarketStage f = new FiltMarketStage(main, state);
            }
        });
        list.setOnMouseClicked(listEvent);
        lineGraph.setOnMouseClicked(lineEvent);
        adj.setOnMouseClicked(adjEvent);
        this.getChildren().addAll(select, list, lineGraph, adj);
    }

    public MarketFiltPane(Main main) {
        super(main);
        setButtons();
        setPrefSize(80, 100);
        setLayoutY(180);
    }

    EventHandler listEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            state = 1;
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new MarketPane(main, impl.setTime(from, to)).getPane());
            } catch (ParseException e) {
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
            }
        }
    };

    EventHandler lineEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            //TODO
            //选择折线图，左边显示大盘折线图信息
            state = 2;
            main.getMenuPane().getLayout().getChildren().remove(2);
            System.out.println(from + " " + to);
            if (from == null || to == null) {
                try {
                    main.getMenuPane().getLayout().getChildren().add(new MarketLinePane(main, GetDate.getDate().get(1), GetDate.getDate().get(0)).getPane());
                } catch (Exception e) {
                    MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
                }
            } else {
                try {
                    main.getMenuPane().getLayout().getChildren().add(new MarketLinePane(main, from, to).getPane());
                } catch (Exception e) {
                    MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
                }
            }
        }
    };

    EventHandler adjEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            state = 3;
            main.getMenuPane().getLayout().getChildren().remove(2);
            try {
                main.getMenuPane().getLayout().getChildren().add(new AdjLinePane(main, from, to).getPane());
            } catch (Exception e) {
                MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");

            }
        }
    };

}
