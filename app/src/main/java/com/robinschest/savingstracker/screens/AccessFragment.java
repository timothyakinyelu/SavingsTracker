package com.robinschest.savingstracker.screens;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.robinschest.savingstracker.MainActivity;
import com.robinschest.savingstracker.R;

public class AccessFragment extends Fragment implements View.OnClickListener {
    private CheckBox pinCheckBox;
    private EditText editUsername, editPin;
    private TextView warnUsername, warnPin;
    private ConstraintLayout parentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initElements(view);
        onCheckBoxClick();
    }

    /**
     * Set edit pin view visibility when checkbox is clicked.
     * Overrides onCheckedChange method
     * */
    private void onCheckBoxClick() {
        pinCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /** The CompoundButton.OnCheckedChangeListener class method
             *  can be replaced with a lambda function, but I prefer to know what class
             *  and methods I borrow from android library
             **/
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editPin.setVisibility(View.VISIBLE);
                } else {
                    editPin.setVisibility(View.GONE);
                    warnPin.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * Initialize all layout elements, and set click listeners
     **/
    private void initElements(View view) {
        pinCheckBox = view.findViewById(R.id.setPin);
        editUsername = view.findViewById(R.id.editUsername);
        editPin = view.findViewById(R.id.editPin);
        warnUsername = view.findViewById(R.id.warnUsername);
        warnPin = view.findViewById(R.id.warnPin);
        AppCompatButton enterBtn = view.findViewById(R.id.enterBtn);
        parentLayout = view.findViewById(R.id.accessLayout);

        editUsername.requestFocus();
        enterBtn.setOnClickListener(this);
        parentLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.enterBtn) {
            authenticate(v);
        } else if(id == R.id.accessLayout) {
             MainActivity.hideSoftKeyBoard(parentLayout, getActivity());
        }
    }

    private void authenticate(View view) {
        if(validateInput()) {
            warnUsername.setVisibility(View.GONE);
            warnPin.setVisibility(View.GONE);

            NavDirections action = AccessFragmentDirections.actionAccessFragmentToHomeFragment();
            Navigation.findNavController(view).navigate(action);
        }
    }

    private boolean validateInput() {
        if(editUsername.getText().toString().trim().equals("")) {
            warnUsername.setVisibility(View.VISIBLE);
            warnUsername.setText("Username is required!");
            return false;
        }

        if(pinCheckBox.isChecked()) {
            if(editPin.getText().toString().trim().equals("")) {
                warnPin.setVisibility(View.VISIBLE);
                warnPin.setText("Enter a 4-digit PIN!");
                return false;
            }
        }

        return true;
    }
}