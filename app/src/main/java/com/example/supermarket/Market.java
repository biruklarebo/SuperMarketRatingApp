package com.example.supermarket;

public class Market {
    private int marketID;
    private String marketName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private float liquorRating;
    private float meatRating;
    private float produceRating;
    private float cheeseRating;
    private float checkoutRating;
    private String averageRating;

    public Market(){
        marketID = -1;
        liquorRating = 0;
        meatRating = 0;
        produceRating = 0;
        cheeseRating = 0;
        checkoutRating = 0;
        averageRating = "0";
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

    public float getLiquorRating() {
        return liquorRating;
    }

    public void setLiquorRating(float d) {
        liquorRating = d;
    }

    public float getMeatRating() {
        return meatRating;
    }

    public void setMeatRating(float d) {
        meatRating = d;
    }

   public float getProduceRating() {
        return produceRating;
   }

    public void setProduceRating(float d) {
        produceRating = d;
    }

    public float getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(float d) {
        cheeseRating = d;
    }

    public float getCheckoutRating() {
        return checkoutRating;
    }

    public void setCheckoutRating(float d) {
        checkoutRating = d;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String d) {
        averageRating = d;
    }
}
