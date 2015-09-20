package arf.com.restaurant.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Dish;

/**
 * Created by arodriguez on 9/20/15.
 */
public class DishDetailPagerFragment extends Fragment {

    private static final String ARG_DISH = "DishDetailPagerFragment.ARG_DISH";

    private Dish mDish;

    private ViewPager mPager;

    public static DishDetailPagerFragment newInstance(Dish dish) {

        DishDetailPagerFragment fragment = new DishDetailPagerFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_DISH, dish);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mDish = (Dish) getArguments().getSerializable(ARG_DISH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dish_pager, container, false);
        mPager = (ViewPager) root.findViewById(R.id.view_pager);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    protected class CityPagerAdapter extends FragmentPagerAdapter {


        /*private Cities mCities;*/

        public CityPagerAdapter(FragmentManager fm) {
            super(fm);
            /*mCities = Cities.getInstance(getActivity());*/
        }

        @Override
        public Fragment getItem(int i) {
            return DishDetailFragment.newInstance(mDish);
        }

        @Override
        public int getCount() {
            return 1;
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            return "";
        }*/
    }
}
