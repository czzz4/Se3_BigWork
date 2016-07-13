package ui.chartPane;

import bl.managementServiceImpl.Hot_IndustryData_Impl;
import ui.common.GetDate;
import ui.common.MyTable;
import ui.filtPane.HotFiltPane;
import ui.main.Main;
import ui.typeDef.ChartPaneType;

import java.text.ParseException;

/**
 * Created by 15HR-1528SS on 2016/4/12.
 */
public class HotIndustryPane extends ParentChartPane {
    private MyTable tableController;
    private Hot_IndustryData_Impl impl;

    public HotIndustryPane(Main main, String from, String to) throws ParseException {
        super(main);
        chartBG();

        main.setChartType(ChartPaneType.HOT_LIST);
//        impl = new Hot_IndustryData_Impl(GetDate.getDate().get(1), GetDate.getDate().get(0));
        impl = new Hot_IndustryData_Impl();

        tableController = new MyTable(main);
        chartPane.getChildren().add(tableController.getHotIndustryTable(impl.GetOrderList(from, to)));
        chartPane.getChildren().add(new HotFiltPane(main));
    }
}
