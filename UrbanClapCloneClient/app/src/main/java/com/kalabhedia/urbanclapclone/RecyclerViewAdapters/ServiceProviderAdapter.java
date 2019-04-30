package com.kalabhedia.urbanclapclone.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.ServiceProvider;
import com.kalabhedia.urbanclapclone.R;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ServiceProviderViewHolder> {

    private List<ServiceProvider> serviceProviderList = null;
    private ItemListener itemListener;

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_provider_layout_view, parent, false);
        return new ServiceProviderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceProviderViewHolder holder, int position) {
        holder.setData(serviceProviderList.get(position));
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    @Override
    public int getItemCount() {
        if (serviceProviderList == null)
            return 0;
        return serviceProviderList.size();
    }

    public static interface ItemListener {
        public void currentCategory(ServiceProvider serviceProvider);
    }

    public class ServiceProviderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView serviceProviderCompanyTextView;
        private TextView addressTextView;
        private TextView contactNoTextView;
        private TextView ratingTextView;
        private RatingBar ratingBar;
        private ServiceProvider serviceProvider;

        public ServiceProviderViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceProviderCompanyTextView = itemView.findViewById(R.id.serviceNameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            contactNoTextView = itemView.findViewById(R.id.contactNoTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(this);
        }

        public void setData(ServiceProvider serviceProvider) {
            this.serviceProvider = serviceProvider;
            serviceProviderCompanyTextView.setText(serviceProvider.getCompanyName());
            addressTextView.setText(serviceProvider.getAddLine1());
            contactNoTextView.setText(serviceProvider.getContactNo() + "");
            ratingTextView.setText(serviceProvider.getRating() + "");
            ratingBar.setNumStars(serviceProvider.getRating());
        }

        @Override
        public void onClick(View view) {
            itemListener.currentCategory(serviceProvider);
        }
    }
}
