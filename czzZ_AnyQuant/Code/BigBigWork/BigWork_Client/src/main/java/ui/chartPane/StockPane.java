package ui.chartPane;

import bl.listServiceImpl.SingleStock_Impl;
import bl.managementServiceImpl.ATR_Impl;
import bl.managementServiceImpl.BIAS_Impl;
import bl.managementServiceImpl.EWMA_Impl;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ui.common.GetDate;
import ui.common.MyTable;
import ui.filtPane.MarketFiltPane;
import ui.filtPane.SingleStockFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;
import vo.StockVO;

import java.text.ParseException;

/**
 * Created by 15HR-1528SS on 2016/4/9.
 */
public class StockPane extends ParentChartPane {
    private MyTable tableController;
    private SingleStock_Impl ss;

    public StockPane(Main main, String id) throws ParseException {
        super(main);
        main.setChartType(ChartPaneType.STOCK);

        ss = new SingleStock_Impl();
        atr = new ATR_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));
        bias = new BIAS_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));
        ewma = new EWMA_Impl(id, GetDate.getDate().get(1), GetDate.getDate().get(0));

        setLabels();

        tableController = new MyTable(main);

        chartPane.getChildren().add(tableController.getStockTable(ss.Show(id)));
        chartPane.getChildren().add(new SingleStockFiltPane(main, id));
    }

    public StockPane(Main main, String id, String df, String dt) throws ParseException {
        super(main);
        main.setChartType(ChartPaneType.STOCK);

        ss = new SingleStock_Impl();
        atr = new ATR_Impl(id, df, dt);
        bias = new BIAS_Impl(id, df, dt);
        ewma = new EWMA_Impl(id, df, dt);

        setLabels();

        tableController = new MyTable(main);

        chartPane.getChildren().add(tableController.getStockTable(ss.setTime(id, df, dt)));
        chartPane.getChildren().add(new SingleStockFiltPane(main, id));
    }
}
