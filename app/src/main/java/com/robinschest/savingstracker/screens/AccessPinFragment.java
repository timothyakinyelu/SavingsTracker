package com.robinschest.savingstracker.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.robinschest.savingstracker.BaseFragment;
import com.robinschest.savingstracker.MainActivity;
import com.robinschest.savingstracker.R;
import com.robinschest.savingstracker.utils.GenericKeyEvent;
import com.robinschest.savingstracker.utils.GenericTextWatcher;

public class AccessPinFragment extends BaseFragment implements View.OnClickListener {
    private EditText pinEditBox1, pinEditBox2, pinEditBox3, pinEditBox4;
    private ConstraintLayout accessPinLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_access_pin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initElements(view);
        addTextChangeListener();
        setKeyListener();
        setBottomNavViewVisibility(View.GONE);
    }

    private void setKeyListener() {
        pinEditBox1.setOnKeyListener(new GenericKeyEvent(pinEditBox1, null));
        pinEditBox2.setOnKeyListener(new GenericKeyEvent(pinEditBox2, pinEditBox1));
        pinEditBox3.setOnKeyListener(new GenericKeyEvent(pinEditBox3, pinEditBox2));
        pinEditBox4.setOnKeyListener(new GenericKeyEvent(pinEditBox4, pinEditBox3));
    }

    private void addTextChangeListener() {
        pinEditBox1.addTextChangedListener(new GenericTextWatcher(pinEditBox1, pinEditBox2));
        pinEditBox2.addTextChangedListener(new GenericTextWatcher(pinEditBox2, pinEditBox3));
        pinEditBox3.addTextChangedListener(new GenericTextWatcher(pinEditBox3, pinEditBox4));
        pinEditBox4.addTextChangedListener(new GenericTextWatcher(pinEditBox4, null));
    }

    private void initElements(View view) {
        pinEditBox1 = view.findViewById(R.id.pin_edit_box1);
        pinEditBox2 = view.findViewById(R.id.pin_edit_box2);
        pinEditBox3 = view.findViewById(R.id.pin_edit_box3);
        pinEditBox4 = view.findViewById(R.id.pin_edit_box4);
        AppCompatButton enterBtn = view.findViewById(R.id.enterBtn);
        accessPinLayout = view.findViewById(R.id.accessPinLayout);

        enterBtn.setOnClickListener(this);
        accessPinLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.enterBtn) {
            authenticate(v);
        } else if (id == R.id.accessPinLayout) {
            MainActivity.hideSoftKeyBoard(accessPinLayout, getActivity());
        }
    }

    /**
     * check if entered pin matches hashed value stored in rooms
     **/
    private void authenticate(View view) {
        if (validateInput()) {
            NavDirections action = AccessPinFragmentDirections.actionAccessPinFragmentToHomeFragment();
            Navigation.findNavController(view).navigate(action);
        }
    }

    private boolean validateInput() {
        if (!pinEditBox2.getText().toString().isEmpty()
                && !pinEditBox2.getText().toString().isEmpty()
                && !pinEditBox3.getText().toString().isEmpty()
                && !pinEditBox4.getText().toString().isEmpty()) {
            return true;
        }

        Toast.makeText(getContext(), "Incomplete Value", Toast.LENGTH_LONG).show();
        return false;
    }
}