package com.kalabhedia.urbanclapclone;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.ServiceCategory;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.RecyclerViewAdapters.ServicesRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    private List<ServiceCategory> serviceCategoryList = null;
    private RecyclerView mRecyclerView;
    private ServicesRecyclerAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.serviceRecyclerView);
        mAdapter = new ServicesRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setItemListener(new ServicesRecyclerAdapter.ItemListener() {
            @Override
            public void currentCategory(ServiceCategory serviceCategory) {
                Toast.makeText(getContext(), serviceCategory.getCategoryId(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("CategoryName",serviceCategory.getCategory());
                bundle.putString("CategoryId",serviceCategory.getCategoryId());
                Intent intent = new Intent(getContext(), ShoppingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Call<List<ServiceCategory>> listCall = UrbanClapApiClient.getClient().getAllServiceCategory();
        listCall.enqueue(new Callback<List<ServiceCategory>>() {
            @Override
            public void onResponse(Call<List<ServiceCategory>> call, Response<List<ServiceCategory>> response) {
                serviceCategoryList = response.body();
                mAdapter.setServiceCategoryList(serviceCategoryList);
            }

            @Override
            public void onFailure(Call<List<ServiceCategory>> call, Throwable t) {
                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
