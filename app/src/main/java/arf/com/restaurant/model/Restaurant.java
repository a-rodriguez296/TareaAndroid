package arf.com.restaurant.model;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

import arf.com.restaurant.R;

/**
 * Created by arodriguez on 9/19/15.
 */
public class Restaurant {

    private ArrayList<Table> mTables;

    private ArrayList<Dish> mDishes;

    private static Restaurant mInstance;


    //Singleton para obtener una instancia del Restaurante con toda su info asociada
    public static Restaurant getInstance(Context context) {


        if (mInstance == null) {
            mInstance = new Restaurant(context);
        }

        return mInstance;

    }

    //Constructor del Restaurante
    private Restaurant(Context context) {


        //Acá es donde se deben cargar tanto las mesas como los platos

        //Mesas
        Resources res = context.getResources();
        String[] tables = res.getStringArray(R.array.tables_array);

        //Inicialización Array Mesas
        mTables = new ArrayList<>();


        for (String tableName : tables) {
            Table mTable = new Table(tableName);
            mTables.add(mTable);
        }
    }


    public ArrayList<Table> getTables() {
        return mTables;
    }

    public ArrayList<Dish> getDishes() {
        return mDishes;
    }

}
