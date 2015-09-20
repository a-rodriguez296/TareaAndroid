package arf.com.restaurant.model;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;


public class Dish implements Serializable {

    private String mName;

    private String mImage;

    private ArrayList<String> mAlergens;

    private double mPrice;

    private String mComments;

    public Dish(String name, String image, ArrayList<String> alergens, double price, String comments) {
        mName = name;
        mImage = image;
        mAlergens = alergens;
        mPrice = price;
        mComments = comments;
    }

    public static Dish copyDish(Dish dish, String comments) {
        return new Dish(dish.getName(), dish.getImage(), dish.getAlergens(), dish.getPrice(), comments);
    }


    @Override
    public String toString() {
        return mName;
    }

    public String getName() {
        return mName;
    }


    public String getImage() {
        return mImage;
    }

    public double getPrice() {
        return mPrice;
    }


    public ArrayList<String> getAlergens() {
        return mAlergens;
    }

    public String getComments() {
        return mComments;
    }

    public String getNormalizedAlergens() {
        return TextUtils.join(", ", getAlergens());
    }


    public Dish() {
        super();
    }
}
