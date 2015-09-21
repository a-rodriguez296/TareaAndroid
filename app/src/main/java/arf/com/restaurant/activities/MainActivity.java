package arf.com.restaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Restaurant;
import arf.com.restaurant.model.Table;


public class MainActivity extends AppCompatActivity implements Restaurant.RestaurantModelListener {


    private Restaurant mRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Inicializar el modelo
        mRestaurant = Restaurant.getInstance(this);


        ListView listView = (ListView) findViewById(android.R.id.list);


        ArrayAdapter<Table> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mRestaurant.getTables());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                Table selectedTable = mRestaurant.getTables().get(index);

                Intent dishesIntent = new Intent(MainActivity.this, DishListActivity.class);
                dishesIntent.putExtra(DishListActivity.TABLE_INDEX_ARGUMENT, index);
                startActivity(dishesIntent);
            }
        });
    }


    @Override
    public void dataDidLoad() {

        String a = "Alejandro";
    }
}
