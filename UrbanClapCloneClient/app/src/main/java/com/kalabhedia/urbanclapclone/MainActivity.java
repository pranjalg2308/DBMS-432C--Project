package com.kalabhedia.urbanclapclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkuserLogIn();
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (bottomNavigationView.getSelectedItemId() != menuItem.getItemId()) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_bar_home:
                            navController.navigate(R.id.homeFragment);
                            navController.popBackStack(R.id.homeFragment, false);
                            break;
                        case R.id.nav_bar_bookings:
                            navController.navigate(R.id.bookingFragment);
                            break;
                        case R.id.nav_bar_profile:
                            navController.navigate(R.id.profileFragment);
                            break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navController.popBackStack(R.id.homeFragment, false);
        bottomNavigationView.setSelectedItemId(R.id.nav_bar_home);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }

    private void checkuserLogIn() {
        if (!new CredentialsUtil().isUserLogged(getApplicationContext())) {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
