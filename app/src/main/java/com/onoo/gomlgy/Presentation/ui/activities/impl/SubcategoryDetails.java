package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onoo.gomlgy.R;
public class SubcategoryDetails extends Fragment {
public String subcategory="asd";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_subcategory_details, container, false);


         return v;
    }
}