package data.dataHelper;

import java.text.ParseException;

public class Test {

    /*public static void main(String args[]){
        ArrayList<StockVO> result = new ArrayList<StockVO>();
        ArrayList<String> re = new ArrayList<String>();
        ArrayList<StockVO> ar = new ArrayList<StockVO>();
        StockList_Impl stocklist = new StockList_Impl();
        //File file = new File("file"+"/"+"StockList.txt");
        FileHelper helper = new FileHelper("file"+"/"+"StockList.txt");

        result = stocklist.ShowAll();
        helper.rewrite(result);
        re = helper.read();

        for (String string : re) {
            ar.add(new StockVO(string));
        }

        for(int i=0;i<re.size();i++){
            System.out.println(ar.get(i));
        }


    }*/
    public static void main(String args[]) throws ParseException {
        Refresh ref = new Refresh();
        ref.refresh();
        //System.out.println("0");
    }

}
