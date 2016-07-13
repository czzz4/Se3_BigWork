package ui.menuPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.funcPane.FuncPane;
import ui.main.Main;
import ui.typeDef.MenuType;

/**
 * Created by asus on 2016/3/28.
 */
public class MenuPane {
    private VBox layout;
    private FuncPane funcPane;

    private Pane menuPane;
    private Parent root;

    private MenuType type;

    private MenuController menuController;

    private Main main;
    private Stage window;

    public MenuPane(Main main) throws Exception {
        this.main = main;
        this.window = main.getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/menu.fxml")));

        root = loader.load();
        menuPane = new Pane(root);
        menuPane.setMinHeight(100);
        setMenuType(MenuType.MARKET);

        funcPane = new FuncPane(getMenuType(), main);


        menuController = loader.getController();
        menuController.set(window, funcPane, this);
    }

    public void setMenuType(MenuType menuType) {
        this.type = menuType;
    }

    public MenuType getMenuType() {
        return type;
    }

    public Pane getPane() {
        return menuPane;
    }

    public FuncPane getFuncPane() {
        return funcPane;
    }

    public void setLayout(VBox layout) {
        this.layout = layout;
        funcPane.setLayout(layout);
    }

    public VBox getLayout() {
        return layout;
    }

    public Main getMain() {
        return main;
    }
}

