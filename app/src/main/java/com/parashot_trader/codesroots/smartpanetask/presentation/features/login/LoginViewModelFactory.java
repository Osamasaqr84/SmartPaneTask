package com.parashot_trader.codesroots.smartpanetask.presentation.features.login;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ApiClient;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.ServerGateway;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.repositries.LoginRepository;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.domain.repositries.RegisterRepository;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries.CountriesViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public LoginViewModelFactory(Application application1) {
        application = application1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == LoginViewModel.class)
        {
            return (T) new LoginViewModel(getLoginRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private LoginRepository getLoginRepositry ()
    {
        return new LoginRepository(getApiService());
    }

    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }

}
