package com.parashot_trader.codesroots.smartpanetask.presentation.features.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.parashot_trader.codesroots.smartpanetask.domain.repositries.LoginRepository;
import com.parashot_trader.codesroots.smartpanetask.entities.LoginResponse;
import com.parashot_trader.codesroots.smartpanetask.entities.User;


public class LoginViewModel extends ViewModel {

    private LoginRepository loginRepository;
    MutableLiveData<LoginResponse> loginLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<String> validateLiveDate = new MutableLiveData<>();

    public LoginViewModel() {
    }

    public void login (User user)
    {
        if (validate(user))
        loginRepository.login(user);
    }

    public LoginViewModel(final LoginRepository repository) {

        repository.setOnSuccess(model -> {
            loginLiveData.postValue(model);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
        });

        this.loginRepository = repository;
    }

    private boolean validate(User user) {
        if ( user.getUsernam().matches("") || user.getPassword().matches("")) {
            validateLiveDate.postValue("please commplete empty data");
            return false;
        }
        else return true;
    }

}
