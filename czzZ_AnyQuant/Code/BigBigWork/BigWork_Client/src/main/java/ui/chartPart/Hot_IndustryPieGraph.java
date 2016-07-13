package ui.chartPart;

import java.math.BigDecimal;
import java.text.ParseException;

import bl.managementServiceImpl.Hot_IndustryData_Impl;
import bl_service.Hot_IndustryData_service;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Hot_IndustryPieGraph {

    private Hot_IndustryData_service data;
    private double sum = 0;

    public PieChart getPieChart(String start, String end) throws ParseException {
        data = new Hot_IndustryData_Impl();
        ObservableList<Data> pieChartData = data.GetPieGraphData(start, end);
        final PieChart chart = new PieChart(pieChartData);

//		chart.getStylesheets().add(getClass().getResource("pieChart.css").toExternalForm());
        chart.getStylesheets().add("pieChart.css");
        chart.setTitle(start + " 至 " + end + "热门行业");
        chart.setPrefSize(900, 500);

        return chart;
    }

    public Scene getPieScene(String start, String end) throws ParseException {
        Scene scene = new Scene(new Group());
        PieChart chart = this.getPieChart(start, end);
        chart.setTitle(start + " 至 " + end + "热门行业");

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
                            lb.setTranslateY(e.getSceneY());
                            BigDecimal bd = new BigDecimal(tmp.getPieValue() / sum).setScale(2);


                            lb.setText(String.valueOf(bd.doubleValue()) + "%");
                        }
                    });
        }

        final ObservableList<Node> children = ((Group) scene.getRoot()).getChildren();
        children.add(chart);
        children.add(lb);
        return scene;
    }
}
