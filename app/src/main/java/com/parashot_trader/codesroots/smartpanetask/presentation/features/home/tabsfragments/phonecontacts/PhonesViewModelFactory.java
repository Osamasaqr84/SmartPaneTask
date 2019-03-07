package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.phonecontacts;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.parashot_trader.codesroots.smartpanetask.domain.ApiClient;
import com.parashot_trader.codesroots.smartpanetask.domain.ServerGateway;

public class PhonesViewModelFactory implements ViewModelProvider.Factory {


    private Activity activity;

    public PhonesViewModelFactory(Activity activity1) {
        activity = activity1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == PhoneContactsViewModel.class)
        {
            return (T) new PhoneContactsViewModel(activity);
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }




}
