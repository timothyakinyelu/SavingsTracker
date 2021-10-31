package com.robinschest.savingstracker.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.robinschest.savingstracker.MainActivity;
import com.robinschest.savingstracker.R;

public class GenericTextWatcher implements TextWatcher {
    private final View currentView;
    private final View nextView;

    public GenericTextWatcher(View currentView, View nextView) {
        this.currentView = currentView;
        this.nextView = nextView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        int id = currentView.getId();

        if(id == R.id.pin_edit_box4) {
            if (text.length() == 1) {
                currentView.clearFocus();
            }
        } else {
            if (text.length() == 1) {
                nextView.requestFocus();
            }
        }
    }
}
