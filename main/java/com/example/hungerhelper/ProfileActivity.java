package com.example.hungerhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView username;
    private ImageButton closeProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);





        profileImage = findViewById(R.id.profileImageSide);
        closeProfileButton = findViewById(R.id.closeProfileButton);

        // Set profile details
        // You can retrieve user information and set it in the views here

        closeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the profile activity
            }
        });
    }
}

