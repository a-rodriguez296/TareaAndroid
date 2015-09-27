package arf.com.restaurant.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Dish;

/**
 * Created by arodriguez on 9/20/15.
 */
public class DishDetailFragment extends Fragment {


    private static final String ARG_DISH = "DishDetailFragment.ARG_DISH";

    private Dish mDish;

    private ImageView mDishImage;
    private TextView mTextDishName;
    private TextView mTextAlergens;
    private TextView mTextComments;
    private TextView mTextPrice;


    public static DishDetailFragment newInstance(Dish dish) {

        DishDetailFragment fragment = new DishDetailFragment();


        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_DISH, dish);

        fragment.setArguments(arguments);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {

            mDish = (Dish) getArguments().getSerializable(ARG_DISH);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish_detail, container, false);


        //Inicialización variables gráficas
        mDishImage = (ImageView) root.findViewById(R.id.img_dish_image);
        mTextDishName = (TextView) root.findViewById(R.id.txt_dish_name);
        mTextAlergens = (TextView) root.findViewById(R.id.txt_alergens);
        mTextComments = (TextView) root.findViewById(R.id.txt_comments);
        mTextPrice = (TextView) root.findViewById(R.id.txt_price);


        if (mDish != null) {

            int imageResource = getResources().getIdentifier(mDish.getImage(), "drawable", getActivity().getPackageName());
            Drawable image = getResources().getDrawable(imageResource);

            mDishImage.setImageDrawable(image);
            mTextDishName.setText(mDish.getName());
            mTextAlergens.setText(mDish.getNormalizedAlergens());
            mTextComments.setText(mDish.getComments());
            mTextPrice.setText("" + mDish.getPrice() + " €");
        }


        return root;
    }
}
