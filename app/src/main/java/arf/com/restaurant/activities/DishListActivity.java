package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import arf.com.restaurant.DishListFragment;
import arf.com.restaurant.R;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/19/15.
 */
public class DishListActivity extends AppCompatActivity {


    public static final String TABLE_ARGUMENT = "DishListActivity.TABLE_ARGUMENT";

    private Button mAddDishButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        final Table selectedTable = (Table) getIntent().getSerializableExtra(TABLE_ARGUMENT);


        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_dish_list, DishListFragment.newInstance(selectedTable))
                    .commit();
        }


        mAddDishButton = (Button) findViewById(R.id.add_dish_button);
        if (mAddDishButton != null) {
            mAddDishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Restaurant.getInstance(DishListActivity.this).addDishToTable(selectedTable, "Plato", "image", true, 123.123, "");
                }
            });
        }
    }
}
