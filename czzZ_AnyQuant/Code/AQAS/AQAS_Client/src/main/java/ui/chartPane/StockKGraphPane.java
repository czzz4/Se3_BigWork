package ui.chartPane;

import bl.managementServiceImpl.ATR_Impl;
import bl.managementServiceImpl.BIAS_Impl;
import bl.managementServiceImpl.EWMA_Impl;
import javafx.scene.Group;
import ui.chartPart.KGraph;
import ui.common.GetDate;
import ui.filtPane.MarketFiltPane;
import ui.filtPane.SingleStockFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

import java.text.ParseException;

/**
 * Created by 15HR-1528SS on 2016/4/13.
 */
public class StockKGraphPane extends ParentChartPane {
    public StockKGraphPane(Main main, String id) throws ParseException {
        super(main);
        chartBG();

        atr = new ATR_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));
        bias = new BIAS_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));
        ewma = new EWMA_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));

        main.setChartType(ChartPaneType.STOCK_K);
        KGraph k = new KGraph(id);
        Group root = k.getGroup(GetDate.getDate().get(1), GetDate.getDate().get(0));
        root.prefWidth(900);
        root.prefHeight(500);
        chartPane.getChildren().add(root);
        chartPane.getChildren().add(new SingleStockFiltPane(main, id));

        setLabels();
    }

    public StockKGraphPane(Main main, String id, String from, String to) throws ParseException {
        super(main);
        chartBG();

        atr = new ATR_Impl(id, from, to);
        bias = new BIAS_Impl(id, from, to);
        ewma = new EWMA_Impl(id, from, to);

        main.setChartType(ChartPaneType.STOCK_K);
        KGraph k = new KGraph(id);
        Group root = k.getGroup(from, to);
        root.prefWidth(900);
        root.prefHeight(500);
        chartPane.getChildren().add(root);
        chartPane.getChildren().add(new SingleStockFiltPane(main, id));

        setLabels();
    }
}
