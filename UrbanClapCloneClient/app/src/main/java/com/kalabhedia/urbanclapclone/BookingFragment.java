package com.kalabhedia.urbanclapclone;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.Booking;
import com.kalabhedia.urbanclapclone.Api.Models.Status;
import com.kalabhedia.urbanclapclone.Api.UrbanClapApiClient;
import com.kalabhedia.urbanclapclone.RecyclerViewAdapters.BookingRecyclerAdapter;
import com.kalabhedia.urbanclapclone.Utils.CredentialsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    private View view;
    private List<Booking> bookingList = null;
    private RecyclerView mRecyclerView;
    private BookingRecyclerAdapter mAdapter;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_booking, container, false);
        mRecyclerView = view.findViewById(R.id.bookingRecyclerView);
        mAdapter = new BookingRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setPendingCustomListener(new BookingRecyclerAdapter.PendingCustomListener() {
            @Override
            public void currentServiceOffered(Booking booking) {
//                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
                if (booking.getDELETED() != 1)
                    initCancelPromt(booking.getORDER_ID());
            }
        });
        mAdapter.setRatingCustomListener(new BookingRecyclerAdapter.RatingCustomListener() {
            @Override
            public void currentServiceOffered(Booking booking) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
                initRatingPromt(booking);
            }
        });
        refreshRecyclerView();
        return view;
    }

    private void refreshRecyclerView() {
        final Call<List<Booking>> bookingCall = UrbanClapApiClient.getClient().getUserBooking(CredentialsUtil.getUsername(getContext()));
        bookingCall.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                bookingList = response.body();
                if (bookingList != null) {
                    mAdapter.setBookingList(bookingList);
                    for (int i = 0; i < bookingList.size(); i++) {
                        long l = System.currentTimeMillis();
                        long l1 = bookingList.get(i).getSTART_TIME();
                        System.out.println();
                        if (bookingList.get(i).getRATING() == 0 && (bookingList.get(i).getSTART_TIME()  < System.currentTimeMillis()/1000L)) {
                            initRatingPromt(bookingList.get(i));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }

    private void initRatingPromt(final Booking booking) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.rating_promt_layout);
        TextView serviceNameTextView = dialog.findViewById(R.id.serviceTextView);
        serviceNameTextView.setText(booking.getNAME());
        final RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
        final Button cancelButton = dialog.findViewById(R.id.cancelRatingButton);
        final Button rateButton = dialog.findViewById(R.id.rateButton);
        dialog.setCancelable(false);
        dialog.show();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        rateButton.setOnClickListener(new View.OnClickListener() {
            Booking currentBooking = booking;
            @Override
            public void onClick(View view) {
                rateOrder(currentBooking, ratingBar.getNumStars());
                dialog.dismiss();
                refreshRecyclerView();
            }
        });
    }

    private void rateOrder(Booking booking, int rating) {
        Call<Status> statusCall = UrbanClapApiClient.getClient().rateFinishedOrder(booking.getORDER_ID(), rating);
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status currentStatus = response.body();
                if (currentStatus.getStatus()) {
                    refreshRecyclerView();
                    Toast.makeText(getContext(), "Order Rated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Can Not be Rated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }

    private void initCancelPromt(final String order_id) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.cancel_order_promt_layout);
        TextView serviceNameTextView = dialog.findViewById(R.id.serviceNameTextView);
        Button cancelButton = dialog.findViewById(R.id.cancelBookingButton);
        Button dontCancelButton = dialog.findViewById(R.id.dontCancelBookingButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                cancelOrder(order_id);
            }
        });
        dontCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    private void cancelOrder(String order_id) {
        Call<Status> cancelStatusCall = UrbanClapApiClient.getClient().cancelOrder(order_id);
        cancelStatusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status cancelStatus = response.body();
                if (cancelStatus.getStatus()) {
                    Toast.makeText(getContext(), "Order Cancelled Successfully", Toast.LENGTH_SHORT).show();
                    refreshRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(), "Order Not Cancelled Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
