/*package voHelper;

import vo.Type;

public class StockWrapper {

    private AdditionalInfoGetter infoGetter = new AdditionalInfoGetter();
    private TypeGetter typeGetter = new TypeGetter();

    public String getName(String id) {
        if (id.equals(""))
            return "";
        else
            return infoGetter.getStockName(id);
    }

    public Type getType(String id) {
        return typeGetter.getType(id);
    }

    public double getDealSum(String id) {
        if (id.equals(""))
            return -1.0;
        else
            return infoGetter.getDealSum(id);
    }

}
*/