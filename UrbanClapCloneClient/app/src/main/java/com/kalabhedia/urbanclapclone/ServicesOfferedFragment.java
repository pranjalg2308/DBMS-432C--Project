package com.kalabhedia.urbanclapclone;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.ServiceProvider;
import com.kalabhedia.urbanclapclone.Api.Models.ServicesOffered;
import com.kalabhedia.urbanclapclone.Api.Models.Status;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.RecyclerViewAdapters.ServiceOfferedRecyclerAdapter;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;
import com.kalabhedia.urbanclapclone.Utils.TimeStampConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesOfferedFragment extends Fragment {


    private Context context;
    private View view;
    private List<ServicesOffered> servicesOfferedList = null;
    private RecyclerView mRecyclerView;
    private ServiceOfferedRecyclerAdapter mAdapter;
    private ServiceProvider currentServiceProvider;

    public ServicesOfferedFragment(Context context, ServiceProvider serviceProvider) {
        this.context = context;
        this.currentServiceProvider = serviceProvider;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_services_offered, container, false);
        mRecyclerView = view.findViewById(R.id.servicesOfferedRecyclerView);
        mAdapter = new ServiceOfferedRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setItemListener(new ServiceOfferedRecyclerAdapter.ItemListener() {
            @Override
            public void currentServiceOffered(ServicesOffered servicesOffered) {
                promtBooking(servicesOffered);
            }
        });

        Call<List<ServicesOffered>> serviceOfferedCall = UrbanClapApiClient.getClient().offeredServiceCompanyWise(currentServiceProvider.getUserId());
        serviceOfferedCall.enqueue(new Callback<List<ServicesOffered>>() {
            @Override
            public void onResponse(Call<List<ServicesOffered>> call, Response<List<ServicesOffered>> response) {
                if (response.body() != null)
                    mAdapter.setServicesOfferedList(response.body());
            }

            @Override
            public void onFailure(Call<List<ServicesOffered>> call, Throwable t) {

            }
        });
        return view;
    }

    private void promtBooking(final ServicesOffered servicesOffered) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.booking_dialog_layout);
        final TextView numHourInputTextView = dialog.findViewById(R.id.numHoursTextView);
        TextView perHourTextView = dialog.findViewById(R.id.perHourTextView);
        final DatePicker datePicker = dialog.findViewById(R.id.datePicker);
        final TimePicker timePicker = dialog.findViewById(R.id.timePicker);
        ImageButton orderButton = dialog.findViewById(R.id.orderButton);
        Button addButton = dialog.findViewById(R.id.addButton);
        Button minusButton = dialog.findViewById(R.id.minusButton);
        Button selectTimeButton = dialog.findViewById(R.id.selectTimeButton);
        Button selectDateButton = dialog.findViewById(R.id.selectDateButton);
        datePicker.setMinDate(Calendar.getInstance().getTimeInMillis());
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderService(datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear(), timePicker.getHour(), timePicker.getMinute(), servicesOffered.getSERVICE_ID(), Integer.parseInt(numHourInputTextView.getText().toString()));
                dialog.dismiss();
            }
        });
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.GONE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.GONE);
            }
        });
        if (servicesOffered.getPERHOUR() != 1) {
            addButton.setVisibility(View.GONE);
            minusButton.setVisibility(View.GONE);
            perHourTextView.setVisibility(View.GONE);
            numHourInputTextView.setVisibility(View.GONE);
        } else {
            addButton.setVisibility(View.VISIBLE);
            minusButton.setVisibility(View.VISIBLE);
            perHourTextView.setVisibility(View.VISIBLE);
            numHourInputTextView.setVisibility(View.VISIBLE);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numHourInputTextView.setText((Integer.parseInt(numHourInputTextView.getText().toString()) + 1) + "");
                }
            });
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((Integer.parseInt(numHourInputTextView.getText().toString()) > 1))
                        numHourInputTextView.setText((Integer.parseInt(numHourInputTextView.getText().toString()) - 1) + "");
                }
            });
        }
    }

    private void orderService(int dayOfMonth, int month, int year, int hour, int minute, String service_id, int duration) {
        String dateString = year + "-" + month + "-" + dayOfMonth + " " + hour + ":" + minute;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = null;
        long millis = 0;
        try {
            date = sdf.parse(dateString);
            millis = date.getTime();
        } catch (ParseException e) {

        }
        long l = Calendar.getInstance().getTimeInMillis() / 1000L;
        String timeForUnixTime = TimeStampConverter.getTimeForUnixTime(l);
        Call<Status> orderServiceStatusCall = UrbanClapApiClient.getClient().orderService(CredentialsUtil.getUsername(getContext()), service_id, millis / 1000L, duration, Calendar.getInstance().getTimeInMillis() / 1000L);
        orderServiceStatusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getContext(), "Service Booked", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }


}
