package com.kalabhedia.urbanclapclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.ServiceProvider;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.RecyclerViewAdapters.ServiceProviderAdapter;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProvidersFragment extends Fragment {

    String categoryId;
    private List<ServiceProvider> serviceProviderList = null;
    private RecyclerView mRecyclerView;
    private ServiceProviderAdapter mAdapter;
    private View view;

    public ProvidersFragment(String categoryId) {
        this.categoryId = categoryId;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_providers, container, false);
        mRecyclerView = view.findViewById(R.id.providerRecyclerView);
        mAdapter = new ServiceProviderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setItemListener(new ServiceProviderAdapter.ItemListener() {
            @Override
            public void currentCategory(ServiceProvider serviceProvider) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
        String city = CredentialsUtil.getCity(getContext());
        Call<List<ServiceProvider>> serviceProviderCall = UrbanClapApiClient.getClient().getServiceProvider(city, categoryId);

        serviceProviderCall.enqueue(new Callback<List<ServiceProvider>>() {
            @Override
            public void onResponse(Call<List<ServiceProvider>> call, Response<List<ServiceProvider>> response) {
                List<ServiceProvider> serviceProviderList = response.body();
                if (serviceProviderList != null)
                    mAdapter.setServiceProviderList(serviceProviderList);
            }

            @Override
            public void onFailure(Call<List<ServiceProvider>> call, Throwable t) {

            }
        });
        mAdapter.setItemListener(new ServiceProviderAdapter.ItemListener() {
            @Override
            public void currentCategory(ServiceProvider serviceProvider) {
                getFragmentManager().beginTransaction().replace(R.id.shoppingActivityFrameLayout, new ServicesOfferedFragment(getContext(), serviceProvider)).commit();
            }
        });
        return view;
    }

}
