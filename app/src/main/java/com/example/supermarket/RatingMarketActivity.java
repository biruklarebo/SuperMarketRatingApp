package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class RatingMarketActivity extends AppCompatActivity {
    private Market currentMarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentMarket = new Market();
        setContentView(R.layout.rating_bar);
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
                final double r = (currentMarket.getLiquorRating() + currentMarket.getMeatRating() + currentMarket.getProduceRating() +
                        currentMarket.getCheeseRating() + currentMarket.getCheckoutRating()) / 5.0;
                currentMarket.setAverageRating(r);
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
    }