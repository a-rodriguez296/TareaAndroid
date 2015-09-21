package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import arf.com.restaurant.R;
import arf.com.restaurant.fragments.DishDetailPagerFragment;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/20/15.
 */
public class DishDetailPagerActivity extends AppCompatActivity {


    public static final String EXTRA_DISH_INDEX = "DishDetailActivity.EXTRA_DISH_INDEX";

    public static final String EXTRA_PARENT_TABLE_INDEX = "DishDetailActivity.EXTRA_PARENT_TABLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        int dishIndex = getIntent().getIntExtra(EXTRA_DISH_INDEX, 0);
        int parentTableIndex = getIntent().getIntExtra(EXTRA_PARENT_TABLE_INDEX, 0);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_pager_detail) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_dish_pager_detail, DishDetailPagerFragment.newInstance(dishIndex, parentTableIndex))
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
