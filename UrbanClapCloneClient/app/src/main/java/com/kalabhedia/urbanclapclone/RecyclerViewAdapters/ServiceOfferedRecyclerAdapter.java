package com.kalabhedia.urbanclapclone.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.ServicesOffered;
import com.kalabhedia.urbanclapclone.R;

import java.util.List;

public class ServiceOfferedRecyclerAdapter extends RecyclerView.Adapter<ServiceOfferedRecyclerAdapter.ServiceOfferedViewHolder> {

    private ItemListener itemListener;
    private List<ServicesOffered> servicesOfferedList = null;

    public void setServicesOfferedList(List<ServicesOffered> servicesOfferedList) {
        this.servicesOfferedList = servicesOfferedList;
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ServiceOfferedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_offered_layout_view, parent, false);
        return new ServiceOfferedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceOfferedViewHolder holder, int position) {
        holder.setData(servicesOfferedList.get(position));
    }

    @Override
    public int getItemCount() {
        if (servicesOfferedList == null)
            return 0;
        return servicesOfferedList.size();
    }

    public static interface ItemListener {
        public void currentServiceOffered(ServicesOffered servicesOffered);
    }


    public class ServiceOfferedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView serviceNameTextView;
        TextView serviceDescriptionTextView;
        TextView chargeTextView;
        TextView perHourTextView;
        ServicesOffered servicesOffered;

        public ServiceOfferedViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceDescriptionTextView = itemView.findViewById(R.id.serviceDescriptionTextView);
            serviceNameTextView = itemView.findViewById(R.id.serviceNameTextView);
            perHourTextView = itemView.findViewById(R.id.perHourTextView);
            chargeTextView = itemView.findViewById(R.id.chargeTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.currentServiceOffered(servicesOffered);
        }

        public void setData(ServicesOffered servicesOffered) {
            this.servicesOffered = servicesOffered;
            serviceDescriptionTextView.setText(servicesOffered.getDESCRIPTION());
            serviceNameTextView.setText(servicesOffered.getNAME());
            int perHour = servicesOffered.getPERHOUR();
            if (perHour == 1) {
                perHourTextView.setText("per hour");
            } else {
                perHourTextView.setText("per service");
            }
            chargeTextView.setText(servicesOffered.getCHARGE() + "");
        }
    }


}
