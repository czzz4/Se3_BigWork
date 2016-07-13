package ui.chartDriver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.chartPart.SingleLineGraph;

public class SingleLineChartDriver extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SingleLineGraph test = new SingleLineGraph("sh600979");
        StackPane pane = test.getGraph("2015-10-09", "2015-11-16");
//		LineChart<DateAxis, NumberAxis> chart = test.getVolGraph("2015-10-11", "2015-12-01");
        Scene scene = new Scene(pane, 600, 500);
//		scene.getStylesheets().add(getClass().getResource("generalStyle.css").toExternalForm());
        scene.getStylesheets().add("generalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
