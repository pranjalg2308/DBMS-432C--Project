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

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkUserLogIn();
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (bottomNavigationView.getSelectedItemId() != menuItem.getItemId()) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_bar_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout,new HomeFragment()).commit();
                            break;
                        case R.id.nav_bar_bookings:
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout,new BookingFragment()).commit();
                            break;
                        case R.id.nav_bar_profile:
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout,new ProfileFragment()).commit();

                            break;
                    }
                }
                return true;
            }
        });
    }



    private void checkUserLogIn() {
        if (!CredentialsUtil.isUserLogged(getApplicationContext())) {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
