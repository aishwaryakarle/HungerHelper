package com.example.hungerhelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DonationFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.donation_page, container, false);

        EditText DNameEditText=view.findViewById(R.id.DNameEditText);
        EditText D_phone=view.findViewById(R.id.D_phone);
        EditText foodNameEditText =view.findViewById(R.id.foodNameEditText);
        EditText quantityEditText = view.findViewById(R.id.quantityEditText);
        EditText HourseEditText = view.findViewById(R.id.HourseEditText);
        Button donateFoodButton = view.findViewById(R.id.donateFoodButton);

        donateFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's input from the EditText fields
                String d_name=DNameEditText.getText().toString();
                String d_phone=D_phone.getText().toString();
                String foodName = foodNameEditText.getText().toString();
                String quantity = quantityEditText.getText().toString();
                String FoodHourse = HourseEditText.getText().toString();

                if (d_name.isEmpty()||d_phone.isEmpty()||foodName.isEmpty() || quantity.isEmpty() || FoodHourse.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill all field", Toast.LENGTH_SHORT).show();
                } else {

                    addInputToDatabase(d_name,d_phone,foodName,quantity,FoodHourse);
                }
            }
        });

        return view;
    }

    private void addInputToDatabase(String d_name,String d_phone,String foodname, String quantity, String hours) {
        HashMap<String, Object> inuputHashMap = new HashMap<>();
        inuputHashMap.put("Donor Name",d_name);
        inuputHashMap.put("Donor Phone",d_phone);
        inuputHashMap.put("Food Name",foodname);
        inuputHashMap.put("Quantity",quantity);
        inuputHashMap.put("Cooking Hours",hours);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myUserRef = database.getReference("Donar_Food_Details");
        String Key = myUserRef.push().getKey();
        inuputHashMap.put("Key", Key);
        myUserRef.child(Key).setValue(inuputHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                Toast.makeText(getContext(), "Successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
