package com.robinschest.savingstracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.robinschest.savingstracker.R;


public class AccessActivity extends AppCompatActivity {
    private CheckBox pinCheckBox;
    private EditText editPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        pinCheckBox = findViewById(R.id.setPin);
        editPin = findViewById(R.id.editPin);
        onCheckBoxClick();
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