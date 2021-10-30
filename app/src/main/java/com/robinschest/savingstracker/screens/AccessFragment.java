package com.robinschest.savingstracker.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robinschest.savingstracker.R;

public class AccessFragment extends Fragment implements View.OnClickListener {
    NavDirections action;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.enterBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        action = AccessFragmentDirections.actionAccessFragmentToHomeFragment();
        Navigation.findNavController(v).navigate(action);
    }
}