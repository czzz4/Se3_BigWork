package ui.funcPane;

import bl.listServiceImpl.Grail_Impl;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.chartPane.HotIndustryPane;
import ui.chartPane.MarketPane;
import ui.chartPane.ParentChartPane;
import ui.common.GetDate;
import ui.filtPane.HotFiltPane;
import ui.main.Main;
import ui.messagePane.MessageDialog;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/3/28.
 */
public class MarketFuncPane {
    Main main;
    private FuncPane funcPane;
    private Grail_Impl impl;

    private VBox layout;

    Image blueLine = new Image("test.png");
    ImageView BlueLine = new ImageView(blueLine);
    Image grayLine = new Image("test2.jpg");
    ImageView GrayLineA = new ImageView(grayLine);
    ImageView GrayLineB = new ImageView(grayLine);

    public MarketFuncPane(Main m, FuncPane f) {
        this.funcPane = f;
        this.main = m;

        impl = new Grail_Impl();
    }

    private void setImageView() {
        BlueLine.setFitWidth(80);
        BlueLine.setFitHeight(10);
        GrayLineA.setFitWidth(80);
        GrayLineA.setFitHeight(10);
        GrayLineB.setFitWidth(80);
        GrayLineB.setFitHeight(10);
    }


    public ArrayList<Label> getLabels() {

        setImageView();
        Label labelA = new Label("大盘数据");
        Label labelB = new Label("热门行业");

        labelA.setPrefSize(80, 30);
        labelB.setPrefSize(80, 30);
        labelA.setGraphic(BlueLine);
        labelB.setGraphic(GrayLineB);
        labelA.setContentDisplay(ContentDisplay.BOTTOM);
        labelB.setContentDisplay(ContentDisplay.BOTTOM);


        labelA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                labelA.setGraphic(BlueLine);
                labelB.setGraphic(GrayLineA);
                layout.getChildren().remove(2);
                try {
                    layout.getChildren().add(new MarketPane(main, impl.setTime(GetDate.getDate().get(1), GetDate.getDate().get(0))).getPane());
                } catch (ParseException e) {
                    MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");                    e.printStackTrace();
                }
            }
        });
        labelB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                labelB.setGraphic(BlueLine);
                labelA.setGraphic(GrayLineB);
                layout.getChildren().remove(2);
                try {
                    layout.getChildren().add(new HotIndustryPane(main, HotFiltPane.from, HotFiltPane.to).getPane());
                } catch (ParseException e) {
                    MessageDialog.display("错误", "您已断开网络连接...(；′⌒`)");
                    e.printStackTrace();
                }
            }
        });
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelA);
        labels.add(labelB);
        return labels;
    }

    public void setLayout(VBox layout) {
        this.layout = layout;
    }
}
