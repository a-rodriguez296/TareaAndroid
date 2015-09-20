package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Dish;

/**
 * Created by arodriguez on 9/20/15.
 */
public class DishDetailPagerActivity extends AppCompatActivity {


    public static final String EXTRA_DISH = "DishDetailActivity.EXTRA_DISH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);


        Dish dish = (Dish) getIntent().getSerializableExtra(EXTRA_DISH);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_pager_detail) == null) {

        }

    }
}
