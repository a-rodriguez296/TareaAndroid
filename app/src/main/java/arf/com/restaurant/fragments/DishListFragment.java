package arf.com.restaurant.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import arf.com.restaurant.R;
import arf.com.restaurant.activities.DishDetailActivity;
import arf.com.restaurant.broadcastReceivers.TableBroadcastReceiver;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/19/15.
 */
public class DishListFragment extends Fragment {


    private static final String ARG_SELECTED_TABLE = "DishListFragment.ARG_SELECTED_TABLE";


    private Table mParentTable;
    private ArrayList<Dish> mDishes;

    private TableBroadcastReceiver mBroadcastReceiver;


    public static DishListFragment newInstance(Table selectedTable) {

        DishListFragment newInstance = new DishListFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_SELECTED_TABLE, selectedTable);

        newInstance.setArguments(arguments);
        return newInstance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish_list, container, false);

        if (getArguments() != null) {
            mParentTable = (Table) getArguments().getSerializable(ARG_SELECTED_TABLE);

            mDishes = mParentTable.getOrderedDishes();
            final ArrayAdapter<Dish> adapter = new ArrayAdapter<Dish>(getActivity(), android.R.layout.simple_list_item_1, mDishes);
            ListView listView = (ListView) root.findViewById(android.R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {


                    /*if (R.id.dish_detail != null){

                        //Caso split screen

                        FragmentManager fm = getFragmentManager();
                        DishDetailFragment detailFragment = fm.findFragmentById(R.id.dish_detail);
                        detailFragment.goToDish(index);

                    }*/
                    if (true) {
                        Intent dishDetailIntent = new Intent(getActivity(), DishDetailActivity.class);
                        dishDetailIntent.putExtra(DishDetailActivity.EXTRA_DISH, adapter.getItem(index));
                        startActivity(dishDetailIntent);
                    }


                }
            });

            //Creación del Broadcast receiver
            mBroadcastReceiver = new TableBroadcastReceiver(adapter);
            getActivity().
                    registerReceiver(mBroadcastReceiver,
                            new IntentFilter(Restaurant.TABLE_CHANGED_ACTION));

        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mBroadcastReceiver);

    }
}
