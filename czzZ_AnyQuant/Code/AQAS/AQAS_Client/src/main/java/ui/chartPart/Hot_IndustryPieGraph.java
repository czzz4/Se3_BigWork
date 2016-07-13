package ui.chartPart;

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

    public Hot_IndustryData_service data;

    public PieChart getPieChart(String start, String end) throws ParseException {
        data = new Hot_IndustryData_Impl();
        ObservableList<Data> pieChartData = data.GetPieGraphData(start, end);
        final PieChart chart = new PieChart(pieChartData);

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

        for (final Data tmp : chart.getData()) {
            tmp.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                    new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent e) {
                            lb.setTranslateX(e.getSceneX());
                            lb.setTranslateY(e.getSceneY());
                            lb.setText(String.valueOf(tmp.getPieValue()) + "%");
                        }
                    });
        }

        final ObservableList<Node> children = ((Group) scene.getRoot()).getChildren();
        children.add(chart);
        children.add(lb);
        return scene;
    }
}
