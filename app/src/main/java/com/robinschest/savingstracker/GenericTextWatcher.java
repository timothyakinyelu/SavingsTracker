package com.robinschest.savingstracker;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class GenericTextWatcher implements TextWatcher {
    private final View currentView;
    private final View nextView;

    public GenericTextWatcher(View currentView, View nextView) {
        this.currentView = currentView;
        this.nextView = nextView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();

        switch (currentView.getId()) {

        }
    }
}
