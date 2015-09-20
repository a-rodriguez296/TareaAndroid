package arf.com.restaurant.activities;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import arf.com.restaurant.R;
import arf.com.restaurant.fragments.AddDishDialogFragment;
import arf.com.restaurant.fragments.DishListFragment;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/19/15.
 */
public class DishListActivity extends AppCompatActivity implements AddDishDialogFragment.DishListener {


    public static final String TABLE_ARGUMENT = "DishListActivity.TABLE_ARGUMENT";

    private AddDishDialogFragment mDialog;

    private Button mAddDishButton;
    private Table mTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        mTable = (Table) getIntent().getSerializableExtra(TABLE_ARGUMENT);


        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_dish_list, DishListFragment.newInstance(mTable))
                    .commit();
        }


        mAddDishButton = (Button) findViewById(R.id.add_dish_button);
        if (mAddDishButton != null) {
            mAddDishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Restaurant.getInstance(DishListActivity.this).addDishToTable(selectedTable, "Plato", "image", true, 123.123, "");
                    mDialog = new AddDishDialogFragment();
                    mDialog.show(getFragmentManager(), null);
                }
            });
        }
    }

    @Override
    public void dishAdded(Dish dish, String comments) {

        Restaurant.getInstance(this).addDishToTable(mTable, dish, comments);
        mDialog.dismiss();
    }
}
