package com.kalabhedia.urbanclapclone.CrentialFragment;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.kalabhedia.urbanclapclone.Api.Models.Status;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.R;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private EditText addressEditText1;
    private ImageButton imageButton;
    private TextView availabilityTextView;
    private View view;
    private EditText contactEditText;
    private EditText ageEditText;
    private EditText emailEditText;
    private EditText cityEditText;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        initViews();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
        return view;
    }

    private void registerNewUser() {
        Call<Status> registerNewUserCall = UrbanClapApiClient.getClient().registerNewUser(
                usernameEditText.getText().toString(),
                passWordEditText.getEditableText().toString(),
                emailEditText.getText().toString(),
                firstNameEditText.getText().toString(),
                lastNameEditText.getText().toString(),
                getGender(),
                Integer.parseInt(ageEditText.getText().toString()),
                Integer.parseInt(ageEditText.getText().toString()),
                addressEditText.getText().toString(),
                addressEditText1.getText().toString(),
                cityEditText.getText().toString()
        );


        registerNewUserCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {

                Status status = response.body();
                if (!response.body().getStatus()) {
                    getFragmentManager().beginTransaction().replace(R.id.credential_fragment, new LogInFragment()).commit();
                } else {
                    Toast.makeText(getContext(), "Check Info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getGender() {
        if (sexRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton)
            return "M";
        return "F";
    }

    private void initViews() {
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        addressEditText = view.findViewById(R.id.addressMultiAutoCompleteTextView);
        addressEditText1 = view.findViewById(R.id.addressMultiAutoCompleteTextView1);
        passWordEditText = view.findViewById(R.id.password_edit_text);
        usernameEditText = view.findViewById(R.id.usernameEditText);
        sexRadioGroup = view.findViewById(R.id.sexRadioGroup);
        submitButton = view.findViewById(R.id.submit_button);
        availabilityTextView = view.findViewById(R.id.availabilityTextView);
        imageButton = view.findViewById(R.id.availabilityImageButton);
        contactEditText = view.findViewById(R.id.contactNumberEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        emailEditText = view.findViewById(R.id.emailTextView);
        cityEditText = view.findViewById(R.id.cityEditText);
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();
            }
        });
        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();
            }
        });
        addressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        passWordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        addressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        contactEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSubmitCriteria();

            }
        });
        cityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkUsernameAvailability(usernameEditText.getText().toString());
            }
        });
    }

    private void checkUsernameAvailability(String userName) {
        if (userName.isEmpty())
            return;

        Call<Status> userNameStatusCall = UrbanClapApiClient.getClient().checkUserName(userName);
        userNameStatusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.getClass().toString() == "error") {
                    imageButton.setImageResource(R.drawable.ic_cancel_circle);
                    availabilityTextView.setVisibility(View.VISIBLE);
                    availabilityTextView.setText("Unavailable");
                    imageButton.setVisibility(View.VISIBLE);
                    submitButton.setEnabled(false);
                } else {
                    imageButton.setImageResource(R.drawable.ic_check_circle);
                    availabilityTextView.setVisibility(View.VISIBLE);
                    availabilityTextView.setText("Available");
                    imageButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
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


    private void checkSubmitCriteria() {
        if (!getFirstName().isEmpty() && !getLastName().isEmpty() && !getaddress().isEmpty() && !getPassword().isEmpty()
                && !contactEditText.getText().toString().isEmpty()
                && !ageEditText.getText().toString().isEmpty()) {
            submitButton.setEnabled(true);
        }

    }
}
