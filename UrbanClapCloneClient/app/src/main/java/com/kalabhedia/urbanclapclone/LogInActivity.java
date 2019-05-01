package com.kalabhedia.urbanclapclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.textfield.TextInputEditText;
import com.kalabhedia.urbanclapclone.CrentialFragment.LogInFragment;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;

public class LogInActivity extends AppCompatActivity {

    private NavController navController;
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        toolbar = getSupportActionBar();
        getSupportFragmentManager().beginTransaction().add(R.id.credential_fragment,new LogInFragment()).commit();
        System.out.println();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }


}
