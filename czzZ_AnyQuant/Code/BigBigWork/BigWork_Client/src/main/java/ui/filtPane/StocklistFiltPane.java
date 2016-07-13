package ui.filtPane;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ui.main.Main;

/**
 * Created by asus on 2016/4/9.
 */
public class StocklistFiltPane extends ParentFiltPane {
    Label select = new Label();
    ImageView selectIcon = new ImageView(new Image("select.png"));

    private void setButtons() {
        select.setGraphic(selectIcon);
        select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new FiltListStage(main);
            }
        });
        this.getChildren().add(select);

    }

    public StocklistFiltPane(Main main) {
        super(main);
        setButtons();
        setPrefSize(80, 60);
        setLayoutY(220);
    }
}
