package ui.chartDriver;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.chartPart.KGraph;

public class KGraphDriver extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        KGraph test = new KGraph("sh600979");
        Group root = test.getGroup("2016-03-01", "2016-03-20");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
