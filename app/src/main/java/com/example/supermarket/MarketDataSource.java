package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MarketDataSource {
    private SQLiteDatabase database;
    private MarketDBHelper dbHelper;

    public MarketDataSource(Context context) {
        dbHelper = new MarketDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public boolean insertMarket(Market m) {
        boolean didSucced = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("marketname", m.getMarketName());
            initialValues.put("streetaddress", m.getStreetAddress());
            initialValues.put("city", m.getCity());
            initialValues.put("state", m.getState());
            initialValues.put("liquorrating", m.getLiquorRating());
            initialValues.put("meatrating", m.getMeatRating());
            initialValues.put("producerating", m.getProduceRating());
            initialValues.put("cheeserating", m.getCheeseRating());
            initialValues.put("checkoutrating", m.getCheckoutRating());
            initialValues.put("averagerating", m.getAverageRating());

            didSucced = database.insert("market", null, initialValues) > 0;
        }
        catch (Exception e) {
            //do nothing -will return false if there is an exception
        }
    return didSucced;
    }

    public boolean updateMarket(Market m) {
        boolean didSucced = false;
        try {
            long rowId = (long) m.getMarketID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("marketname", m.getMarketName());
            updateValues.put("streetaddress", m.getStreetAddress());
            updateValues.put("city", m.getCity());
            updateValues.put("state", m.getState());
            updateValues.put("liquorrating", m.getLiquorRating());
            updateValues.put("meatrating", m.getMeatRating());
            updateValues.put("producerating", m.getProduceRating());
            updateValues.put("cheeserating", m.getCheeseRating());
            updateValues.put("checkoutrating", m.getCheckoutRating());
            updateValues.put("averagerating", m.getAverageRating());

            didSucced = database.update("market",  updateValues, "_id=" + rowId , null) > 0;
        }
        catch (Exception e) {
            //do nothing -will return false if there is an exception
        }
        return didSucced;
    }
    public boolean addRatings(Market m) {
        boolean didSucced = false;
        try {
            long rowId = (long) m.getMarketID();
            ContentValues updateValues = new ContentValues();
            updateValues.put("liquorrating", m.getLiquorRating());
            updateValues.put("meatrating", m.getMeatRating());
            updateValues.put("producerating", m.getProduceRating());
            updateValues.put("cheeserating", m.getCheeseRating());
            updateValues.put("checkoutrating", m.getCheckoutRating());
            updateValues.put("averagerating", m.getAverageRating());

            didSucced = database.update("market",  updateValues, "_id=" + rowId , null) > 0;
        }
        catch (Exception e) {
            //do nothing -will return false if there is an exception
        }
        return didSucced;
    }
    public int getLastMarketID() {
        int lastID;
        try{
            String query = "Select MAX(_id) from market";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastID = -1;
        }
        return lastID;
    }

    public ArrayList<Market> getMarkets() {
        ArrayList<Market> markets = new ArrayList<Market>();
        try {
            String query = "SELECT * FROM market";
            Cursor cursor = database.rawQuery(query, null);

            Market newMarket;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newMarket = new Market();
                newMarket.setMarketID(cursor.getInt(0));
                newMarket.setMarketName(cursor.getString(1));
                newMarket.setStreetAddress(cursor.getString(2));
                newMarket.setCity(cursor.getString(3));
                newMarket.setState(cursor.getString(4));
                newMarket.setZipCode(cursor.getString(5));
                newMarket.setLiquorRating(cursor.getFloat(6));
                newMarket.setMeatRating(cursor.getFloat(7));
                newMarket.setProduceRating(cursor.getFloat(8));
                newMarket.setCheeseRating(cursor.getFloat(9));
                newMarket.setCheckoutRating(cursor.getFloat(10));
                newMarket.setAverageRating(cursor.getString(11));
                markets.add(newMarket);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e){
            markets = new ArrayList<Market>();
        }
        return markets;
    }

    public Market getSpecificMarket(int marketId){
        Market market = new Market();
        String query = "SELECT * FROM market WHERE _id =" + marketId;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            market.setMarketID(cursor.getInt(0));
            market.setMarketName(cursor.getString(1));
            market.setStreetAddress(cursor.getString(2));
            market.setCity(cursor.getString(3));
            market.setState(cursor.getString(4));
            market.setZipCode(cursor.getString(5));
            market.setLiquorRating(cursor.getFloat(6));
            market.setMeatRating(cursor.getFloat(7));
            market.setProduceRating(cursor.getFloat(8));
            market.setCheeseRating(cursor.getFloat(9));
            market.setCheckoutRating(cursor.getFloat(10));
            market.setAverageRating(cursor.getString(11));

            cursor.close();
        }
        return market;
    }
    public boolean deleteMarket (int marketId) {
        boolean didDelete = false;
        try{
            didDelete = database.delete("market", "_id=" + marketId,null) >0;
        }
        catch (Exception e) {
            //Do nothing -return value is already set to false
        }
        return didDelete;
    }
}
