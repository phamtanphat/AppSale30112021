package com.example.appsale30112021.presentation.sign_up;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.UserResponse;
import com.example.appsale30112021.databinding.ActivitySignUpBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignUpActivity extends DaggerAppCompatActivity {

    @Inject
    SignUpViewModel mViewModel;

    ActivitySignUpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initView();
        observerData();
        event();
    }

    private void initView() {
        setSupportActionBar(mBinding.toolbarRegister);
        getSupportActionBar().setTitle("Sign Up");
    }

    private void event() {
        mBinding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mBinding.textEditEmail.getText().toString();
                String password = mBinding.textEditPassword.getText().toString();
                String name = mBinding.textEditName.getText().toString();
                String phone = mBinding.textEditPhone.getText().toString();
                String address = mBinding.textEditLocation.getText().toString();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "You have not filled in enough information", Toast.LENGTH_SHORT).show();
                    return;
                }
                mViewModel.perFormSignUp(email, name, password, phone, address);
            }
        });
    }

    private void observerData() {
        mViewModel.getSignUpStatus().observe(this, new Observer<AppResponse<UserResponse>>() {
            @Override
            public void onChanged(AppResponse<UserResponse> response) {
                switch (response.status){
                    case LOADING:
                        isShowLoad(true);
                        break;
                    case SUCCESS:
                        Toast.makeText(SignUpActivity.this,"You have successfully registered", Toast.LENGTH_SHORT).show();
                        isShowLoad(false);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                    case ERROR:
                        Toast.makeText(SignUpActivity.this, response.message, Toast.LENGTH_SHORT).show();
                        isShowLoad(false);
                        break;
                }
            }
        });
    }
    private void isShowLoad(boolean isShow) {
        if (isShow) {
            mBinding.containerLoading.layoutLoading.setVisibility(View.VISIBLE);
        } else {
            mBinding.containerLoading.layoutLoading.setVisibility(View.GONE);
        }

    }
}