package com.example.appsale30112021.presentation.features.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.ProductResponse;
import com.example.appsale30112021.data.repository.ProductRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private ProductRepository productRepository;
    private MutableLiveData<AppResponse<List<ProductResponse>>> productResponse = new MutableLiveData<>();

    @Inject
    public MainViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public LiveData<AppResponse<List<ProductResponse>>> getProductResponse() {
        return productResponse;
    }

    public void perFormFetchProduct(){
        productResponse.setValue(new AppResponse.Loading(null));
        productRepository.fetchProduct().enqueue(new Callback<AppResponse<List<ProductResponse>>>() {
            @Override
            public void onResponse(Call<AppResponse<List<ProductResponse>>> call, Response<AppResponse<List<ProductResponse>>> response) {
                if (response.errorBody() != null) {
                    try {
                        JSONObject jsonObjectError = new JSONObject(response.errorBody().string());
                        productResponse.setValue(new AppResponse.Error<>(jsonObjectError.getString("message")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    productResponse.setValue(new AppResponse.Success<>(response.body().data));
                }
            }

            @Override
            public void onFailure(Call<AppResponse<List<ProductResponse>>> call, Throwable t) {
                productResponse.setValue(new AppResponse.Error<>(t.getMessage()));
            }
        });
    }
}
