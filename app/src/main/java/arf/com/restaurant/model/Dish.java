package arf.com.restaurant.model;

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

    public Dish() {
        super();
    }
}
