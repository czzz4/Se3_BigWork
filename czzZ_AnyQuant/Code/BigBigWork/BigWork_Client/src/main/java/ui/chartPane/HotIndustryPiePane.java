package ui.chartPane;

import bl.managementServiceImpl.Hot_IndustryData_Impl;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.chartPart.Hot_IndustryBarChart;
import ui.chartPart.Hot_IndustryPieGraph;
import ui.common.GetDate;
import ui.filtPane.HotFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Created by 15HR-1528SS on 2016/4/13.
 */
public class HotIndustryPiePane extends ParentChartPane {
    private Pane pane;
    private int sum = 0;

    public HotIndustryPiePane(Main main) throws ParseException {
        super(main);
        chartBG();

        Hot_IndustryPieGraph pie = new Hot_IndustryPieGraph();
        main.setChartType(ChartPaneType.HOT_PIE);

        PieChart chart = pie.getPieChart(GetDate.getDate().get(1), GetDate.getDate().get(0));
//        PieChart chart = pie.getPieChart("2015-07-08", "2015-09-01");

        pane = new Pane();
        pane.setPrefSize(900, 500);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        final Label lb = new Label("");
        lb.setTextFill(Color.IVORY);
        lb.setStyle("-fx-font: 20 arial;");

        for (final PieChart.Data tmp : chart.getData()) {
            sum += tmp.getPieValue();
        }

        System.out.println("SUM = " + sum);


        for (final PieChart.Data tmp : chart.getData()) {
            tmp.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                    new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            lb.setTranslateX(e.getSceneX());
                            lb.setTranslateY(e.getSceneY() - 140);
                            BigDecimal bd = new BigDecimal(tmp.getPieValue() * 100 / sum).setScale(2, BigDecimal.ROUND_HALF_DOWN);


                            lb.setText(String.valueOf(bd.doubleValue()) + "%");
                        }
                    });
        }
        final ObservableList<Node> children = pane.getChildren();
        children.add(chart);
        children.add(lb);

        chartPane.getChildren().add(pane);
        chartPane.getChildren().add(new HotFiltPane(main));
    }

    public HotIndustryPiePane(Main main, String df, String dt) throws ParseException {
        super(main);
        chartBG();

        Hot_IndustryPieGraph pie = new Hot_IndustryPieGraph();
        main.setChartType(ChartPaneType.HOT_PIE);

        PieChart chart = pie.getPieChart(df, dt);

        pane = new Pane();
        pane.setPrefSize(900, 500);
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        final Label lb = new Label("");
        lb.setTextFill(Color.IVORY);
        lb.setStyle("-fx-font: 20 arial;");

        for (final PieChart.Data tmp : chart.getData()) {
            tmp.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                    new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            lb.setTranslateX(e.getSceneX());
                            lb.setTranslateY(e.getSceneY() - 150);
                            lb.setText(String.valueOf(tmp.getPieValue()) + "%");
                        }
                    });
        }

        final ObservableList<Node> children = pane.getChildren();
        children.add(chart);
        children.add(lb);

        chartPane.getChildren().add(pane);
        chartPane.getChildren().add(new HotFiltPane(main));
    }
}
