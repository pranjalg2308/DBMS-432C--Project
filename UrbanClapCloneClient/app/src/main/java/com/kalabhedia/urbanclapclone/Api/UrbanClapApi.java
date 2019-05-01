package com.kalabhedia.urbanclapclone.Api;

import com.kalabhedia.urbanclapclone.Api.Models.Booking;
import com.kalabhedia.urbanclapclone.Api.Models.ServiceCategory;
import com.kalabhedia.urbanclapclone.Api.Models.ServiceProvider;
import com.kalabhedia.urbanclapclone.Api.Models.ServicesOffered;
import com.kalabhedia.urbanclapclone.Api.Models.Status;
import com.kalabhedia.urbanclapclone.Api.Models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UrbanClapApi {


    @GET("/api/categories")
    Call<List<ServiceCategory>> getAllServiceCategory();

    @GET("/api/users")
    Call<List<Users>> getAllUsers();

    @POST("/api/users/register")
    @FormUrlEncoded
    Call<Status> registerNewUser(@Field("user_id") String user_id,
                                 @Field("password") String password,
                                 @Field("email") String email,
                                 @Field("firstName") String firstName,
                                 @Field("lastName") String lastName,
                                 @Field("gender") String gender,
                                 @Field("age") int age,
                                 @Field("contactNo") int contactNo,
                                 @Field("addLine1") String addLine1,
                                 @Field("addLine2") String addLine2,
                                 @Field("city") String city);


    @POST("/api/providers/register/{username}")
    @FormUrlEncoded
    Call<Status> changepassword(@Field("password") String password, @Path("username") String username);


    @POST("/api/completeOrders/{booking_id}")
    @FormUrlEncoded
    Call<Status> submitBookingRating(@Path("booking_id") String bookingId, @Field("rating") String rating);

    @POST("/api/checkUser")
    @FormUrlEncoded
    Call<Status> checkUserName(@Field("user_id") String userName);

    @POST("/api/user/login")
    @FormUrlEncoded
    Call<Status> checkLoginCredential(@Field("user_id") String userName, @Field("password") String password);

    @GET("/api/providers/{city}&{category_id}")
    Call<List<ServiceProvider>> getServiceProvider(@Path("city") String city, @Path("category_id") String categoryId);

    @GET("/api/orders/{user_id}")
    Call<List<Booking>> getUserBooking(@Path("user_id") String userId);

    @POST("/api/completeOrders/{orderId}")
    @FormUrlEncoded
    Call<Status> rateFinishedOrder(@Path("orderId") String orderId, @Field("rating") int rate);

    @PUT("/api/deleteOrders/{orderId}")
    Call<Status> cancelOrder(@Path("orderId") String orderId);

    @GET("/api/users/{user_id}")
    Call<List<Users>> userData(@Path("user_id") String  user_id);

    @GET("/api/services/{company_name}")
    Call<List<ServicesOffered>> offeredServiceCompanyWise(@Path("company_name") String companyName);

    @POST("/api/order")
    @FormUrlEncoded
    Call<Status> orderService(@Field("user_id") String userId,
                              @Field("service_id") String serviceId,
                              @Field("start_time") long startTime,
                              @Field("duration") int duration,
                              @Field("date_of_order") long dateOfOrder);

}
