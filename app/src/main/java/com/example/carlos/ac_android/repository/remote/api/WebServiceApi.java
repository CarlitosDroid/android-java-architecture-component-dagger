package com.example.carlos.ac_android.repository.remote.api;


import android.arch.lifecycle.LiveData;

import com.example.carlos.ac_android.repository.remote.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/21/18.
 */

public interface WebServiceApi {

    @GET("users/{userId}")
    Call<UserResponse> getUser(@Path("userId")int userId);

    @GET("users/{userId}")
    LiveData<ApiResponse<UserResponse>> getUser2(@Path("userId") int userId);
}
