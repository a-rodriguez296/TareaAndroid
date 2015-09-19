package arf.com.restaurant.model;

import java.util.ArrayList;

/**
 * Created by arodriguez on 9/19/15.
 */
public class Table {

    private String tableName;

    private ArrayList<Dish> mOrderedDishes;


    public Table(String tableName) {
        this.tableName = tableName;

    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
