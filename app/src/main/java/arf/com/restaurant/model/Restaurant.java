package arf.com.restaurant.model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import arf.com.restaurant.R;
import cz.msebera.android.httpclient.Header;

/**
 * Created by arodriguez on 9/19/15.
 */
public class Restaurant {

    private static final String SYNCHRONIZED_KEY = "Restaurant.SYNCHRONIZED_KEY";

    public static final String TABLE_CHANGED_ACTION = "Restaurant.TABLE_CHANGED_ACTION";


    private WeakReference<RestaurantModelListener> mModelListener;

    private ArrayList<Table> mTables;

    private ArrayList<Dish> mDishes;

    private static Restaurant mInstance;

    private WeakReference<Context> mContext;


    //Singleton para obtener una instancia del Restaurante con toda su info asociada
    public static Restaurant getInstance(Context context) {


        if (mInstance == null || mInstance.mContext.get() == null) {
            synchronized (SYNCHRONIZED_KEY) {
                if (mInstance == null) {
                    mInstance = new Restaurant(context);
                } else if (mInstance.mContext.get() == null) {
                    mInstance.mContext = new WeakReference<Context>(context);
                }
            }
        }

        return mInstance;

    }

    //Constructor del Restaurante
    private Restaurant(Context context) {

        mContext = new WeakReference<Context>(context);

        mModelListener = new WeakReference<RestaurantModelListener>((RestaurantModelListener) context);

        //Acá es donde se deben cargar tanto las mesas como los platos

        //Mesas
        initTables(context);

        //Platos
        initDishes(context);


        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mModelListener.get() != null) {
                    mModelListener.get().dataDidLoad();
                }

            }
        }, 2500);



        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.mocky.io/v2/560320de3a8c44bf039fcb06", new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                JSONArray dishes = new JSONArray();
                try {
                    dishes = response.getJSONArray("dishes");


                    for (int i = 0; i < dishes.length(); i++) {
                        JSONObject c;
                        try {
                            c = dishes.getJSONObject(i);
                            String name = c.getString("name");
                            String icon = c.getString("icon");
                            ArrayList<String> allergens = (ArrayList<String>) Arrays.asList(c.getString("allergens").split(","));
                            double price = c.getDouble("price");



                            Dish dish = new Dish(name,icon,allergens,1.0,"");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }





            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline


                // Do something with the response
                System.out.println("Hola");
            }

        });


    }

    private void initTables(Context context) {

        Resources res = context.getResources();
        String[] tables = res.getStringArray(R.array.tables_array);

        //Inicialización Array Mesas
        mTables = new ArrayList<>();


        for (String tableName : tables) {
            Table mTable = new Table(tableName);
            mTables.add(mTable);
        }

    }

    private void initDishes(Context context) {

        Resources res = context.getResources();
        String[] dishes = res.getStringArray(R.array.dishes_array);

        //Inicialización Array Platos
        mDishes = new ArrayList<>();

        for (String dishName :
                dishes) {

            ArrayList<String> alergensArray = new ArrayList<String>(Arrays.asList("Cheese", "Pepperoni", "Black Olives"));
            Dish mDish = new Dish(dishName, "dishImage", alergensArray, 123.123, "");
            mDishes.add(mDish);
        }
    }


    public ArrayList<Table> getTables() {
        return mTables;
    }

    public ArrayList<Dish> getDishes() {
        return mDishes;
    }


    public void addDishToTable(Table currentTable, Dish dish, String comments) {

        Dish createdDish = Dish.copyDish(dish, comments);


        /*Dish newDish = new Dish(name, image, containsAlergens, price, comments);*/
        currentTable.addDish(createdDish);


        //Mandar el broadcast
        sendBroadcastTableModelChange();

    }


    public void sendBroadcastTableModelChange() {

        if (mContext != null) {
            Intent broadcast = new Intent(TABLE_CHANGED_ACTION);
            mContext.get().sendBroadcast(broadcast);
        }

    }


    //Listener
    public interface RestaurantModelListener {

        void dataDidLoad();
    }


}
