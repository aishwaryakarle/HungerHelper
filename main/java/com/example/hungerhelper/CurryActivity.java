package com.example.hungerhelper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CurryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        // Add the DonationFragment to the activity
        FoodListActivity fd =new FoodListActivity();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fd)
                .commit();
    }
}