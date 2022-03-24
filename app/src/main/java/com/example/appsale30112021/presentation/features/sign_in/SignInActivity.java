package com.example.appsale30112021.presentation.features.sign_in;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.appsale30112021.R;
import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.UserResponse;
import com.example.appsale30112021.databinding.ActivitySignInBinding;
import com.example.appsale30112021.presentation.features.sign_up.SignUpActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignInActivity extends DaggerAppCompatActivity {

    @Inject
    SignInViewModel mSignViewModel;

    ActivitySignInBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        initView();
        observerData();
        event();

    }

    private void event() {
        mBinding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mBinding.textEditEmail.getText().toString();
                String password = mBinding.textEditPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Input is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                mSignViewModel.perFormSignIn(email, password);
            }
        });

        mBinding.textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

    private void initView() {
        setSupportActionBar(mBinding.toolbarLogin);
        getSupportActionBar().setTitle("Sign In");
    }

    private void observerData() {

        mSignViewModel.getLoginStatus().observe(this, new Observer<AppResponse<UserResponse>>() {
            @Override
            public void onChanged(AppResponse<UserResponse> response) {
                String token = mSignViewModel.appPreference.getToken();
                Log.d("BBB","Token " + token);
                switch (response.status) {
                    case ERROR:
                        Toast.makeText(SignInActivity.this, response.message, Toast.LENGTH_SHORT).show();
                        isShowLoad(false);
                        break;
                    case SUCCESS:
                        Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        isShowLoad(false);
                        break;
                    case LOADING:
                        isShowLoad(true);
                        break;
                }
            }
        });
    }

    private void isShowLoad(boolean isShow) {
        if (isShow) {
            mBinding.includeLoading.layoutLoading.setVisibility(View.VISIBLE);
        } else {
            mBinding.includeLoading.layoutLoading.setVisibility(View.GONE);
        }

    }

}