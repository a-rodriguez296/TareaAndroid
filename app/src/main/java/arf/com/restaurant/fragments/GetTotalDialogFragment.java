package arf.com.restaurant.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import arf.com.restaurant.R;

/**
 * Created by arodriguez on 9/27/15.
 */
public class GetTotalDialogFragment extends DialogFragment {

    private static final String ARG_TOTAL = "GetTotalDialogFragment.ARG_TOTAL";


    private TextView mTxtGetTotal;
    private String mTotalValue;


    public static GetTotalDialogFragment newInstance(String total) {

        GetTotalDialogFragment fragment = new GetTotalDialogFragment();

        Bundle arguments = new Bundle();
        arguments.putString(ARG_TOTAL, total);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mTotalValue = getArguments().getString(ARG_TOTAL);
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_get_total, null);
        dialog.setView(dialogView);

        mTxtGetTotal = (TextView) dialogView.findViewById(R.id.txt_total);
        mTxtGetTotal.setText(mTotalValue);


        return dialog.create();
    }
}
