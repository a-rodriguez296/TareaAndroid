package arf.com.restaurant.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Dish;
import arf.com.restaurant.model.Restaurant;

/**
 * Created by arodriguez on 9/19/15.
 */
public class AddDishDialogFragment extends DialogFragment {

    public static final String ADDED_DISH_BROADCAST_INDENTIFIER = "AddDishDialogFragment.ADDED_DISH_BROADCAST_INDENTIFIER";

    public static final String DISH = "AddDishDialogFragment.DISH";

    public static final String COMMENTS = "AddDishDialogFragment.COMMENTS";

    private DishListener mDishListener;


    private Spinner mSpinner;
    private Button mButton;
    private ListView mListView;
    private EditText mComments;
    private TextView mPriceText;

    private ArrayList<Dish> mDishes;

    private Dish mCurrentDish;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_add_dish, null);
        dialog.setView(dialogView);


        //Spinner de platos
        mSpinner = (Spinner) dialogView.findViewById(R.id.spinner);
        mDishes = Restaurant.getInstance(getActivity()).getDishes();
        mCurrentDish = mDishes.get(0);

        ArrayAdapter<Dish> spinnerAdapter = new ArrayAdapter<Dish>(getActivity(),
                android.R.layout.simple_spinner_item,
                mDishes);

        mSpinner.setAdapter(spinnerAdapter);
       /* mSpinner.setSelection(0);*/
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {

                //Actualización lista alergenos
                mCurrentDish = mDishes.get(index);
                ArrayAdapter<String> listAdapter = (ArrayAdapter<String>) mListView.getAdapter();
                listAdapter.clear();
                listAdapter.addAll(mCurrentDish.getAlergens());
                listAdapter.notifyDataSetChanged();


                //Acuatlización Precio
                mPriceText.setText("" + mCurrentDish.getPrice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Lista de Alergenos
        mListView = (ListView) dialogView.findViewById(R.id.alergens_list);


        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        mListView.setAdapter(listAdapter);


        //Precio
        mPriceText = (TextView) dialogView.findViewById(R.id.price_textview);
        mPriceText.setText("" + mCurrentDish.getPrice());


        //Comentarios
        mComments = (EditText) dialogView.findViewById(R.id.comments);

        mButton = (Button) dialogView.findViewById(R.id.add_dish_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDishListener != null) {
                    if (mComments.getText() != null) {
                        mDishListener.dishAdded(mCurrentDish, mComments.getText().toString());
                    } else {
                        mDishListener.dishAdded(mCurrentDish, "");
                    }
                }


            }
        });
        return dialog.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDishListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDishListener = (DishListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mDishListener = (DishListener) activity;
    }

    public interface DishListener {

        void dishAdded(Dish dish, String comments);
    }


}
