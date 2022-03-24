package com.example.appsale30112021.presentation.sign_up;

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

public class SignUpViewModel extends ViewModel {
    private AuthRepository authRepository;
    private MutableLiveData<AppResponse<UserResponse>> userResponse = new MutableLiveData<>();

    @Inject
    public SignUpViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<AppResponse<UserResponse>> getLoginStatus() {
        return userResponse;
    }

    public void perFormSignUp(String email, String name, String password, String phone, String address) {
        userResponse.setValue(new AppResponse.Loading(null));
        authRepository.signUp(new UserRequest(email, password, name, phone, address)).enqueue(new Callback<AppResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<AppResponse<UserResponse>> call, Response<AppResponse<UserResponse>> response) {
                if (response.errorBody() != null) {
                    try {
                        JSONObject jsonObjectError = new JSONObject(response.errorBody().string());
                        userResponse.setValue(new AppResponse.Error<>(jsonObjectError.getString("message")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
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
