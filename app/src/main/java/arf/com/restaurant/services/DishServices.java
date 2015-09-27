package arf.com.restaurant.services;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import arf.com.restaurant.model.Dish;
import cz.msebera.android.httpclient.Header;

/**
 * Created by arodriguez on 9/27/15.
 */
public class DishServices {


    private DishServiceListener mListener;


    public DishServices(DishServiceListener listener) {
        mListener = listener;
    }


    public void requestDishes() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.mocky.io/v2/560856369665b9880569bac8", new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                ArrayList<Dish> responseArray = new ArrayList<Dish>();
                JSONArray dishes = new JSONArray();
                try {
                    dishes = response.getJSONArray("dishes");


                    for (int i = 0; i < dishes.length(); i++) {
                        JSONObject c;
                        try {
                            c = dishes.getJSONObject(i);
                            String name = c.getString("name");
                            String icon = c.getString("icon");
                            JSONArray allergensArray = c.getJSONArray("allergens");

                            ArrayList<String> stAllergensArray = new ArrayList<String>();
                            for (int j = 0; j < allergensArray.length(); j++) {

                                String allergen = allergensArray.getString(j);
                                stAllergensArray.add(allergen);
                            }
                            double price = c.getDouble("price");
                            Dish dish = new Dish(name, icon, stAllergensArray, 1.0, "");
                            responseArray.add(dish);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    if (mListener != null) {
                        mListener.didDownloadDishes(responseArray);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public interface DishServiceListener {

        void didDownloadDishes(ArrayList<Dish> dishes);

    }


}



