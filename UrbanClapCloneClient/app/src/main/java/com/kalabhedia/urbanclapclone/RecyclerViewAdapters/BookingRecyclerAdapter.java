package com.kalabhedia.urbanclapclone.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalabhedia.urbanclapclone.Api.Models.Booking;
import com.kalabhedia.urbanclapclone.R;
import com.kalabhedia.urbanclapclone.Utils.TimeStampConverter;

import java.util.List;

public class BookingRecyclerAdapter extends RecyclerView.Adapter<BookingRecyclerAdapter.BookingViewHolder> {

    private PendingCustomListener pendingCustomListener;
    private RatingCustomListener ratingCustomListener;

    private List<Booking> bookingList = null;

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }


    public void setPendingCustomListener(PendingCustomListener pendingCustomListener) {
        this.pendingCustomListener = pendingCustomListener;
    }

    public void setRatingCustomListener(RatingCustomListener ratingCustomListener) {
        this.ratingCustomListener = ratingCustomListener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_layout_view, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking currentBooking = bookingList.get(position);
        holder.setData(currentBooking);
    }

    @Override
    public int getItemCount() {
        if (bookingList == null)
            return 0;
        return bookingList.size();
    }


    public static interface PendingCustomListener {
        public void currentServiceOffered(Booking booking);
    }

    public static interface RatingCustomListener {
        public void currentServiceOffered(Booking booking);
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder {

        private TextView serviceTextView;
        private TextView timeEditText;
        private TextView dateEditText;
        private ImageButton pendingStatusImageButtom;
        private TextView pendingStatusEditText;
        private TextView bookingDateTextView;
        private TextView bookingTimeTextView;
        private RatingBar ratingStars;
        private TextView notRatedTextView;
        private TextView durationTextView;
        //ItemViewObject
        private Booking booking;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            //ItemViewObjectInstantiation
            serviceTextView = itemView.findViewById(R.id.serviceNameTextView);
            timeEditText = itemView.findViewById(R.id.timeTextView);
            dateEditText = itemView.findViewById(R.id.dateTextView);
            pendingStatusImageButtom = itemView.findViewById(R.id.pendingStatusImageView);
            pendingStatusEditText = itemView.findViewById(R.id.pendingStatusTextView);
            bookingDateTextView = itemView.findViewById(R.id.bookingDateTextView);
            bookingTimeTextView = itemView.findViewById(R.id.bookingTimeTextView);
            ratingStars = itemView.findViewById(R.id.ratingStars);
            notRatedTextView = itemView.findViewById(R.id.notRatedTextView);
            durationTextView = itemView.findViewById(R.id.durationTextView);

        }

        public void setData(Booking currentBooking) {
            this.booking = currentBooking;
            //data feed into ItemViewObjects
            serviceTextView.setText(booking.getNAME());
            timeEditText.setText(TimeStampConverter.getTimeForUnixTime(booking.getSTART_TIME()));
            dateEditText.setText(TimeStampConverter.getDateForUnixTime(booking.getSTART_TIME()));
            bookingTimeTextView.setText(TimeStampConverter.getTimeForUnixTime(booking.getDATE_OF_ORDER()));
            bookingDateTextView.setText(TimeStampConverter.getDateForUnixTime(booking.getDATE_OF_ORDER()));
            durationTextView.setText("for " + booking.getDURATION() + " hrs");
            long currentSystemTime = System.currentTimeMillis() / 1000L;
            long startTime = currentBooking.getSTART_TIME();
            if (currentBooking.getRATING() == 0)
                ratingStars.setVisibility(View.GONE);
            else {
                ratingStars.setVisibility(View.VISIBLE);
                ratingStars.setNumStars(currentBooking.getRATING());
            }
            pendingStatusImageButtom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pendingCustomListener.currentServiceOffered(booking);
                }
            });
            if (startTime > currentSystemTime) {
                if (booking.getDELETED() == 1) {
                    pendingStatusEditText.setText("Cancelled");
                    pendingStatusImageButtom.setVisibility(View.GONE);
                } else {
                    pendingStatusEditText.setText("Scheduled");
                    pendingStatusImageButtom.setImageResource(R.drawable.ic_cancel_circle);
                    pendingStatusImageButtom.setVisibility(View.VISIBLE);
                }
            } else {
                if (booking.getDELETED() == 1) {
                    pendingStatusImageButtom.setVisibility(View.GONE);
                    pendingStatusEditText.setText("Cancelled");
                } else {
                    pendingStatusImageButtom.setImageResource(R.drawable.ic_check_circle);
                    pendingStatusEditText.setText("Done");
                }
            }
        }

    }

}

