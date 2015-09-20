package arf.com.restaurant.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

/**
 * Created by arodriguez on 9/19/15.
 */
public class TableBroadcastReceiver extends BroadcastReceiver {


    private ArrayAdapter mAdapter;


    public TableBroadcastReceiver(ArrayAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mAdapter.notifyDataSetChanged();
    }
}
