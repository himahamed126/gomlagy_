package com.onoo.gomlgy.Utils;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class HelperMethod {

    public static void replace(Fragment fragment, FragmentManager supportFragmentManager, int id, boolean addToBackStack) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void add(Fragment fragment, FragmentManager supportFragmentManager, int id, boolean addToBackStack) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(id, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void setAnimation(View view, int position, int type, int lastPosition, boolean on_attach) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, type);
            lastPosition = position;
        }
    }
}

