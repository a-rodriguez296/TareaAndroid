package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arf.com.restaurant.DishListFragment;
import arf.com.restaurant.R;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/19/15.
 */
public class DishListActivity extends AppCompatActivity {


    public static final String TABLE_ARGUMENT = "DishListActivity.TABLE_ARGUMENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        Table selectedTable = (Table) getIntent().getSerializableExtra(TABLE_ARGUMENT);


        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_dish_list, DishListFragment.newInstance(selectedTable))
                    .commit();
        }
    }
}
