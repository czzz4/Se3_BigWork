package ui.chartPane;

import javafx.scene.layout.StackPane;
import ui.chartPart.MarketLineGraph;
import ui.common.GetDate;
import ui.filtPane.MarketFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

/**
 * Created by asus on 2016/4/14.
 */
public class AdjLinePane extends ParentChartPane {

    public AdjLinePane(Main main, String from, String to) {
        super(main);
        chartBG();
        main.setChartType(ChartPaneType.MARKET_ADJ);
        MarketLineGraph mlg = new MarketLineGraph();
        StackPane chart = mlg.getAdjpane(from, to);
        chart.setPrefSize(900, 500);

        chartPane.getChildren().add(chart);
        chartPane.getChildren().add(new MarketFiltPane(main));
    }
}
