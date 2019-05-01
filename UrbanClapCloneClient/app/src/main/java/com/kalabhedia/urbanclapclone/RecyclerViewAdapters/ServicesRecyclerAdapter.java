package com.kalabhedia.urbanclapclone.RecyclerViewAdapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.ServiceCategory;
import com.kalabhedia.urbanclapclone.R;

import java.util.List;

public class ServicesRecyclerAdapter extends RecyclerView.Adapter<ServicesRecyclerAdapter.ServiceViewHolder> {


    private List<ServiceCategory> serviceCategoryList = null;
    private ItemListener itemListener;

    public void setServiceCategoryList(List<ServiceCategory> serviceCategoryList) {
        this.serviceCategoryList = serviceCategoryList;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_category_layout_view, parent, false);
        return new ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceCategory currentService = serviceCategoryList.get(position);
        holder.setData(currentService);
    }

    @Override
    public int getItemCount() {
        if (serviceCategoryList == null)
            return 0;
        return serviceCategoryList.size();
    }

    public static interface ItemListener {
        public void currentCategory(ServiceCategory serviceCategory);
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView serviceNameTextView;
        TextView montlyPaymentTextView;
        ImageView serviceThumbnail;
        ServiceCategory serviceCategory;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            Log.v("ViewHolder", "in View Holder");
            serviceNameTextView = itemView.findViewById(R.id.serviceNameTextView);
            montlyPaymentTextView = itemView.findViewById(R.id.monthlyPaymentTextView);
            serviceThumbnail = itemView.findViewById(R.id.serviceThumbnail);
            itemView.setOnClickListener(this);
        }

        public void setData(ServiceCategory serviceCategory) {
            this.serviceCategory = serviceCategory;
            serviceNameTextView.setText(serviceCategory.getCategory());
            montlyPaymentTextView.setText(serviceCategory.getMonthlyPayement() + "");
        }

        @Override
        public void onClick(View view) {
            itemListener.currentCategory(serviceCategory);
        }

    }
}
