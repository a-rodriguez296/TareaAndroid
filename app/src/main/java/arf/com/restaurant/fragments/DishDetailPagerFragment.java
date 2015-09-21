package arf.com.restaurant.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;

/**
 * Created by arodriguez on 9/20/15.
 */
public class DishDetailPagerFragment extends Fragment {

    private static final String ARG_DISH_INDEX = "DishDetailPagerFragment.ARG_DISH_INDEX";

    private static final String ARG_PARENT_TABLE_INDEX = "DishDetailPagerFragment.ARG_PARENT_TABLE_INDEX";

    private int mDishIndex;

    private Table mParentTable;

    private ViewPager mPager;

    public static DishDetailPagerFragment newInstance(int dishIndex, int parentTableIndex) {

        DishDetailPagerFragment fragment = new DishDetailPagerFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ARG_DISH_INDEX, dishIndex);
        arguments.putInt(ARG_PARENT_TABLE_INDEX, parentTableIndex);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {

            int tableIndex = getArguments().getInt(ARG_PARENT_TABLE_INDEX);
            mParentTable = Restaurant.getInstance(getActivity()).getTables().get(tableIndex);
            mDishIndex = getArguments().getInt(ARG_DISH_INDEX);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dish_pager, container, false);
        mPager = (ViewPager) root.findViewById(R.id.view_pager);
        mPager.setAdapter(new DishPagerAdapter(getFragmentManager()));
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPager.setCurrentItem(mDishIndex);


        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.dish_pager, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.previous) {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            return true;
        } else if (item.getItemId() == R.id.next) {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mPager != null) {

            MenuItem previous = menu.findItem(R.id.previous);
            MenuItem next = menu.findItem(R.id.next);

            previous.setEnabled(mPager.getCurrentItem() > 0);
            next.setEnabled(mPager.getCurrentItem() < mParentTable.getOrderedDishes().size() - 1);
        }
    }

    protected class DishPagerAdapter extends FragmentPagerAdapter {


        public DishPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return DishDetailFragment.newInstance(mParentTable.getOrderedDishes().get(i));
        }

        @Override
        public int getCount() {
            return mParentTable.getOrderedDishes().size();
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            return "";
        }*/
    }
}
