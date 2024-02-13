package com.example.hungerhelper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class DonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        // Add the DonationFragment to the activity
        DonationFragment donationFragment = new DonationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, donationFragment)
                .commit();






    }
}

