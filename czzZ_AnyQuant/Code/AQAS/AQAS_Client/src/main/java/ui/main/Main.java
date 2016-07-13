package ui.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.menuPane.MenuPane;
import ui.typeDef.ChartPaneType;

/**
 * Created by asus on 2016/3/28.
 */
public class Main extends Application {
    private static Stage window;
    private Scene scene;

    private MenuPane menuPane;
    private ChartPaneType chartType;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        // new pane
        menuPane = new MenuPane(this);

        //scene
        VBox layout = new VBox();
        menuPane.setLayout(layout);
        layout.getChildren().addAll(menuPane.getPane(), menuPane.getFuncPane(), menuPane.getFuncPane().chartPane.getPane());
        scene = new Scene(layout, 1200, 650);

        window.setResizable(false);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setScene(scene);
        window.show();

        System.out.println("funcPane Y: " + menuPane.getFuncPane().getLayoutY());
        System.out.println("menuPane height: " + menuPane.getPane().getHeight());

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static double[] getLocs() {
        double x = window.getX();
        double y = window.getY();
        double width = window.getWidth();
        double height = window.getHeight();
        double[] info = {x, y, width, height};
        return info;
    }

    public Stage getWindow() {
        return window;
    }

    public MenuPane getMenuPane() {
        return menuPane;
    }

    public void setChartType(ChartPaneType type) {
        this.chartType = type;
    }

    public ChartPaneType getChartType() {
        return chartType;
    }
}
