package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.countries;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.parashot_trader.codesroots.smartpanetask.domain.ApiClient;
import com.parashot_trader.codesroots.smartpanetask.domain.ServerGateway;

public class CountriesViewModelFactory implements ViewModelProvider.Factory {


    private Application application;


    public CountriesViewModelFactory(Application application1) {
        application = application1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == CountriesViewModel.class)
        {
            return (T) new CountriesViewModel(getApiService());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ServerGateway getApiService() {
        return ApiClient.getClient().create(ServerGateway.class);
    }

}
