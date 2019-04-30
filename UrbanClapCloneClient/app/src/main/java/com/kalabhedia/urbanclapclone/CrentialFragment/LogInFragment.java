package com.kalabhedia.urbanclapclone.CrentialFragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.kalabhedia.urbanclapclone.Api.Models.Status;
import com.kalabhedia.urbanclapclone.Api.Models.Users;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.MainActivity;
import com.kalabhedia.urbanclapclone.R;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private ProgressBar progressBar;
    private FragmentManager fragmentManager;

    public LogInFragment() {

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
        progressBar = view.findViewById(R.id.progressBar);
        forgotPassordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.credential_fragment, new ForgetPasswordFragment()).commit();
            }
        });
        newUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.credential_fragment, new SignUpFragment()).commit();
            }
        });
        submitButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userNameEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);
                    submitButtom.setEnabled(false);
                    forgotPassordTextView.setEnabled(false);
                    newUserTextView.setEnabled(false);
                    logIn();
                } else {
                    Toast.makeText(getContext(), "Enter Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void logIn() {
        Call<List<Users>> userCall = UrbanClapApiClient.getClient().userData(userNameEditText.getText().toString());
        userCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.body() != null){
                    CredentialsUtil.saveCity(getContext(), response.body().get(0).getCity());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getContext(), "User not available, sign up now!", Toast.LENGTH_SHORT).show();
            }
        });
        Call<Status> statusCall = UrbanClapApiClient.getClient().checkLoginCredential(userNameEditText.getText().toString(), passwordEditText.getText().toString());
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.body().getStatus()) {
                    CredentialsUtil.saveLogIn(getContext(), userNameEditText.getText().toString());
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    progressBar.setVisibility(View.GONE);
                    submitButtom.setEnabled(true);
                    Toast.makeText(getContext(), "Returned False, Retry", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
