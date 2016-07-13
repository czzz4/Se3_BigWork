package ui.common;

import bl.listServiceImpl.Grail_Impl;
import bl.listServiceImpl.SingleStock_Impl;
import bl.listServiceImpl.StockList_Impl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ui.chartPane.StockListPane;
import ui.chartPane.StockPane;
import ui.main.Main;
import vo.GrailVO;
import vo.IndustryVO;
import vo.StockVO;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * Created by 15HR-1528SS on 2016/4/9.
 */
public class MyTable {
    private TableView myTable;
    private Grail_Impl market_impl;
    private StockList_Impl list_impl;
    private SingleStock_Impl stock_impl;
    private Main main;

    public MyTable(Main m) {
        this.main = m;
    }

    public static class Stock {
        SimpleStringProperty id, open, high, low, close, adj_price, volume, turnover, pe, pb, date;

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public String getOpen() {
            return open.get();
        }

        public SimpleStringProperty openProperty() {
            return open;
        }

        public void setOpen(String open) {
            this.open.set(open);
        }

        public String getHigh() {
            return high.get();
        }

        public SimpleStringProperty highProperty() {
            return high;
        }

        public void setHigh(String high) {
            this.high.set(high);
        }

        public String getLow() {
            return low.get();
        }

        public SimpleStringProperty lowProperty() {
            return low;
        }

        public void setLow(String low) {
            this.low.set(low);
        }

        public String getAdj_price() {
            return adj_price.get();
        }

        public SimpleStringProperty adj_priceProperty() {
            return adj_price;
        }

        public void setAdj_price(String adj_price) {
            this.adj_price.set(adj_price);
        }

        public String getClose() {
            return close.get();
        }

        public SimpleStringProperty closeProperty() {
            return close;
        }

        public void setClose(String close) {
            this.close.set(close);
        }

        public String getVolume() {
            return volume.get();
        }

        public SimpleStringProperty volumeProperty() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume.set(volume);
        }

        public String getTurnover() {
            return turnover.get();
        }

        public SimpleStringProperty turnoverProperty() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover.set(turnover);
        }

        public String getPe() {
            return pe.get();
        }

        public SimpleStringProperty peProperty() {
            return pe;
        }

        public void setPe(String pe) {
            this.pe.set(pe);
        }

        public String getPb() {
            return pb.get();
        }

        public SimpleStringProperty pbProperty() {
            return pb;
        }

        public void setPb(String pb) {
            this.pb.set(pb);
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public Stock(String id, double open, double high, double low, double close, double adj_price, int volume, double turnover, double pe, double pb, String date) {
            this.id = new SimpleStringProperty(id);
            this.open = new SimpleStringProperty(open + "");
            this.high = new SimpleStringProperty(high + "");
            this.low = new SimpleStringProperty(low + "");
            this.close = new SimpleStringProperty(close + "");
            this.adj_price = new SimpleStringProperty(adj_price + "");
            this.volume = new SimpleStringProperty(volume + "");
            this.turnover = new SimpleStringProperty(turnover + "");
            this.pe = new SimpleStringProperty(pe + "");
            this.pb = new SimpleStringProperty(pb + "");
            this.date = new SimpleStringProperty(date + "");
        }
    }

    public static class Grail {
        SimpleStringProperty id, open, high, low, close, adj_price, volume, date;

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public String getOpen() {
            return open.get();
        }

        public SimpleStringProperty openProperty() {
            return open;
        }

        public void setOpen(String open) {
            this.open.set(open);
        }

        public String getHigh() {
            return high.get();
        }

        public SimpleStringProperty highProperty() {
            return high;
        }

        public void setHigh(String high) {
            this.high.set(high);
        }

        public String getLow() {
            return low.get();
        }

        public SimpleStringProperty lowProperty() {
            return low;
        }

        public void setLow(String low) {
            this.low.set(low);
        }

        public String getClose() {
            return close.get();
        }

        public SimpleStringProperty closeProperty() {
            return close;
        }

        public void setClose(String close) {
            this.close.set(close);
        }

        public String getAdj_price() {
            return adj_price.get();
        }

        public SimpleStringProperty adj_priceProperty() {
            return adj_price;
        }

        public void setAdj_price(String adj_price) {
            this.adj_price.set(adj_price);
        }

        public String getVolume() {
            return volume.get();
        }

        public SimpleStringProperty volumeProperty() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume.set(volume);
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public Grail(String id, double open, double high, double low, double close, double adj_price, String volume, String date) {
            this.id = new SimpleStringProperty(id);
            this.open = new SimpleStringProperty(open + "");
            this.high = new SimpleStringProperty(high + "");
            this.low = new SimpleStringProperty(low + "");
            this.close = new SimpleStringProperty(close + "");
            this.adj_price = new SimpleStringProperty(adj_price + "");
            this.volume = new SimpleStringProperty(volume);
            this.date = new SimpleStringProperty(date + "");
        }
    }

    public static class Industry {
        SimpleStringProperty rank, name;

        public Industry(int rank, String name) {
            this.rank = new SimpleStringProperty(rank + "");
            this.name = new SimpleStringProperty(name);
        }

        public String getRank() {
            return rank.get();
        }

        public SimpleStringProperty rankProperty() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank.set(rank);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }
    }

    public TableView getListTable(ArrayList<StockVO> list) {
        myTable = new TableView<>();

        TableColumn idColumn = new TableColumn("股票ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("id"));

        TableColumn openColumn = new TableColumn("开盘价");
        openColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("open"));

        TableColumn highColumn = new TableColumn("最高价");
        highColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("high"));

        TableColumn lowColumn = new TableColumn("最低价");
        lowColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("low"));

        TableColumn closeColumn = new TableColumn("收盘价");
        closeColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("close"));

        TableColumn adjColumn = new TableColumn("后复权价");
        adjColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("adj_price"));

        TableColumn volumeColumn = new TableColumn("成交量");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("volume"));

        TableColumn turnColumn = new TableColumn("转手率");
        turnColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("turnover"));

        TableColumn peColumn = new TableColumn("市盈率");
        peColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("pe"));

        TableColumn pbColumn = new TableColumn("市净率");
        pbColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("pb"));

        TableColumn dateColumn = new TableColumn<>("更新日期");
        dateColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("date"));


        list_impl = new StockList_Impl();

        myTable.setPrefSize(900, 450);
        myTable.setItems(getStockListData(list));
        myTable.getColumns().addAll(idColumn, openColumn, highColumn, lowColumn, closeColumn, adjColumn, volumeColumn, turnColumn, peColumn, pbColumn, dateColumn);
        setTableStyle(idColumn, TableType.LIST);

        idColumn.setPrefWidth(100);
        openColumn.setPrefWidth(85);
        highColumn.setPrefWidth(85);
        lowColumn.setPrefWidth(85);
        closeColumn.setPrefWidth(85);
        adjColumn.setPrefWidth(85);
        volumeColumn.setPrefWidth(105);
        turnColumn.setPrefWidth(50);
        peColumn.setPrefWidth(60);
        pbColumn.setPrefWidth(50);
        dateColumn.setPrefWidth(120);

        return myTable;
    }

    public TableView getMarketTable(ArrayList<GrailVO> list) {
        myTable = new TableView<Grail>();

        TableColumn idColumn = new TableColumn("大盘类型");
        idColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("id"));

        TableColumn openColumn = new TableColumn("开盘价");
        openColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("open"));

        TableColumn highColumn = new TableColumn("最高价");
        highColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("high"));

        TableColumn lowColumn = new TableColumn("最低价");
        lowColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("low"));

        TableColumn closeColumn = new TableColumn("收盘价");
        closeColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("close"));

        TableColumn adjColumn = new TableColumn("后复权价");
        adjColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("adj_price"));

        TableColumn volumeColumn = new TableColumn("成交量");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("volume"));

        TableColumn dateColumn = new TableColumn<>("更新日期");
        dateColumn.setCellValueFactory(new PropertyValueFactory<Grail, String>("date"));


        market_impl = new Grail_Impl();

        myTable.setPrefSize(900, 450);

        myTable.setItems(getMarketData(list));
        myTable.getColumns().addAll(idColumn, openColumn, highColumn, lowColumn, closeColumn, adjColumn, volumeColumn, dateColumn);
        setTableStyle(idColumn, TableType.MARKET);

        idColumn.setPrefWidth(100);
        openColumn.setPrefWidth(100);
        highColumn.setPrefWidth(100);
        lowColumn.setPrefWidth(100);
        closeColumn.setPrefWidth(100);
        adjColumn.setPrefWidth(100);
        volumeColumn.setPrefWidth(150);
        dateColumn.setPrefWidth(150);

        return myTable;
    }

    public TableView getStockTable(ArrayList<StockVO> list) {
        myTable = new TableView<>();

        TableColumn idColumn = new TableColumn("股票ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("id"));

        TableColumn openColumn = new TableColumn("开盘价");
        openColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("open"));

        TableColumn highColumn = new TableColumn("最高价");
        highColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("high"));

        TableColumn lowColumn = new TableColumn("最低价");
        lowColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("low"));

        TableColumn closeColumn = new TableColumn("收盘价");
        closeColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("close"));

        TableColumn adjColumn = new TableColumn("后复权价");
        adjColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("adj_price"));

        TableColumn volumeColumn = new TableColumn("成交量");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("volume"));

        TableColumn turnColumn = new TableColumn("转手率");
        turnColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("turnover"));

        TableColumn peColumn = new TableColumn("市盈率");
        peColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("pe"));

        TableColumn pbColumn = new TableColumn("市净率");
        pbColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("pb"));

        TableColumn dateColumn = new TableColumn<>("更新日期");
        dateColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("date"));

        stock_impl = new SingleStock_Impl();

        myTable.setPrefSize(900, 450);
        myTable.setItems(getStockListData(list));
        myTable.getColumns().addAll(idColumn, openColumn, highColumn, lowColumn, closeColumn, adjColumn, volumeColumn, turnColumn, peColumn, pbColumn, dateColumn);
        setTableStyle(idColumn, TableType.STOCK);

        idColumn.setPrefWidth(100);
        openColumn.setPrefWidth(85);
        highColumn.setPrefWidth(85);
        lowColumn.setPrefWidth(85);
        closeColumn.setPrefWidth(85);
        adjColumn.setPrefWidth(85);
        volumeColumn.setPrefWidth(105);
        turnColumn.setPrefWidth(50);
        peColumn.setPrefWidth(60);
        pbColumn.setPrefWidth(50);
        dateColumn.setPrefWidth(120);

        return myTable;
    }

    public TableView getHotIndustryTable(ArrayList<IndustryVO> list) {
        myTable = new TableView<>();

        TableColumn rankColumn = new TableColumn("排名");
        rankColumn.setCellValueFactory(new PropertyValueFactory<Industry, String>("rank"));

        TableColumn nameColumn = new TableColumn("行业名称");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Industry, String>("name"));

        myTable.setPrefSize(900, 450);
        myTable.setItems(getIndustryData(list));
        myTable.getColumns().addAll(rankColumn, nameColumn);

        rankColumn.setPrefWidth(280);
        nameColumn.setPrefWidth(620);

        setTableStyle(rankColumn, TableType.HOTINDUSTRY);
        return myTable;
    }

    public void setTableStyle(TableColumn idColumn, TableType type) {
        myTable.setLayoutY(25);
        myTable.setLayoutX(15);
        myTable.getStylesheets().add("TableStyle.css");

        idColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell<Grail, String>() {
                    ObservableValue ov;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            ov = getTableColumn().getCellObservableValue(getIndex());
                            setText(item);
                        }
                        if (getTableRow() != null) {
                            int row = this.getTableRow().getIndex();

                            if (row % 2 == 0)
                                this.getTableRow().setStyle("-fx-background-color: #adae8f;-fx-table-cell-border-color: #adae8f");
                            else
                                this.getTableRow().setStyle("-fx-background-color: #919d9f;-fx-table-cell-border-color: #919d9f");
                            if (type == TableType.LIST)
                                this.getTableRow().setOnMouseClicked((MouseEvent e) -> {
                                    if (e.getClickCount() == 2) {
                                        main.getMenuPane().getLayout().getChildren().remove(2);
                                        try {
                                            main.getMenuPane().getLayout().getChildren().add(new StockPane(main, item).getPane());
                                        } catch (ParseException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                });
//                            this.getTableRow().setOnMouseEntered((MouseEvent e) -> {
//                                this.getTableRow().setStyle("-fx-background-color: aqua");
//                            });
//                            this.getTableRow().setOnMouseExited((MouseEvent e) -> {
//                                if (row % 2 == 0)
//                                    this.getTableRow().setStyle("-fx-background-color: #F6F7D7;-fx-table-cell-border-color: #F6F7D7");
//                                else
//                                    this.getTableRow().setStyle("-fx-background-color: #DFEBED;-fx-table-cell-border-color: #DFEBED");
//                            });
                        }
                    }
                };
            }
        });
    }

    public ObservableList<Stock> getStockListData(ArrayList<StockVO> list) {
        ObservableList<Stock> stockList = FXCollections.observableArrayList();

        for (StockVO stock : list)
            stockList.add(new Stock(stock.getId(), stock.getOpen(), stock.getHigh(), stock.getLow(), stock.getClose(), stock.getAdj(), stock.getVolume(), stock.getTurnover(), stock.getPe(), stock.getPd(), stock.getDate()));
        return stockList;
    }

    public ObservableList<Industry> getIndustryData(ArrayList<IndustryVO> list) {
        ObservableList<Industry> industryList = FXCollections.observableArrayList();

        for (IndustryVO industry : list)
            industryList.add(new Industry(industry.getRank(), industry.getName()));
        return industryList;
    }


    public ObservableList<Grail> getMarketData(ArrayList<GrailVO> list) {
        ObservableList<Grail> marketList = FXCollections.observableArrayList();

        for (GrailVO grail : list) {
            marketList.add(new Grail(grail.getID(), grail.getOpen(), grail.getHigh(), grail.getLow(), grail.getClose(), grail.getAdj_price(), grail.getVolume(), grail.getDate()));
        }
        System.out.println("obser list index is : " + marketList.size());
        System.out.println("the first line is : " + marketList.get(0).id);
        return marketList;
    }
}
