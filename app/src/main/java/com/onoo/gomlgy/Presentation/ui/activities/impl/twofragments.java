package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.onoo.gomlgy.R;


public class twofragments extends Fragment {
    View v;

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//
//    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment childFragment = new CategoryRecycler();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.category, childFragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_twofragments, null);
//        insertNestedFragment();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}