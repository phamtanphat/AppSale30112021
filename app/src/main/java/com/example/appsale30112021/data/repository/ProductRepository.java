package com.example.appsale30112021.data.repository;

import com.example.appsale30112021.data.remote.ApiService;
import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.ProductResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class ProductRepository {
    private ApiService apiService;

    @Inject
    public ProductRepository(ApiService apiService) {
        this.apiService = apiService;
    }


    public Call<AppResponse<List<ProductResponse>>> fetchProduct() {
        return apiService.fetchProduct();
    }
}
