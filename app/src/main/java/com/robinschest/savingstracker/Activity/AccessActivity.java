package com.robinschest.savingstracker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.robinschest.savingstracker.R;


public class AccessActivity extends AppCompatActivity {
    private EditText editUsername, editPin;
    private TextView warnUsername, warnPin;
    private CheckBox pinCheckBox;
    private ConstraintLayout parentLayout;
    private AppCompatButton enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        initElements();

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate();
            }
        });
        onCheckBoxClick();
    }

    private void authenticate() {
        if(validateData()) {
            allowEntry();
        }
    }

    private void allowEntry() {
        warnUsername.setVisibility(View.GONE);
        warnPin.setVisibility(View.GONE);

        Snackbar.make(parentLayout, "Access Granted", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    private boolean validateData() {
        if(editUsername.getText().toString().equals("")) {
            warnUsername.setVisibility(View.VISIBLE);
            warnUsername.setText("Please enter a username");
            return false;
        }

        if(pinCheckBox.isChecked()) {
            if(editPin.getText().toString().equals("")) {
                warnPin.setVisibility(View.VISIBLE);
                warnPin.setText("Enter a 4-DIGIT PIN");
                return  false;
            }
        }
        return true;
    }

    private void initElements() {
        pinCheckBox = findViewById(R.id.setPin);
        editPin = findViewById(R.id.editPin);
        editUsername = findViewById(R.id.editUsername);
        parentLayout = findViewById(R.id.parentLayout);
        enterBtn = findViewById(R.id.enterBtn);
        warnUsername = findViewById(R.id.warnUsername);
        warnPin = findViewById(R.id.warnPin);
    }

    public void onCheckBoxClick() {
        pinCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editPin.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_LONG).show();
                } else {
                    editPin.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "not selected", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}