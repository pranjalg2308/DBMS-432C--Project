package com.kalabhedia.urbanclapclone;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;


public class ProfileFragment extends Fragment {


    private TextView userNameTextView;
    private EditText addressEditText;
    private EditText cityEditText;
    private Button saveButton;
    private Button logOutButton;
    private View view;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        logOutButton = view.findViewById(R.id.logout_bn);
        userNameTextView = view.findViewById(R.id.username_text_view);
        addressEditText = view.findViewById(R.id.addressEditText);
        cityEditText = view.findViewById(R.id.cityEditText);
        saveButton = view.findViewById(R.id.saveButton);
        userNameTextView.setText(CredentialsUtil.getUsername(getContext()));
        cityEditText.setText(CredentialsUtil.getCity(getContext()));
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cityEditText.getText().toString().isEmpty()){
                    CredentialsUtil.saveLogIn(getContext(),cityEditText.getText().toString());
                }
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CredentialsUtil().logOut(getContext());
                Intent intent = new Intent(getContext(), LogInActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
