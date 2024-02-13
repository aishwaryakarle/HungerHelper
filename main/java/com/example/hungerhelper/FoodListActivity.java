package com.example.hungerhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FoodListActivity extends Fragment {
    public void FoodListActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_list, container, false);

        Button ricebtn=view.findViewById(R.id.ricebtn);
        Button currybtn=view.findViewById(R.id.currybtn);
        Button rotibtn=view.findViewById(R.id.rotibtn);
        Button fastbtn=view.findViewById(R.id.fastbtn);
        Button chinesebtn=view.findViewById(R.id.chinesebtn);


        ricebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), DonationActivity.class);
                startActivity(intent);

            }
        });

        currybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(),DonationActivity.class);
                startActivity(intent);
            }
        });

        rotibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), DonationActivity.class);
                startActivity(intent);
            }
        });

        fastbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), DonationActivity.class);
                startActivity(intent);
            }
        });

        chinesebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), DonationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
