package com.kalabhedia.urbanclapclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class ShoppingActivity extends AppCompatActivity {

    private Intent intent;
    private String categoryName;
    private String categoryId;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        setUpToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.shoppingActivityFrameLayout, new ProvidersFragment(categoryId)).commit();
    }

    private void setUpToolbar() {
        intent = getIntent();
        categoryId = intent.getExtras().getString("CategoryId");
        categoryName = intent.getExtras().getString("CategoryName");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(categoryName);
        }
    }

}
