package ui.chartPane;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.chartPart.Hot_IndustryBarChart;
import ui.common.GetDate;
import ui.filtPane.HotFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

import java.text.ParseException;

/**
 * Created by 15HR-1528SS on 2016/4/13.
 */
public class HotIndustryBarPane extends ParentChartPane {
    private Pane pane;
    private int sum = 0;

    public HotIndustryBarPane(Main main) throws ParseException {
        super(main);
        chartBG();
        Hot_IndustryBarChart bar = new Hot_IndustryBarChart();
        main.setChartType(ChartPaneType.HOT_BAR);

//        BarChart<String, Number> chart = bar.getBarChart(GetDate.getDate().get(1), GetDate.getDate().get(0));
        BarChart<String, Number> chart = bar.getBarChart("2016-02-28", "2016-03-29");


        pane = new Pane();
        pane.setPrefSize(900, 500);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        final Label label = new Label("");
        label.setTextFill(Color.IVORY);
        label.setStyle("-fx-font: 20 arial;");


        for (final XYChart.Series<String, Number> tmp : chart.getData()) {
            for (final XYChart.Data<String, Number> tmpData : tmp.getData()) {
                tmpData.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent e) {
                                label.setTranslateX(e.getSceneX());
                                label.setTranslateY(e.getSceneY() - 140);
                                label.setText(String.valueOf(tmpData.getYValue()));
                            }
                        });
            }
        }
        final ObservableList<Node> children = pane.getChildren();
        children.add(chart);
        children.add(label);

        chartPane.getChildren().add(pane);
        chartPane.getChildren().add(new HotFiltPane(main));
    }

    public HotIndustryBarPane(Main main, String df, String dt) throws ParseException {
        super(main);
        chartBG();

        Hot_IndustryBarChart bar = new Hot_IndustryBarChart();
        main.setChartType(ChartPaneType.HOT_BAR);

        pane = new Pane();
        pane.getChildren().add(bar.getBarChart(GetDate.getDate().get(1), GetDate.getDate().get(0)));
        pane.setPrefSize(900, 500);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        chartPane.getChildren().add(pane);
        chartPane.getChildren().add(new HotFiltPane(main));
    }
}
