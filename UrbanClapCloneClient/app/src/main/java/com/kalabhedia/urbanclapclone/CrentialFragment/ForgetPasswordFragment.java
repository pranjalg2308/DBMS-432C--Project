package com.kalabhedia.urbanclapclone.CrentialFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.kalabhedia.urbanclapclone.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {

    private EditText userNameEdit;
    private TextInputEditText passwordEditText;
    private TextInputEditText confirmPasswordEditText;
    private Button submitButton;
    private View view;

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        userNameEdit = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        submitButton = view.findViewById(R.id.submitButton);
        return view;
    }

}
