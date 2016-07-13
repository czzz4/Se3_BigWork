package ui.chartPane;

import bl.listServiceImpl.Grail_Impl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ui.common.MyTable;
import ui.filtPane.MarketFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;
import vo.GrailVO;

import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/4/9.
 */
public class MarketPane extends ParentChartPane {
    private MyTable tableController;

    public MarketPane(Main main, ArrayList<GrailVO> list) {
        super(main);
        main.setChartType(ChartPaneType.MARKET_LIST);
        tableController = new MyTable(main);
        chartPane.getChildren().add(tableController.getMarketTable(list));
        chartPane.getChildren().add(new MarketFiltPane(main));
    }
}
