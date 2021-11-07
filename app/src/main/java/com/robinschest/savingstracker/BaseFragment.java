package com.robinschest.savingstracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    protected int bottomNavViewVisibility = View.VISIBLE;

    public void setBottomNavViewVisibility(int bottomNavViewVisibility) {
        this.bottomNavViewVisibility = bottomNavViewVisibility;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (((MainActivity) getActivity()) != null) {
            ((MainActivity) getActivity()).setBottomNavigationVisibility(bottomNavViewVisibility);
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (((MainActivity) getActivity()) != null) {
            ((MainActivity) getActivity()).setBottomNavigationVisibility(bottomNavViewVisibility);
        }
    }
}