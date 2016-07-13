package ui.funcPane;

import bl.listServiceImpl.StockList_Impl;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.chartPane.ParentChartPane;
import ui.chartPane.StockListPane;
import ui.main.Main;

import java.util.ArrayList;

/**
 * Created by asus on 2016/3/28.
 */
public class StockFuncPane {
    Main main;
    private FuncPane funcPane;
    private VBox layout;

    private StockList_Impl impl;

    Image blueLine = new Image("test.png");
    ImageView BlueLine = new ImageView(blueLine);
    Image grayLine = new Image("test2.jpg");
    ImageView GrayLineA = new ImageView(grayLine);

    public StockFuncPane(Main m, FuncPane f) {
        this.main = m;
        this.funcPane = f;
        impl = new StockList_Impl();
    }

    private void setImageView() {
        BlueLine.setFitWidth(80);
        BlueLine.setFitHeight(10);
        GrayLineA.setFitWidth(80);
        GrayLineA.setFitHeight(10);
    }


    public ArrayList<Label> getLabels() {
        setImageView();
        Label labelA = new Label("股票列表");

        labelA.setPrefSize(80, 30);
        labelA.setGraphic(BlueLine);
        labelA.setContentDisplay(ContentDisplay.BOTTOM);

        labelA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                labelA.setGraphic(BlueLine);
                layout.getChildren().remove(2);
                layout.getChildren().add(new StockListPane(main, impl.ShowAll()).getPane());
            }
        });

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelA);
        return labels;
    }

    public void setLayout(VBox layout) {
        this.layout = layout;
    }
}
