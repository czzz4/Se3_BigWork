package ui.common;

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
 * Created by 15HR-1528SS on 2016/3/20.
 */
public class ConfirmDialog {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.setTitle(title);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setOpacity(0.9);

        Label label = new Label(message);

        Button yesbutton = new Button("是");
        yesbutton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button nobutton = new Button("否");
        nobutton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        yesbutton.setPrefWidth(60);
        nobutton.setPrefWidth(60);

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


        HBox h = new HBox(yesbutton, nobutton);
        h.setSpacing(15);
        h.setPadding(new Insets(10, 10, 10, 10));
        h.setAlignment(Pos.CENTER);

        VBox v = new VBox(tip, label, h);
        v.setSpacing(10);
        v.setAlignment(Pos.CENTER);
        v.getStylesheets().add("setMessageDialog.css");

        Scene scene = new Scene(v, 220, 140);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
