package com.example.appsale30112021.data.remote;

import com.example.appsale30112021.data.remote.request.UserRequest;
import com.example.appsale30112021.data.remote.response.UserResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/sign-in")
    Call<Response<UserResponse>> signIn(@Body UserRequest userRequest);
}