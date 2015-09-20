package arf.com.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Table implements Serializable {

    private String mTableName;

    private ArrayList<Dish> mOrderedDishes;


    public Table(String tableName) {
        this.mTableName = tableName;
        mOrderedDishes = new ArrayList<>();

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

    public ArrayList<Dish> getOrderedDishes() {
        return mOrderedDishes;
    }
}
