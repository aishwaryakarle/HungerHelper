package com.example.hungerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ImageView profileImage = findViewById(R.id.profileImageSide);
        ImageButton closeProfileButton = findViewById(R.id.closeProfileButton);
        Button donor=findViewById(R.id.donor);
        Button receiver=findViewById(R.id.receiver);
        Button logout=findViewById(R.id.logout);

        // Retrieve user information from the Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("Email");
        String phone = getIntent().getStringExtra("phone");
        String location = getIntent().getStringExtra("location");

        // Display user information in TextViews (you should have TextViews in user_profile.xml)
        TextView Myname=findViewById(R.id.Myname);
        TextView myemail=findViewById(R.id.myemail);
        TextView phoneTextView=findViewById(R.id.phoneTextView);
        TextView locationTextView=findViewById(R.id.locationTextView);

        Myname.setText( name);
        myemail.setText(email);
        phoneTextView.setText(phone);
        locationTextView.setText(location);

        // Set profile details
        // You can retrieve user information and set it in the views here

        closeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close the profile activity
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserProfileActivity.this,MainActivity.class));
            }
        });

        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, CurryActivity.class);
                startActivity(intent);

            }
        });
    }
}
