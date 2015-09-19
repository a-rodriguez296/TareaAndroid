package arf.com.restaurant.model;

import java.util.ArrayList;

/**
 * Created by arodriguez on 9/19/15.
 */
public class Table {

    private String mTableName;

    private ArrayList<Dish> mOrderedDishes;


    public Table(String tableName) {
        this.mTableName = tableName;

    }

    @Override
    public String toString() {
        return mTableName;
    }

    public String getTableName() {
        return mTableName;
    }

    public void setTableName(String tableName) {
        this.mTableName = tableName;
    }
}
