package ui.chartPane;

import javafx.scene.layout.StackPane;
import ui.chartPart.MarketLineGraph;
import ui.common.GetDate;
import ui.filtPane.MarketFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

/**
 * Created by 15HR-1528SS on 2016/4/13.
 */
public class MarketLinePane extends ParentChartPane {
    public MarketLinePane(Main main) {
        super(main);
        chartBG();
        main.setChartType(ChartPaneType.MARKET_LINE);
        MarketLineGraph mlg = new MarketLineGraph();
        StackPane chart = mlg.getVolpane(GetDate.getDate().get(1), GetDate.getDate().get(0));
        chart.setPrefSize(900, 500);

        chartPane.getChildren().add(chart);
        chartPane.getChildren().add(new MarketFiltPane(main));
    }

    public MarketLinePane(Main main, String df, String dt) {
        super(main);
        chartBG();

        MarketLineGraph mlg = new MarketLineGraph();
        StackPane chart = mlg.getVolpane(df, dt);
        chart.setPrefSize(900, 500);
        chartPane.getChildren().add(chart);
        chartPane.getChildren().add(new MarketFiltPane(main));
    }
}
