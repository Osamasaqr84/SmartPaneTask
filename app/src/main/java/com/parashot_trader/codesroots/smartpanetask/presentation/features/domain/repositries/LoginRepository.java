package com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.repositries;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;

import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ServerGateway;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.LoginResponse;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {


    private ServerGateway apiService;
    private Consumer<LoginResponse> onSuccess;
    private Consumer<Throwable> onError;

    public LoginRepository(ServerGateway apiService) {
        this.apiService = apiService;
    }

    public void login(User user) {

         apiService.Login(
                 user.getUsernam(),user.getPassword()
                ).
                 enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                onSuccess.accept(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }


    public void setOnSuccess(Consumer<LoginResponse> onSuccess) {
        this.onSuccess = onSuccess;
    }
    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
