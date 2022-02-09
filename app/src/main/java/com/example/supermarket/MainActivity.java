package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity  {
    private Market currentMarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRateButton();
        currentMarket = new Market();
        hideKeyboard();
        initTextChangedEvents();
        initSaveButton();
    }
    private void initRateButton() {
        Button changeRating = findViewById(R.id.ratingButton);
        changeRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RatingMarketActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initTextChangedEvents() {
        final EditText etMarketName = findViewById(R.id.editMarket);
        etMarketName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMarket.setMarketName(etMarketName.getText().toString());
            }
        });
        final EditText etStreetAddress = findViewById(R.id.editAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setStreetAddress(etStreetAddress.getText().toString());
            }
        });

        final EditText etCity = findViewById(R.id.editCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setCity(etCity.getText().toString());
            }
        });
        final EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setState(etState.getText().toString());
            }
        });
        final EditText etZipCode = findViewById(R.id.editZipcode);
        etZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setZipCode(etZipCode.getText().toString());
            }
        });
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editMarket);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText editCity = findViewById(R.id.editCity);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);
        EditText editState = findViewById(R.id.editState);
        imm.hideSoftInputFromWindow(editState.getWindowToken(), 0);
        EditText editZipCode = findViewById(R.id.editZipcode);
        imm.hideSoftInputFromWindow(editZipCode.getWindowToken(), 0);
    }
    private void initSaveButton() {
        Button save = findViewById(R.id.saveMarketButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                MarketDataSource ds = new MarketDataSource(MainActivity.this);
                boolean wasSuccessful;
                try {
                    ds.open();
                    if (currentMarket.getMarketID() == -1) {
                        wasSuccessful = ds.insertMarket(currentMarket);
                        if (wasSuccessful) {
                            int newId = ds.getLastMarketID();
                            currentMarket.setMarketID(newId);
                        }
                    }
                    else {
                        wasSuccessful = ds.updateMarket(currentMarket);
                        ds.close();
                    }
                } catch (Exception e) {
                    wasSuccessful = false;
                }
            }
        });
    }
    /* private void initMarket(int id) {
        MarketDataSource ds = new MarketDataSource(MainActivity.this);
        try {
            ds.open();
            currentMarket = ds.getSpecificMarket(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Market Failed", Toast.LENGTH_LONG).show();
        }
        EditText editName = findViewById(R.id.editMarket);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode = findViewById(R.id.editZipcode);

        editName.setText(currentMarket.getMarketName());
        editAddress.setText(currentMarket.getStreetAddress());
        editCity.setText(currentMarket.getCity());
        editState.setText(currentMarket.getState());
        editZipCode.setText(currentMarket.getZipCode());
    }
    /
     */
}
