package com.example.supermarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MarketDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mymarket.db";
    private static final int DATABASE_VERSION = 1;
    // database creation

    private static final String CREATE_TABLE_MARKET = "create table market (_id integer primary key autoincrement, "
            + "marketname text not null, streetaddress text, " + "city text, state text, zipcode text, "
            + "liquorrating text, meatrating text, producerating text, " + " cheeserating text, checkoutrating text, averagerating text);";

    public MarketDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MARKET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MarketDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS market");
        onCreate(db);
    }
}
