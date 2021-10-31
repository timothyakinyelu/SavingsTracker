package com.robinschest.savingstracker.utils;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.robinschest.savingstracker.R;

public class GenericKeyEvent implements View.OnKeyListener {
    private final EditText currentView;
    private final EditText previousView;

    public GenericKeyEvent(EditText currentView, EditText previousView) {
        this.currentView = currentView;
        this.previousView = previousView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN &&
        currentView.getId() != R.id.pin_edit_box1 && currentView.getText().toString().isEmpty()) {
            previousView.requestFocus();
            return true;
        }
        return false;
    }
}
