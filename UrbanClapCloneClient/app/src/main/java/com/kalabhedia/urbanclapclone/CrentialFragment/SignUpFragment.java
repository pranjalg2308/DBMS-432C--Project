package com.kalabhedia.urbanclapclone.CrentialFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.kalabhedia.urbanclapclone.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText addressEditText;
    private TextInputEditText passWordEditText;
    private EditText usernameEditText;
    private RadioGroup sexRadioGroup;
    private Button submitButton;
    private View view;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        addressEditText = view.findViewById(R.id.addressMultiAutoCompleteTextView);
        passWordEditText = view.findViewById(R.id.password_edit_text);
        usernameEditText = view.findViewById(R.id.username_text_view);
        sexRadioGroup = view.findViewById(R.id.sexRadioGroup);
        submitButton = view.findViewById(R.id.submit_button);


        firstNameEditText.setOnClickListener(new submitButtonListener());
        lastNameEditText.setOnClickListener(new submitButtonListener());
        addressEditText.setOnClickListener(new submitButtonListener());
        passWordEditText.setOnClickListener(new submitButtonListener());
        usernameEditText.setOnClickListener(new submitButtonListener());
        return view;
    }

    private boolean checkUsernameAvailability(String userName) {

        return true;
    }

    private String getFirstName() {
        return firstNameEditText.getText().toString();
    }


    private String getLastName() {
        return lastNameEditText.getText().toString();
    }


    private String getaddress() {
        return addressEditText.getText().toString();
    }

    private String getUserName() {
        return usernameEditText.getText().toString();
    }

    private String getPassword() {
        return passWordEditText.getText().toString();
    }


    private class submitButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (!getFirstName().isEmpty() && !getLastName().isEmpty() && !getaddress().isEmpty() && getPassword().isEmpty() && checkUsernameAvailability(getUserName())){
                submitButton.setEnabled(true);
            }
        }
    }
}
