package com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.repositries;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ServerGateway;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.RegisterResponse;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterRepository {


    private ServerGateway apiService;
    private Consumer<RegisterResponse> onSuccess;
    private Consumer<Throwable> onError;

    public RegisterRepository(ServerGateway apiService) {
        this.apiService = apiService;
    }

    public void registerUser(User user) {

         apiService.Register(
                 user.getName(),user.getUsernam(),user.getMobile(),user.getPassword(),
                 user.getMail(),user.getGender()
                ).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, final Response<RegisterResponse> response) {
                if (onSuccess!=null)
                    onSuccess.accept(response.body());

            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }


    public void setOnSuccess(Consumer<RegisterResponse> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


}
