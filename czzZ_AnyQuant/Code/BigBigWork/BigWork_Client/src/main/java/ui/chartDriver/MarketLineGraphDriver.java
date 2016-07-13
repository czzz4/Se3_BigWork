package ui.chartDriver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.chartPart.MarketLineGraph;

public class MarketLineGraphDriver extends Application {
    //@Override
    /*public void start(Stage stage) throws Exception {
		MarketLineGraph test = new MarketLineGraph();
		StackPane pane = test.getAdjpane("2015-10-09", "2015-11-16");
		Scene scene = new Scene(pane, 600, 500);
		scene.getStylesheets().add(getClass().getResource("generalStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}*/

    @Override
    public void start(Stage stage) throws Exception {
        MarketLineGraph test = new MarketLineGraph();
        StackPane pane = test.getVolpane("2015-03-29", "2015-04-15");
        Scene scene = new Scene(pane, 600, 500);
        //scene.getStylesheets().add(getClass().getResource("generalStyle.css").toExternalForm());
        scene.getStylesheets().add("generalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

