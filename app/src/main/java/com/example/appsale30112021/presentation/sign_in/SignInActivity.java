package com.example.appsale30112021.presentation.sign_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appsale30112021.R;
import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.UserResponse;
import com.example.appsale30112021.di.others.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignInActivity extends DaggerAppCompatActivity {

    @Inject
    SignInViewModel mSignViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mSignViewModel.getLoginStatus().observe(this, new Observer<AppResponse<UserResponse>>() {
            @Override
            public void onChanged(AppResponse<UserResponse> response) {
                switch (response.status){
                    case ERROR:
                        Log.d("BBB","Lỗi " + response.message);
                        break;
                    case SUCCESS:
                        Log.d("BBB","Login Success");
                        break;
                    case LOADING:
                        Log.d("BBB","Loading");
                        break;
                }
            }
        });

        mSignViewModel.perFormSignIn("dem2@gmail.com","12345678");
    }
}