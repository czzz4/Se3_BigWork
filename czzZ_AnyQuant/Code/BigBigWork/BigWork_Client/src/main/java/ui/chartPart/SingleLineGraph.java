package ui.chartPart;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bl.managementServiceImpl.SingleLineGraphData_Impl;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SingleLineGraph {

	private SingleLineGraphData_Impl data;

	public SingleLineGraph(String id){
		data = new SingleLineGraphData_Impl(id);
	}

	public LineChart getVolGraph(String start, String end) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LineChart graph = null;
		try {
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);
			DateAxis dateAxis = new DateAxis(startDate, endDate);
			NumberAxis numAxis = new NumberAxis();
			ObservableList<Series<Date, Number>> list = data.getVolData(start, end);
			graph = new LineChart(dateAxis, numAxis, list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return graph;
	}

	public StackPane getVolpane(String start, String end){
		StackPane pane = new StackPane();
		LineChart volGraph = this.getVolGraph(start, end);
//		volGraph.getStylesheets().add(getClass().getResource("generalStyle.css").toExternalForm());
		volGraph.getStylesheets().add("generalStyle.css");
		pane.getChildren().addAll(volGraph);
		return pane;
	}

	public LineChart getDealGraph(String start, String end){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LineChart graph = null;
		try {
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);
			DateAxis dateAxis = new DateAxis(startDate, endDate);
//			dateAxis.setStyle("visibility: hidden");
			dateAxis.setId("dateAxis");
			NumberAxis numAxis = new NumberAxis();
			numAxis.setId("numAxis");
			ObservableList<Series<Date, Number>> list = data.getDealData(start, end);
			graph = new LineChart(dateAxis, numAxis, list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return graph;
	}

	public StackPane getAdjpane(String start, String end){
		StackPane pane = new StackPane();
		LineChart adjGraph = this.getDealGraph(start, end);
		adjGraph.getStylesheets().add("generalStyle.css");
		pane.getChildren().addAll(adjGraph);
		return pane;
	}

	public StackPane getGraph(String start, String end){
		StackPane pane = new StackPane();
		LineChart volGraph = this.getVolGraph(start, end);
		LineChart dealGraph = this.getDealGraph(start, end);
//		volGraph.getStylesheets().add(getClass().getResource("generalStyle.css").toExternalForm());
		volGraph.getStylesheets().add("generalStyle.css");
		//dealGraph.getStylesheets().add(getClass().getResource("secondChart.css").toExternalForm());
		dealGraph.getStylesheets().add("secondChart.css");
		dealGraph.setLayoutX(0);
		dealGraph.setLayoutY(0);
		pane.getChildren().addAll(volGraph, dealGraph);
		return pane;
	}


}