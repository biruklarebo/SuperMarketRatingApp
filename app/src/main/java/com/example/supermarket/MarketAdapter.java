package com.example.supermarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter {
    ArrayList<Market> marketData;
    private boolean isDeleting;
    private Context parentContext;
    private View.OnClickListener mOnItemClickListener;

    public class MarketViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMarket;
        public TextView textAvgRating;
        public Button deleteButton;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMarket = itemView.findViewById(R.id.textMarketName);
            textAvgRating = itemView.findViewById(R.id.textAverageRating);
            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getMarketTextView() {
            return textViewMarket;
        }

        public TextView getAvgRatingTextView() {
            return textAvgRating;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    public MarketAdapter(ArrayList<Market> arrayList, Context context) {
        marketData = arrayList;
        parentContext = context;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_list, parent, false);
        return new MarketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MarketViewHolder mvh = (MarketViewHolder) holder;
        mvh.getMarketTextView().setText(marketData.get(position).getMarketName());
        mvh.getAvgRatingTextView().setText(marketData.get(position).getAverageRating());
        if (isDeleting) {
            mvh.getDeleteButton().setVisibility(View.VISIBLE);
            mvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
        else {
            mvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return marketData.size();
    }

    public void setDelete(boolean b) {
        isDeleting = b;
    }

    private void deleteItem(int position) {
        Market market = marketData.get(position);
        MarketDataSource ds = new MarketDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteMarket(market.getMarketID());
            ds.close();
            if (didDelete) {
                marketData.remove(position);
                notifyDataSetChanged();
            } else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(parentContext, "Delete Failed", Toast.LENGTH_LONG).show();
        }
    }
}
