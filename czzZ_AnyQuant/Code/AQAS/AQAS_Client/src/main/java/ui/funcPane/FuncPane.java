package ui.funcPane;

import bl.listServiceImpl.Grail_Impl;
import bl.listServiceImpl.StockList_Impl;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import ui.chartPane.MarketPane;
import ui.chartPane.ParentChartPane;
import ui.chartPane.StockListPane;
import ui.common.GetDate;
import ui.main.Main;
import ui.typeDef.MenuType;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/3/28.
 */
public class FuncPane extends HBox {
    private Grail_Impl g_impl;
    private StockList_Impl l_impl;
    private MarketFuncPane marketFuncPane;
    private StockFuncPane stockFuncPane;
    private ArrayList<Label> labels;

    public ParentChartPane chartPane;

    private VBox layout;

    private boolean first_load;

    Main main;

    public FuncPane(MenuType menu, Main main) throws ParseException {
        this.main = main;
        this.setBackground(new Background(new BackgroundImage(new Image("funcBG.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        g_impl = new Grail_Impl();
        l_impl = new StockList_Impl();
        marketFuncPane = new MarketFuncPane(main, this);
        stockFuncPane = new StockFuncPane(main, this);
        chartPane = new MarketPane(main, g_impl.setTime(GetDate.getDate().get(1), GetDate.getDate().get(0)));

        this.setHeight(50);
        Region blank = new Region();
        blank.setPrefWidth(150);
        this.getStylesheets().add("SetFuncPane.css");
        this.getChildren().add(blank);
        this.setSpacing(0); // set the gap
        this.setWidth(1200);
        this.setHeight(300);
        this.setPadding(new Insets(10));
        first_load = true;
        show(menu);
    }

    public void refresh(MenuType menu) throws ParseException {
        this.getChildren().removeAll(labels);
        show(menu);
    }

    private void show(MenuType type) throws ParseException {
        if (!first_load && type == MenuType.MARKET) {
            labels = marketFuncPane.getLabels();
            layout.getChildren().remove(2);
            layout.getChildren().add(new MarketPane(main, g_impl.setTime(GetDate.getDate().get(1), GetDate.getDate().get(0))).getPane());
        } else if (type == MenuType.MARKET) {
            labels = marketFuncPane.getLabels();
        } else if (type == MenuType.STOCK) {
            labels = stockFuncPane.getLabels();
            layout.getChildren().remove(2);
            layout.getChildren().add(new StockListPane(main, l_impl.ShowAll()).getPane());
        }
        this.getChildren().addAll(labels);
        first_load = false;
    }

    public void setLayout(VBox layout) {
        this.layout = layout;
        chartPane.setLayout(layout);
        marketFuncPane.setLayout(layout);
        stockFuncPane.setLayout(layout);
    }
}
