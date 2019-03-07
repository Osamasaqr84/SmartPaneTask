package com.parashot_trader.codesroots.smartpanetask.presentation.features.register;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ApiClient;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ServerGateway;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.repositries.RegisterRepository;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.login.LoginViewModel;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {


    private Application application;


    public RegisterViewModelFactory(Application application1) {
        application = application1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == RegisterViewModel.class)
        {
            return (T) new RegisterViewModel(getRegisterRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private RegisterRepository getRegisterRepositry ()
    {
        return new RegisterRepository(getApiService());
    }
    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }

}
