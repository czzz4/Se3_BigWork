package ui.messagePane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by 15HR-1528SS on 2016/4/6.
 */
public class MessageDialog {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.setTitle(title);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setResizable(false);
        window.setOpacity(0.9);
        window.initModality(Modality.APPLICATION_MODAL);


        String m = "提示";
        Label tips = new Label(m);
        ImageView t = new ImageView(new Image("tips.png"));
        t.setFitHeight(15);
        t.setFitWidth(15);
        tips.setGraphic(t);
        tips.setContentDisplay(ContentDisplay.LEFT);
        tips.setPrefSize(80, 15);
        HBox tip = new HBox(10);
        tip.getChildren().add(tips);
        tip.setPadding(new Insets(0, 60, 0, 20));


        Label label = new Label(message);
        Button button = new Button("好的");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(tip, label, button);
        layout.setSpacing(25);
        layout.setAlignment(Pos.CENTER);
        layout.getStylesheets().add("setMessageDialog.css");

        Scene scene = new Scene(layout, 200, 140);
        window.setScene(scene);
        window.showAndWait();
    }
}

