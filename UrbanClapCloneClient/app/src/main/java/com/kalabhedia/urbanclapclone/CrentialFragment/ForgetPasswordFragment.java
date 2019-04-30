package com.kalabhedia.urbanclapclone.CrentialFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.kalabhedia.urbanclapclone.Api.Models.Status;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {

    private EditText userNameEdit;
    private TextInputLayout passwordEditText;
    private TextInputLayout confirmPasswordEditText;
    private Button submitButton;
    private ProgressBar progressBar;
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
        progressBar = view.findViewById(R.id.progressBar2);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userNameEdit.getText().toString().isEmpty() && !passwordEditText.getEditText().getText().toString().isEmpty() && !confirmPasswordEditText.getEditText().getText().toString().isEmpty()) {
                    if (passwordEditText.getEditText().getText().toString().equals(confirmPasswordEditText.getEditText().getText().toString())) {
                        submitButton.setEnabled(false);
                        progressBar.setVisibility(View.VISIBLE);
                        changePassword();
                    } else {
                        Toast.makeText(getContext(), "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Enter Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void changePassword() {
        Call<Status> passwordChangeStatusCall = UrbanClapApiClient.getClient().changepassword(passwordEditText.getEditText().toString(), userNameEdit.getText().toString());
        passwordChangeStatusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.body().getStatus())
                    getFragmentManager().beginTransaction().replace(R.id.credential_fragment, new LogInFragment()).commit();
                else
                    Toast.makeText(getContext(), "Returned False", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
