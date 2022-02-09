package com.example.supermarket;
import java.util.Calendar;

public class Market {
    private int marketID;
    private String marketName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private double liquorRating;
    private double meatRating;
    private double produceRating;
    private double cheeseRating;
    private double checkoutRating;
    private double averageRating;

    public Market(){
        marketID = -1;
        liquorRating = 0.0;
        meatRating = 0.0;
        produceRating = 0.0;
        cheeseRating = 0.0;
        checkoutRating = 0.0;
        averageRating = 0.0;
    }

    public int getMarketID() {
        return marketID;
    }

    public void setMarketID(int i) {
        marketID = i;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String s) {
        marketName = s;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String s) {
        streetAddress = s;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String s) {
        city = s;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String s) {
        zipCode = s;
    }

    public double getLiquorRating() {
        return liquorRating;
    }

    public void setLiquorRating(double d) {
        liquorRating = d;
    }

    public double getMeatRating() {
        return meatRating;
    }

    public void setMeatRating(double d) {
        meatRating = d;
    }

   public double getProduceRating() {
        return produceRating;
   }

    public void setProduceRating(double d) {
        produceRating = d;
    }

    public double getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(double d) {
        cheeseRating = d;
    }

    public double getCheckoutRating() {
        return checkoutRating;
    }

    public void setCheckoutRating(double d) {
        checkoutRating = d;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double d) {
        averageRating = d;
    }
}
