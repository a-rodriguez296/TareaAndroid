package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arf.com.restaurant.R;
import arf.com.restaurant.fragments.DishDetailFragment;
import arf.com.restaurant.fragments.DishDetailPagerFragment;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/20/15.
 */
public class DishDetailPagerActivity extends AppCompatActivity {


    public static final String EXTRA_DISH = "DishDetailActivity.EXTRA_DISH";

    public static final String EXTRA_PARENT_TABLE = "DishDetailActivity.EXTRA_PARENT_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);


        Dish dish = (Dish) getIntent().getSerializableExtra(EXTRA_DISH);
        Table parentTable = (Table) getIntent().getSerializableExtra(EXTRA_PARENT_TABLE);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_pager_detail) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_dish_pager_detail, DishDetailPagerFragment.newInstance(dish, parentTable))
                    .commit();
        }

    }
}
