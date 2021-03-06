package com.example.appsale30112021.presentation.features.sign_in;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsale30112021.data.remote.request.UserRequest;
import com.example.appsale30112021.data.remote.response.AppResponse;
import com.example.appsale30112021.data.remote.response.UserResponse;
import com.example.appsale30112021.data.repository.AuthRepository;
import com.example.appsale30112021.ultils.AppPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {
    public AppPreference appPreference;
    private AuthRepository authRepository;
    private MutableLiveData<AppResponse<UserResponse>> userResponse = new MutableLiveData<>();

    @Inject
    public SignInViewModel(AuthRepository authRepository, AppPreference appPreference){
        this.authRepository = authRepository;
        this.appPreference = appPreference;
    }

    public LiveData<AppResponse<UserResponse>> getLoginStatus(){
        return userResponse;
    }

    public void perFormSignIn(String email,String password){
        userResponse.setValue(new AppResponse.Loading(null));
        authRepository.signIn(new UserRequest(email,password)).enqueue(new Callback<AppResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<AppResponse<UserResponse>> call, Response<AppResponse<UserResponse>> response) {
                if (response.errorBody() != null){
                    try {
                        JSONObject jsonObjectError = new JSONObject(response.errorBody().string());
                        userResponse.setValue(new AppResponse.Error<>(jsonObjectError.getString("message")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    String token = response.body().data.getToken();
                    if (!token.isEmpty()){
                        appPreference.saveToken(token);
                    }
                    userResponse.setValue(new AppResponse.Success<>(response.body().data));
                }

            }

            @Override
            public void onFailure(Call<AppResponse<UserResponse>> call, Throwable t) {
                userResponse.setValue(new AppResponse.Error<>(t.getMessage()));
            }
        });
    }
}
