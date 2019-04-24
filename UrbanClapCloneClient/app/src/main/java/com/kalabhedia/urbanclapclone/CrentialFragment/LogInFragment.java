package com.kalabhedia.urbanclapclone.CrentialFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.kalabhedia.urbanclapclone.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {

    private EditText userNameEditText;
    private TextInputEditText passwordEditText;
    private Button submitButtom;
    private TextView forgotPassordTextView;
    private TextView newUserTextView;
    private View view;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        userNameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        submitButtom = view.findViewById(R.id.submit_button);
        forgotPassordTextView = view.findViewById(R.id.forgot_password_text_view);
        newUserTextView = view.findViewById(R.id.new_user_text_view);

        return view;
    }

}
