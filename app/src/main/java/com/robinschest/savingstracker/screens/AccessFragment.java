package com.robinschest.savingstracker.screens;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

public class AccessFragment extends Fragment {
    private EditText editUsername, editPin;
    private TextView warnUsername, warnPin;
    private CheckBox pinCheckBox;
    private ConstraintLayout parentLayout;
    private AppCompatButton enterBtn;

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
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
            }
        });

        parentLayout.setOnClickListener(v -> hideKeyboard(getActivity()));
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(R.id.parentLayout);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void authenticate() {
        if(validateData()) {
            checkCredentials();
        }
    }

    private void checkCredentials() {
        warnUsername.setVisibility(View.GONE);
        warnPin.setVisibility(View.GONE);
    }

    private boolean validateData() {
        if(editUsername.getText().toString().isEmpty()) {
            warnUsername.setVisibility(View.VISIBLE);
            warnUsername.setText("Username is required");
            return false;
        }

        if(pinCheckBox.isChecked() && editPin.getText().toString().isEmpty()) {
            warnPin.setVisibility(View.VISIBLE);
            warnPin.setText("Enter a 4-DIGIT PIN");
            return false;
        }

        return true;
    }

    private void onCheckBoxClick() {
        pinCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    editPin.setVisibility(View.VISIBLE);
                } else {
                    editPin.setVisibility(View.GONE);
                    warnPin.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initElements(View view) {
        editUsername = view.findViewById(R.id.editUsername);
        editPin = view.findViewById(R.id.editPin);
        parentLayout = view.findViewById(R.id.parentLayout);
        enterBtn = view.findViewById(R.id.enterBtn);
        warnUsername = view.findViewById(R.id.warnUsername);
        warnPin = view.findViewById(R.id.warnPin);
        pinCheckBox = view.findViewById(R.id.setPin);

        editUsername.requestFocus();
    }
}