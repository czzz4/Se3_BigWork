package ui.chartDriver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.chartPart.Hot_IndustryBarChart;

public class Hot_IndustryBarChartDriver extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Hot_IndustryBarChart test = new Hot_IndustryBarChart();
//		BarChart<String, Number> chart = test.getBarChart("2015-10-09", "2015-11-16");
        Pane pane = test.getBarScene("2015-10-15", "2015-11-12");
		Scene scene = new Scene(pane, 500, 500);
        //stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
