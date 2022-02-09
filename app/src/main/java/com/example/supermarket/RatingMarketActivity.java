package com.example.supermarket;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class RatingMarketActivity extends AppCompatActivity {
    private Market currentMarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_bar);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            initMarket(extras.getInt("marketid"));
        }
        else{
            currentMarket = new Market();
        }
        final RatingBar rbLiquor = findViewById(R.id.ratingLiquor);
        rbLiquor.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
            }
        });
        final RatingBar rbMeat = findViewById(R.id.ratingMeat);
        rbMeat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
            }
        });
        final RatingBar rbProduce = findViewById(R.id.ratingProduce);
        rbProduce.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
            }
        });
        final RatingBar rbCheese = findViewById(R.id.ratingCheese);
        rbCheese.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
            }
        });
        final RatingBar rbCheckout = findViewById(R.id.ratingCheckout);
        rbCheckout.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingBar.setRating(v);
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMarket.setLiquorRating(rbLiquor.getRating());
                currentMarket.setMeatRating(rbMeat.getRating());
                currentMarket.setProduceRating(rbProduce.getRating());
                currentMarket.setCheeseRating(rbCheese.getRating());
                currentMarket.setCheckoutRating(rbCheckout.getRating());
                final float r = (currentMarket.getLiquorRating() + currentMarket.getMeatRating() + currentMarket.getProduceRating() +
                        currentMarket.getCheeseRating() + currentMarket.getCheckoutRating()) / 5;
                String s = r + "";
                currentMarket.setAverageRating(s);
                String avg = r + "";
                TextView averageRating = findViewById(R.id.textAvgRating);
                averageRating.setText(avg);
                MarketDataSource ds = new MarketDataSource(RatingMarketActivity.this);
                boolean wasSuccessful;
                try {
                    ds.open();
                    int newId = ds.getLastMarketID();
                    currentMarket.setMarketID(newId);
                    wasSuccessful = ds.addRatings(currentMarket);
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                if (wasSuccessful){
                    Intent intent = new Intent(RatingMarketActivity.this, MarketListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
            });
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RatingMarketActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        }
    private void initMarket(int id) {
        MarketDataSource ds = new MarketDataSource(RatingMarketActivity.this);
        try {
            ds.open();
            currentMarket = ds.getSpecificMarket(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Market Failed", Toast.LENGTH_LONG).show();
        }
        RatingBar liqRating = findViewById(R.id.ratingLiquor);
        RatingBar meatRating = findViewById(R.id.ratingMeat);
        RatingBar produceRating = findViewById(R.id.ratingProduce);
        RatingBar cheeseRating = findViewById(R.id.ratingCheese);
        RatingBar checkoutRating = findViewById(R.id.ratingCheckout);
        TextView avgRating = findViewById(R.id.textAvgRating);

        liqRating.setRating(currentMarket.getLiquorRating());
        meatRating.setRating(currentMarket.getMeatRating());
        produceRating.setRating(currentMarket.getProduceRating());
        cheeseRating.setRating(currentMarket.getCheeseRating());
        checkoutRating.setRating(currentMarket.getCheckoutRating());
        String average = currentMarket.getAverageRating() + "";
        avgRating.setText(average);
    }
}