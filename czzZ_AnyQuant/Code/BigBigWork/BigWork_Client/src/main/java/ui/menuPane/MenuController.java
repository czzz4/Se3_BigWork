package ui.menuPane;

import bl.listServiceImpl.SingleStock_Impl;
import data.dataHelper.Refresh;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ui.chartPane.StockPane;
import ui.common.ConfirmDialog;
import ui.common.MessageDialog;
import ui.funcPane.FuncPane;
import ui.typeDef.MenuType;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Created by asus on 2016/3/28.
 */

public class MenuController implements Initializable {
    private Stage window;
    private MenuPane menuPane;
    private FuncPane funcPane;
    private SingleStock_Impl impl;

    @FXML
    private Label market;
    @FXML
    private Label stock;
    @FXML
    private TextField input;
    @FXML
    private Label refresh;
    @FXML
    private Label collection;
    @FXML
    private Label settings;
    @FXML
    private Label exit;
    @FXML
    private Label search;

    private String searchInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchInfo = "";
        market.setGraphic(new ImageView(new Image("selectedMarket.jpg")));
        stock.setGraphic(new ImageView(new Image("unselectedList.jpg")));
        search.setGraphic(new ImageView(new Image("search2.png")));
        market.setOnMouseEntered((MouseEvent e) -> {
            market.setGraphic(new ImageView(new Image("touchedMarket.jpg")));
        });
        stock.setOnMouseEntered((MouseEvent e) -> {
            stock.setGraphic(new ImageView(new Image("touchedList.jpg")));
        });
        search.setOnMouseEntered((MouseEvent e) -> search.setGraphic(new ImageView(new Image("search.png"))));
        search.setOnMouseExited((MouseEvent e) -> search.setGraphic(new ImageView(new Image("search2.png"))));

        market.setOnMouseExited((MouseEvent e) -> {
            if (menuPane.getMenuType() == MenuType.MARKET)
                market.setGraphic(new ImageView(new Image("selectedMarket.jpg")));
            else
                market.setGraphic(new ImageView(new Image("unselectedMarket.jpg")));
        });

        stock.setOnMouseExited((MouseEvent e) -> {
            if (menuPane.getMenuType() == MenuType.STOCK)
                stock.setGraphic(new ImageView(new Image("selectedList.jpg")));
            else
                stock.setGraphic(new ImageView(new Image("unselectedList.jpg")));
        });

        refresh.setOnMouseClicked(e -> {
            Refresh re = new Refresh();
            if (re.refresh())
                MessageDialog.display("刷新", "刷新成功，本地数据已更新");
        });

        collection.setOnMouseClicked(e -> MessageDialog.display("通知", "收藏功能尚待开发，敬请期待"));
        settings.setOnMouseClicked(e -> MessageDialog.display("通知", "设置功能尚待开发，敬请期待"));

        refresh.setGraphic(new ImageView(new Image("refresh.png")));
        refresh.setOnMouseEntered((MouseEvent e) -> refresh.setGraphic(new ImageView(new Image("refresh2.png"))));
        refresh.setOnMouseExited((MouseEvent e) -> refresh.setGraphic(new ImageView(new Image("refresh.png"))));
        collection.setGraphic(new ImageView(new Image("collection.png")));
        collection.setOnMouseEntered((MouseEvent e) -> collection.setGraphic(new ImageView(new Image("collection2.png"))));
        collection.setOnMouseExited((MouseEvent e) -> collection.setGraphic(new ImageView(new Image("collection.png"))));
        settings.setGraphic(new ImageView(new Image("setting.png")));
        settings.setOnMouseEntered((MouseEvent e) -> settings.setGraphic(new ImageView(new Image("setting2.png"))));
        settings.setOnMouseExited((MouseEvent e) -> settings.setGraphic(new ImageView(new Image("setting.png"))));
        exit.setGraphic(new ImageView(new Image("close.png")));
        exit.setOnMouseEntered((MouseEvent e) -> exit.setGraphic(new ImageView(new Image("close2.png"))));
        exit.setOnMouseExited((MouseEvent e) -> exit.setGraphic(new ImageView(new Image("close.png"))));
    }

    @FXML
    public void changeToMarketPane() throws ParseException {
        System.out.println("Change to market pane...");
        menuPane.setMenuType(MenuType.MARKET);
        market.setGraphic(new ImageView(new Image("touchedMarket.jpg")));
        stock.setGraphic(new ImageView(new Image("unselectedList.jpg")));
        funcPane.refresh(menuPane.getMenuType());
    }


    @FXML
    public void changeToStockPane() throws ParseException {
        System.out.println("Change to stock pane...");
        menuPane.setMenuType(MenuType.STOCK);
        market.setGraphic(new ImageView(new Image("unselectedMarket.jpg")));
        stock.setGraphic(new ImageView(new Image("selectedList.jpg")));
        funcPane.refresh(menuPane.getMenuType());
    }

    @FXML
    public void exit() {
        System.out.println("Exit AQAS System...");
        if (ConfirmDialog.display("退出系统", "是否确认退出？"))
            window.close();
    }

    @FXML
    public void search() throws ParseException {
        impl = new SingleStock_Impl();
        System.out.println("searching...");
        searchInfo = input.getText();
        if (impl.Show(searchInfo).size() == 0)
            MessageDialog.display("错误", "输入的ID有误，请按照格式输入");
        else {
            changeToStockPane();
            menuPane.getLayout().getChildren().remove(2);
            menuPane.getLayout().getChildren().add(new StockPane(menuPane.getMain(), searchInfo).getPane());
        }

        input.clear();
    }

    public void set(Stage w, FuncPane f, MenuPane p) {
        this.window = w;
        this.menuPane = p;
        this.funcPane = f;
    }


}
