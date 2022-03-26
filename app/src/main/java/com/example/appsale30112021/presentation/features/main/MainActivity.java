package com.example.appsale30112021.presentation.features.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.ProductResponse;
import com.example.appsale30112021.databinding.ActivityMainBinding;
import com.example.appsale30112021.presentation.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    MainViewModel mMainViewModel;

    ActivityMainBinding mBinding;

    List<ProductResponse> mListProduct;
    ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initView();
        observerData();
        event();
    }

    private void event() {
        mMainViewModel.perFormFetchProduct();
    }

    private void observerData() {
        mMainViewModel.getProductResponse().observe(this, new Observer<AppResponse<List<ProductResponse>>>() {
            @Override
            public void onChanged(AppResponse<List<ProductResponse>> response) {
                switch (response.status) {
                    case LOADING:
                        isShowLoad(true);
                        break;
                    case SUCCESS:
                        mProductAdapter.updateListProduct(response.data);
                        isShowLoad(false);
                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this, response.message, Toast.LENGTH_SHORT).show();
                        isShowLoad(false);
                        break;
                }
            }
        });
    }

    private void initView() {
        //toolbar
        setSupportActionBar(mBinding.toolbarHome);
        getSupportActionBar().setTitle("Product");


        mListProduct = new ArrayList<>();
        mProductAdapter = new ProductAdapter();
        mBinding.recyclerViewHome.setAdapter(mProductAdapter);
    }

    private void isShowLoad(boolean isShow) {
        if (isShow) {
            mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
        } else {
            mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
        }

    }
}