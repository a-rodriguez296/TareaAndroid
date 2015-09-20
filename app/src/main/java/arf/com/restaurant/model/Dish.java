package arf.com.restaurant.model;

import java.io.Serializable;


public class Dish implements Serializable {

    private String mName;

    private String mImage;

    private Boolean mContainsAlergens;

    private double mPrice;

    private String mComments;

    public Dish(String name, String image, Boolean containsAlergens, double price, String comments) {
        mName = name;
        mImage = image;
        mContainsAlergens = containsAlergens;
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

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Boolean getContainsAlergens() {
        return mContainsAlergens;
    }

    public void setContainsAlergens(Boolean containsAlergens) {
        mContainsAlergens = containsAlergens;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }
}
