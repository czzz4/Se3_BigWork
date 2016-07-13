package ui.chartPart;

import java.text.ParseException;

import bl.managementServiceImpl.Hot_IndustryData_Impl;
import bl_service.Hot_IndustryData_service;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Hot_IndustryBarChart {

    Hot_IndustryData_service data;

    public BarChart<String, Number> getBarChart(String start, String end) throws ParseException {
//        data = new Hot_IndustryData_Impl(start, end);
        data = new Hot_IndustryData_Impl();
        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        yAxis.setLabel("热门指数");
        xAxis.setLabel("行业名称");
        ObservableList<String> typeNames = FXCollections.observableArrayList();
        typeNames.add("生物制药");
        typeNames.add("电器行业");
        typeNames.add("房地产");
        typeNames.add("服装鞋类");
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis
                , yAxis, data.GetBarChartData(), 30);    //last = barGap
//		barChart.getStylesheets().add(getClass().getResource("barChart.css").toExternalForm());
        barChart.getStylesheets().add("barChart.css");

        barChart.setTitle(start + " 至 " + end + "热门行业");
        barChart.setPrefSize(900,500);
        return barChart;
    }

    public Pane getBarScene(String start, String end) throws ParseException {
        Pane pane = new Pane(new Group());
        BarChart<String, Number> chart = this.getBarChart(start, end);
        chart.setTitle(start + " 至 " + end + "热门行业");
        final Label label = new Label("");
        label.setTextFill(Color.IVORY);
        label.setStyle("-fx-font: 20 arial;");


        for (final Series<String, Number> tmp : chart.getData()) {
            for (final Data<String, Number> tmpData : tmp.getData()) {
                tmpData.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        new EventHandler<MouseEvent>() {
                            public void handle(MouseEvent e) {
                                label.setTranslateX(e.getSceneX());
                                label.setTranslateY(e.getSceneY());
                                label.setText(String.valueOf(tmpData.getYValue()));
                            }
                        });
            }
        }
		Scene scene = new Scene(pane);
		final ObservableList<Node> children = ((Group) scene.getRoot()).getChildren();
		children.add(chart);
		children.add(label);
        return pane;
    }

}
