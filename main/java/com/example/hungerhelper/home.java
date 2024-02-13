package com.example.hungerhelper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class home extends Fragment {

    GridView grid;
    List databaseList;
    List list_1;
    DatabaseReference reference;
    Integer[] icons ={
            R.drawable.roti,
            R.drawable.foodvarity,
            R.drawable.fullplate,
            R.drawable.hunger3,
            R.drawable.roti,
            R.drawable.foodvarity,
            R.drawable.fullplate,
            R.drawable.hunger3,
            R.drawable.roti,
            R.drawable.foodvarity,
            R.drawable.fullplate,
            R.drawable.hunger3,
            R.drawable.roti
    };
    String[] name;//={"chapati","roti","fullplate","rice","dal","fullplate","rice","dal","bhaji","rice","dal","fullplate","rice","dal","bhaji"};

    public home() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.home, container, false);
        grid=(GridView)view.findViewById(R.id.main);
        list_1 = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Donor_Food_Details");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Clear the list before populating it
                list_1.clear();

                for (DataSnapshot extract : snapshot.getChildren()) {
                    // Use the key as the ID or some unique identifier
                    String data1 = extract.getKey();
                    pass(data1);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle errors
            }
        });


      /*  Grid_food_adapter arrayAdapter=new Grid_food_adapter(getContext(),icons,name);
        grid.setAdapter(arrayAdapter);*/

        return view;
    }
    private void pass(String data1) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Donor_Food_Details").child(data1);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User information retrieved successfully
                    String name = dataSnapshot.child("Food Name").getValue(String.class);
                    list_1.toArray();
                    list_1.add(name);

                    Grid_food_adapter arrayAdapter=new Grid_food_adapter(getContext(),icons,list_1);
                    grid.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle errors
            }
        });
    }
}
