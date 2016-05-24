package com.bigwork.model;

import com.bigwork.model.Type;

/**
 * Created by asus on 2016/5/7.
 */
public class TypeGetter {

    public Type getType(String id) {
        Type type = Type.MED;
        switch (id) {
            case "sz002644":
            case "sz000908":
            case "sh600216":
            case "sh600129":
                type = Type.MED;
                break;
            case "sz002664":
            case "sh600979":
            case "sz300137":
                type = Type.ELEC;
                break;
            case "sh600724":
                type = Type.ESTATE;
                break;
            case "sz002569":
                type = Type.SALES;
                break;
        }

        return type;
    }
}
