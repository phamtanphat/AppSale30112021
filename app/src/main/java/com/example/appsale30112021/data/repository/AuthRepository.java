package com.example.appsale30112021.data.repository;

import com.example.appsale30112021.data.remote.ApiService;
import com.example.appsale30112021.data.remote.request.UserRequest;
import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.UserResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class AuthRepository {
    private ApiService apiService;

    @Inject
    public AuthRepository(ApiService apiService){
        this.apiService = apiService;
    }


    public Call<AppResponse<UserResponse>> signIn(UserRequest userRequest){
        return apiService.signIn(userRequest);
    }

    public Call<AppResponse<UserResponse>> signUp(UserRequest userRequest){
        return apiService.signUp(userRequest);
    }
}
