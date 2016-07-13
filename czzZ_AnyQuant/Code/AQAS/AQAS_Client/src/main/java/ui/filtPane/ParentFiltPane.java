package ui.filtPane;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import ui.main.Main;

/**
 * Created by asus on 2016/3/28.
 */
class ParentFiltPane extends VBox {

    Main main;

    public ParentFiltPane(Main main) {
        super();
        this.main = main;
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setWidth(80);
        this.setLayoutX(1110);
        this.setSpacing(10);
        this.getStylesheets().add("setParentPane.css");
        this.setPadding(new Insets(10));
    }
}
