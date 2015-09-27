package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import arf.com.restaurant.R;
import arf.com.restaurant.fragments.AddDishDialogFragment;
import arf.com.restaurant.fragments.DishDetailPagerFragment;
import arf.com.restaurant.fragments.DishListFragment;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/19/15.
 */
public class DishListActivity extends AppCompatActivity implements AddDishDialogFragment.DishListener {


    public static final String TABLE_INDEX_ARGUMENT = "DishListActivity.TABLE_INDEX_ARGUMENT";

    private AddDishDialogFragment mDialog;

    private Table mTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int tableIndex = getIntent().getIntExtra(TABLE_INDEX_ARGUMENT, 0);
        mTable = Restaurant.getInstance(this).getTables().get(tableIndex);


        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_dish_list, DishListFragment.newInstance(tableIndex))
                    .commit();
        }

        if (findViewById(R.id.fragment_dish_detail) != null) {
            if (fm.findFragmentById(R.id.fragment_dish_detail) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_dish_detail, DishDetailPagerFragment.newInstance(0, tableIndex))
                        .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dish_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_dish) {
            mDialog = new AddDishDialogFragment();
            mDialog.show(getFragmentManager(), null);
            return true;
        }
        else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dishAdded(Dish dish, String comments) {

        Restaurant.getInstance(this).addDishToTable(mTable, dish, comments);
        mDialog.dismiss();

        Snackbar.make(
                findViewById(android.R.id.content),
                "Plato añadido",
                Snackbar.LENGTH_SHORT)
                .show();

    }
}
