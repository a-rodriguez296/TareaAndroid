package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import arf.com.restaurant.R;
import arf.com.restaurant.fragments.AddDishDialogFragment;
import arf.com.restaurant.fragments.DishDetailFragment;
import arf.com.restaurant.fragments.DishDetailPagerFragment;
import arf.com.restaurant.fragments.DishListFragment;
import arf.com.restaurant.fragments.GetTotalDialogFragment;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/19/15.
 */
public class DishListActivity extends AppCompatActivity implements AddDishDialogFragment.DishListener, DishListFragment.TableClickListener {


    public static final String TABLE_INDEX_ARGUMENT = "DishListActivity.TABLE_INDEX_ARGUMENT";

    private AddDishDialogFragment mAddDishDialog;

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
            mAddDishDialog = new AddDishDialogFragment();
            mAddDishDialog.show(getFragmentManager(), null);
            return true;
        } else if (id == R.id.get_check) {
            GetTotalDialogFragment mGetTotalDialog = GetTotalDialogFragment.newInstance(getCheck());
            mGetTotalDialog.show(getFragmentManager(), null);
            return true;

        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem mTotal = menu.findItem(R.id.get_check);
        mTotal.setEnabled(mTable.getOrderedDishes().size() > 0);

        return super.onPrepareOptionsMenu(menu);
    }

    public String getCheck() {

        double total = 0;

        for (Dish orderedDish :
                mTable.getOrderedDishes()) {
            total += orderedDish.getPrice();
        }
        return "" + total;
    }


    //Listeners

    @Override
    public void dishAdded(Dish dish, String comments) {

        Restaurant.getInstance(this).addDishToTable(mTable, dish, comments);
        mAddDishDialog.dismiss();

        invalidateOptionsMenu();

        Snackbar.make(
                findViewById(android.R.id.content),
                "Plato añadido",
                Snackbar.LENGTH_SHORT)
                .show();

    }

    @Override
    public void didSelectItemAtIndex(int index) {

        if (findViewById(R.id.fragment_dish_detail) != null) {

            //Caso split screen

            FragmentManager fm = getFragmentManager();
            DishDetailPagerFragment detailFragment = (DishDetailPagerFragment) fm.findFragmentById(R.id.fragment_dish_detail);
            detailFragment.goToDishWithIndex(index);
        } else {
            Intent dishDetailIntent = new Intent(this, DishDetailPagerActivity.class);
            dishDetailIntent.putExtra(DishDetailPagerActivity.EXTRA_DISH_INDEX, index);
            dishDetailIntent.putExtra(DishDetailPagerActivity.EXTRA_PARENT_TABLE_INDEX, index);
            startActivity(dishDetailIntent);
        }
    }


}
