package ui.chartDriver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.chartPart.Hot_IndustryPieGraph;

public class Hot_IndustryPieChartDriver extends Application {

    public void start(Stage stage) throws Exception {
        Hot_IndustryPieGraph test = new Hot_IndustryPieGraph();
//		PieChart chart = test.getPieChart("2015-10-21", "2015-11-12");
//		Scene scene = new Scene(chart, 500, 500);
        Scene scene = test.getPieScene("2015-07-09", "2015-07-14");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
