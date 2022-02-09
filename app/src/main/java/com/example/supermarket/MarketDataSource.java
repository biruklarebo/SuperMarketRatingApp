package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

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
}
