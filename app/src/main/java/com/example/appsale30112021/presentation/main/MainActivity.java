package com.example.appsale30112021.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appsale30112021.R;
import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.ProductResponse;
import com.example.appsale30112021.presentation.sign_up.SignUpActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                switch (response.status){
                    case LOADING:
//                        isShowLoad(true);
                        break;
                    case SUCCESS:
//                        Toast.makeText(SignUpActivity.this,"You have successfully registered", Toast.LENGTH_SHORT).show();
//                        isShowLoad(false);
                        Log.d("BBB",response.data.size() + "");
                        break;
                    case ERROR:
//                        Toast.makeText(SignUpActivity.this, response.message, Toast.LENGTH_SHORT).show();
//                        isShowLoad(false);
                        Log.d("BBB",response.message);
                        break;
                }
            }
        });
    }

    private void initView() {
    }
}