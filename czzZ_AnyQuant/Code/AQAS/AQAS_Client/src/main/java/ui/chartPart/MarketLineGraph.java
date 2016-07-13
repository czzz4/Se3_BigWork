package ui.chartPart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bl.managementServiceImpl.MarketLineGraphData_Impl;
import bl_service.MarketLineGraphData_service;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;

public class MarketLineGraph {
    private MarketLineGraphData_service data;

    public MarketLineGraph() {
        data = new MarketLineGraphData_Impl();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private LineChart getVolGraph(String start, String end) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LineChart graph = null;
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            DateAxis dateAxis = new DateAxis(startDate, endDate);
            NumberAxis numAxis = new NumberAxis();
            numAxis.setForceZeroInRange(false);
            ObservableList<Series<Date, Number>> list = data.getVolData(start, end);
            graph = new LineChart(dateAxis, numAxis, list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public StackPane getVolpane(String start, String end) {
        StackPane pane = new StackPane();
        LineChart volGraph = this.getVolGraph(start, end);
//		volGraph.getStylesheets().add(getClass().getResource("generalStyle.css").toExternalForm());
        volGraph.getStylesheets().add("generalStyle.css");
        pane.getChildren().addAll(volGraph);
        return pane;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    private LineChart getAdjGraph(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LineChart graph = null;
        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            DateAxis dateAxis = new DateAxis(startDate, endDate);
            dateAxis.setId("dateAxis");
            NumberAxis numAxis = new NumberAxis();
            numAxis.setId("numAxis");
            numAxis.setForceZeroInRange(false);
            ObservableList<Series<Date, Number>> list = data.getAdjData(start, end);
            graph = new LineChart(dateAxis, numAxis, list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public StackPane getAdjpane(String start, String end) {
        StackPane pane = new StackPane();
        LineChart adjGraph = this.getAdjGraph(start, end);
        adjGraph.getStylesheets().add("generalStyle.css");
        pane.getChildren().addAll(adjGraph);
        return pane;
    }

}
