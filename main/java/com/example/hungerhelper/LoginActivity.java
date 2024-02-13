package com.example.hungerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        Button register = findViewById(R.id.createAccountButton);
        Button loginButton = findViewById(R.id.button4);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = usernameEditText.getText().toString().trim();
                String Password = passwordEditText.getText().toString().trim();

                if (Email.isEmpty() || Password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check user credentials against the stored information in the database
                authenticateUser(Email, Password);


            }

            private void authenticateUser(String Email, String Password) {

                mDatabase.orderByChild("Email").equalTo(Email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                // Retrieve user information
                                String storedPassword = userSnapshot.child("Password").getValue(String.class);

                                // Check if the entered password matches the stored password
                                if (Password.equals(storedPassword)) {
                                    // Authentication successful
                                    String userId = userSnapshot.getKey();
                                    retrieveUserInfo(userId);
                                    return;
                                }
                            }
                            // Incorrect password
                            Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        } else {
                            // User not found
                            Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle errors during data retrieval
                        Toast.makeText(LoginActivity.this, "Error retrieving user information", Toast.LENGTH_SHORT).show();
                    }

                });
            }

            // Inside your LoginActivity's retrieveUserInfo method after successful login:

            private void retrieveUserInfo(String userId) {
                mDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // User information retrieved successfully
                            String name = dataSnapshot.child("Name").getValue(String.class);
                            String email = dataSnapshot.child("Email").getValue(String.class);
                            String phone = dataSnapshot.child("Phone").getValue(String.class);
                            String location = dataSnapshot.child("Location").getValue(String.class);

                            // Pass the user information to the UserProfileActivity
                            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("Email", email);
                            intent.putExtra("phone", phone);
                            intent.putExtra("location", location);
                            startActivity(intent);
                            finish(); // Optional: Close the LoginActivity if needed

                        } else {
                            // User information not found
                            Toast.makeText(LoginActivity.this, "User information not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle errors during data retrieval
                        Toast.makeText(LoginActivity.this, "Error retrieving user information", Toast.LENGTH_SHORT).show();
                    }

                    private void updateProfileInformation(String userId, String name, String email, String phone, String location) {
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                        // Update or set user information in the Realtime Database
                        userRef.child("Name").setValue(name);
                        userRef.child("Email").setValue(email);
                        userRef.child("Phone").setValue(phone);
                        userRef.child("Location").setValue(location);
                    }

                });
            }
        });
    }
}