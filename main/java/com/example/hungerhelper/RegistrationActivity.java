package com.example.hungerhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        Button register = findViewById(R.id.register);
        EditText username = findViewById(R.id.username);
        EditText email=findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText phone = findViewById(R.id.phone);
        EditText location = findViewById(R.id.location);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = username.getText().toString();
                final String email1 = email.getText().toString();
                final String pass = password.getText().toString();
                final String contact = phone.getText().toString();
                final String loc = location.getText().toString();

                if (name.isEmpty() || email1.isEmpty()||pass.isEmpty() || contact.isEmpty() || loc.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill all field", Toast.LENGTH_SHORT).show();
                } else {

                    addInputToDatabase(name,email1,pass,contact,loc);
                }

            }
        });
    }

    private void addInputToDatabase(String name,String email1, String pass, String contact, String loc) {
        HashMap<String, Object> inuputHashMap = new HashMap<>();
        inuputHashMap.put("Name", name);
        inuputHashMap.put("Email", email1);
        inuputHashMap.put("Password", pass);
        inuputHashMap.put("Phone", contact);
        inuputHashMap.put("Location", loc);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myUserRef = database.getReference("Users");
        String Key = myUserRef.push().getKey();
        inuputHashMap.put("Key", Key);
        myUserRef.child(Key).setValue(inuputHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                Toast.makeText(RegistrationActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



