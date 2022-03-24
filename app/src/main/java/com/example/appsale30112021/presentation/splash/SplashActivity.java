package com.example.appsale30112021.presentation.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import com.example.appsale30112021.R;
import com.example.appsale30112021.databinding.ActivitySplashBinding;
import com.example.appsale30112021.presentation.main.MainActivity;
import com.example.appsale30112021.presentation.sign_in.SignInActivity;
import com.example.appsale30112021.ultils.AppPreference;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    AppPreference mAppPreference;

    ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

       mBinding.animationView.addAnimatorListener(new Animator.AnimatorListener() {
           @Override
           public void onAnimationStart(Animator animator) {

           }

           @Override
           public void onAnimationEnd(Animator animator) {
               String token = mAppPreference.getToken();

               if (token.isEmpty()){
                   startActivity(new Intent(SplashActivity.this,SignInActivity.class));
               }else{
                   startActivity(new Intent(SplashActivity.this, MainActivity.class));
               }
           }

           @Override
           public void onAnimationCancel(Animator animator) {

           }

           @Override
           public void onAnimationRepeat(Animator animator) {

           }
       });
    }
}