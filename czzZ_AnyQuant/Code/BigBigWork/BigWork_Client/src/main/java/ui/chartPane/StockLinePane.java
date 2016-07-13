package ui.chartPane;

import bl.managementServiceImpl.ATR_Impl;
import bl.managementServiceImpl.BIAS_Impl;
import bl.managementServiceImpl.EWMA_Impl;
import javafx.scene.layout.StackPane;
import ui.chartPart.SingleLineGraph;
import ui.common.GetDate;
import ui.filtPane.SingleStockFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

import java.text.ParseException;

/**
 * Created by 15HR-1528SS on 2016/4/13.
 */
public class StockLinePane extends ParentChartPane {
    public StockLinePane(Main main, String id) throws ParseException {
        super(main);
        chartBG();

        atr = new ATR_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));
        bias = new BIAS_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));
        ewma = new EWMA_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));

        main.setChartType(ChartPaneType.STOCK_LIME);
        SingleLineGraph slg = new SingleLineGraph(id);
        System.out.println(GetDate.getDate().get(1) + " " + GetDate.getDate().get(0));
        StackPane pane = slg.getGraph(GetDate.getDate().get(1), GetDate.getDate().get(0));
        pane.setPrefSize(900, 500);
        chartPane.getChildren().add(pane);
        chartPane.getChildren().add(new SingleStockFiltPane(main, id));

        setLabels();
    }

    public StockLinePane(Main main, String id, String from, String to) throws ParseException {
        super(main);
        chartBG();

        atr = new ATR_Impl(id, from, to);
        bias = new BIAS_Impl(id, from, to);
        ewma = new EWMA_Impl(id, from, to);

        main.setChartType(ChartPaneType.STOCK_LIME);
        SingleLineGraph slg = new SingleLineGraph(id);
        System.out.println(from + " " + to);
        StackPane pane1 = slg.getAdjpane(from, to);
        pane1.setPrefSize(900, 250);
        pane1.setLayoutX(0);
        pane1.setLayoutY(0);
        StackPane pane2 = slg.getVolpane(from, to);
        pane2.setPrefSize(900, 250);
        pane2.setLayoutX(0);
        pane2.setLayoutY(250);
        chartPane.getChildren().addAll(pane1, pane2);
        chartPane.getChildren().add(new SingleStockFiltPane(main, id));

        setLabels();
    }
}
