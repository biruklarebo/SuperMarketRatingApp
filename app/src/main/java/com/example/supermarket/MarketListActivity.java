package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarketListActivity extends AppCompatActivity {
    ArrayList<Market> markets;
    MarketAdapter marketAdapter;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)
                    view.getTag();
            int position = viewHolder.getAdapterPosition();
            int marketId = markets.get(position).getMarketID();
            Intent intent = new Intent(MarketListActivity.this, RatingMarketActivity.class);
            intent.putExtra("marketid", marketId);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_list);
        initAddMarketButton();
        initDeleteSwitch();
    }
    @Override
    public void onResume() {
        super.onResume();
        MarketDataSource ds = new MarketDataSource(this);

        try {
            ds.open();
            markets = ds.getMarkets();
            ds.close();
            if (markets.size() > 0) {
                RecyclerView marketList = findViewById(R.id.rvMarkets);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                marketList.setLayoutManager(layoutManager);
                marketAdapter = new MarketAdapter(markets, this);
                marketAdapter.setOnItemClickListener(onItemClickListener);
                marketList.setAdapter(marketAdapter);
            }
            else {
                Intent intent  = new Intent(MarketListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }


    private void initAddMarketButton() {
        Button newMarket = findViewById(R.id.buttonAddMarket);
        newMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initDeleteSwitch() {
        Switch s = findViewById(R.id.switchDelete);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Boolean status = compoundButton.isChecked();
                marketAdapter.setDelete(status);
                marketAdapter.notifyDataSetChanged();
            }
        });
    }
}
