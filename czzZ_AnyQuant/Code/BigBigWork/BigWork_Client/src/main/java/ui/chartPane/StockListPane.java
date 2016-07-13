package ui.chartPane;


import bl.listServiceImpl.StockList_Impl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import ui.common.MyTable;
import ui.filtPane.MarketFiltPane;
import ui.filtPane.StocklistFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;
import vo.StockVO;

import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/4/9.
 */
public class StockListPane extends ParentChartPane {
    private MyTable tableController;

    public StockListPane(Main main, ArrayList<StockVO> list) {
        super(main);
        main.setChartType(ChartPaneType.STOCK_LIST);
        tableController = new MyTable(main);
        chartPane.getChildren().add(tableController.getListTable(list));
        chartPane.getChildren().add(new StocklistFiltPane(main));
    }
}
