package arf.com.restaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arf.com.restaurant.R;
import arf.com.restaurant.model.Restaurant;

public class LoadingActivity extends AppCompatActivity implements Restaurant.RestaurantModelListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        //Cargar el modelo
        Restaurant.getInstance(this);


    }


    @Override
    public void dataDidLoad() {


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
